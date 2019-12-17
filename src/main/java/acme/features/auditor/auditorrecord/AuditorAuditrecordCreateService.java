
package acme.features.auditor.auditorrecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecords.Auditrecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

// Apuntes:
// -Comprobar que los auditrecords que no estan en modo final no se pueden listar.
// -Repasar el authorise
// -A el create de esta feature se accede desde el job

@Service
public class AuditorAuditrecordCreateService implements AbstractCreateService<Auditor, Auditrecord> {

	@Autowired
	AuditorAuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		Boolean result;

		Principal principal = request.getPrincipal();

		result = principal.getActiveRole() == Auditor.class;

		return result;
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

		request.unbind(entity, model, "title", "finalMode", "body", "job.id");
	}

	@Override
	public Auditrecord instantiate(final Request<Auditrecord> request) {
		assert request != null;

		Integer id = request.getModel().getInteger("job.id");
		if (id == null) {
			id = request.getModel().getInteger("id");
		}

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

		//TODO:Spam control missing
	}

	@Override
	public void create(final Request<Auditrecord> request, final Auditrecord entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		Integer jobId = request.getModel().getInteger("job.id");
		if (jobId == null) {
			jobId = request.getModel().getInteger("id");
		}

		Job job = this.repository.findJobById(jobId);

		Integer auditorId = request.getPrincipal().getActiveRoleId();
		Auditor auditor = this.repository.findAuditorById(auditorId);

		entity.setJob(job);
		entity.setAuditor(auditor);

		this.repository.save(entity);
	}

}
