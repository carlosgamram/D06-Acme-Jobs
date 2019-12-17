
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	@Autowired
	EmployerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result;
		int ApplicationId;
		Application application;
		Employer employer;
		Principal principal;

		ApplicationId = request.getModel().getInteger("id");
		application = this.repository.findOneApplicationById(ApplicationId);

		employer = application.getJob().getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "creationMoment", "status", "justification");
		request.unbind(entity, model, "statement", "skills", "qualifications", "job");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;

		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneApplicationById(id);
		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("status")) {
			if (entity.getStatus().equals(ApplicationStatus.REJECTED)) {
				errors.state(request, !entity.getJustification().isEmpty(), "justification", "employer.application.form.errors.justification");
			}
			if (entity.getStatus().equals(ApplicationStatus.ACCEPTED)) {
				entity.setJustification(null);
			}
		}

		if (errors.hasErrors()) {
			request.getModel().setAttribute("status", ApplicationStatus.PENDING);
			request.getModel().setAttribute("job", entity.getJob());
		}
	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
