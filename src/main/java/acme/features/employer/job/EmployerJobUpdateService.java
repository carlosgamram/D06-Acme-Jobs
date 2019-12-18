
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
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	@Autowired
	EmployerJobRepository	repository;

	@Autowired
	private SpamUtils		spamUtils;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
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

		request.unbind(entity, model, "reference", "title", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "description", "finalMode");
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;

		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Check if reference already exists-----------------------------------------------------
		String reference = entity.getReference();
		Job exists = this.repository.findOneJobByReferenceWithoutJobId(reference, entity.getId());
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
		if (entity.getFinalMode()) {
			Double sumPercentageOfTime = this.repository.sumPercentageOfTimeByJobId(entity.getId());
			errors.state(request, sumPercentageOfTime != null && sumPercentageOfTime == 100L, "finalMode", "employer.job.form.errors.duties.percentage");
		}

		errors.state(request, !this.spamUtils.checkSpam(entity.getTitle()), "title", "employer.job.form.errors.spam.title");
		errors.state(request, !this.spamUtils.checkSpam(entity.getDescription()), "description", "employer.job.form.errors.spam.description");

		if (errors.hasErrors()) {
			request.getModel().setAttribute("finalMode", false);
		}

	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
