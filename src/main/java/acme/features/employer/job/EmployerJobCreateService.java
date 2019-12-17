
package acme.features.employer.job;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SpamUtils;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	@Autowired
	private EmployerJobRepository	repository;

	@Autowired
	private SpamUtils				spamUtils;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "deadline", "reference", "title", "salary", "description");
		request.unbind(entity, model, "moreInfo", "finalMode");

	}

	@Override
	public Job instantiate(final Request<Job> request) {
		Principal principal = request.getPrincipal();
		int userAccountId = principal.getAccountId();

		Employer employer = this.repository.findOneEmployerByUserAccountId(userAccountId);
		Job result = new Job();
		result.setEmployer(employer);

		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Check if reference already exists-----------------------------------------------------
		String reference = entity.getReference();
		Job exists = this.repository.findOneJobByReference(reference);
		errors.state(request, exists == null, "reference", "employer.job.form.errors.reference");

		if (!errors.hasErrors("deadline")) {
			//Check if deadline is in future------------------------------------------------------
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.DAY_OF_YEAR, 7);
			errors.state(request, entity.getDeadline().after(calendar.getTime()), "deadline", "employer.job.form.errors.deadline.week");
		}

		if (!errors.hasErrors("salary")) {
			//Check if currency is in EUR-----------------------------------------------------
			Boolean maxIsEur = entity.getSalary().getCurrency().equals("EUR") || entity.getSalary().getCurrency().equals("€");
			errors.state(request, maxIsEur, "salary", "employer.job.form.errors.salary.currency");
		}

		errors.state(request, !this.spamUtils.checkSpam(entity.getTitle()), "title", "employer.job.form.errors.spam.title");
		errors.state(request, !this.spamUtils.checkSpam(entity.getDescription()), "description", "employer.job.form.errors.spam.description");
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		this.repository.save(entity);
	}

}
