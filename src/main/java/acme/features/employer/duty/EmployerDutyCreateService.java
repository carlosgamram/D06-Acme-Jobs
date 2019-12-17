
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SpamUtils;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	@Autowired
	private EmployerDutyRepository	repository;

	@Autowired
	private SpamUtils				spamUtils;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		int jobId = request.getModel().getInteger("job.id");
		Job job = this.repository.findOneJobById(jobId);

		boolean result;
		Employer employer;
		Principal principal;

		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;

	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percentageOfTime", "job", "job.id", "job.reference");

	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		Duty result = new Duty();

		int jobId = request.getModel().getInteger("job.id");
		Job job = this.repository.findOneJobById(jobId);
		result.setJob(job);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Double sumPercentageOfTime = this.repository.sumPercentageOfTimeByJobId(entity.getJob().getId());
		if (sumPercentageOfTime != null) {
			Double sumPercentageOfTimeTotal = sumPercentageOfTime + entity.getPercentageOfTime();
			if (sumPercentageOfTime == 100L) {
				errors.state(request, false, "percentageOfTime", "employer.duty.form.errors.percentageOfTime.limit");
			} else if (sumPercentageOfTimeTotal > 100L) {
				errors.state(request, false, "percentageOfTime", "employer.duty.form.errors.percentageOfTime", 100 - sumPercentageOfTime);
			}
		}

		errors.state(request, !this.spamUtils.checkSpam(entity.getTitle()), "title", "employer.duty.form.errors.spam.title");
		errors.state(request, !this.spamUtils.checkSpam(entity.getDescription()), "description", "employer.duty.form.errors.spam.description");

		if (errors.hasErrors()) {
			request.getModel().setAttribute("job", entity.getJob());
		}
	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		this.repository.save(entity);
	}

}
