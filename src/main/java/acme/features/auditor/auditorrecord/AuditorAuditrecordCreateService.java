
package acme.features.auditor.auditorrecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecords.Auditrecord;
import acme.entities.configuration.SpamUtils;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

// Apuntes:
// -Comprobar que los auditrecords que no estan en modo final no se pueden listar.
// -Repasar el authorise
// -A el create de esta feature se accede desde el job

@Service
public class AuditorAuditrecordCreateService implements AbstractCreateService<Auditor, Auditrecord> {

	@Autowired
	private AuditorAuditrecordRepository	repository;

	@Autowired
	private SpamUtils						spamUtils;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Auditrecord> request, final Auditrecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "finalMode", "body", "job", "job.id", "auditor");
	}

	@Override
	public Auditrecord instantiate(final Request<Auditrecord> request) {
		assert request != null;

		int id = request.getModel().getInteger("job.id");

		int roleId = request.getPrincipal().getActiveRoleId();

		Job job = this.repository.findJobById(id);
		Auditor auditor = this.repository.findAuditorById(roleId);

		Auditrecord result = new Auditrecord();

		result.setJob(job);
		result.setAuditor(auditor);
		result.setFinalMode(false);

		return result;
	}

	@Override
	public void validate(final Request<Auditrecord> request, final Auditrecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		errors.state(request, !this.spamUtils.checkSpam(entity.getTitle()), "title", "auditor.auditrecord.form.errors.spam.title");
		errors.state(request, !this.spamUtils.checkSpam(entity.getBody()), "body", "auditor.auditrecord.form.errors.spam.body");

		if (errors.hasErrors()) {
			request.getModel().setAttribute("job", entity.getJob());
			request.getModel().setAttribute("auditor", entity.getAuditor());
		}

	}

	@Override
	public void create(final Request<Auditrecord> request, final Auditrecord entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);
	}

}
