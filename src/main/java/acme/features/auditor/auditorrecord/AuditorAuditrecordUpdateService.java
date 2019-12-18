
package acme.features.auditor.auditorrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecords.Auditrecord;
import acme.entities.configuration.SpamUtils;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditrecordUpdateService implements AbstractUpdateService<Auditor, Auditrecord> {

	@Autowired
	AuditorAuditrecordRepository	repository;

	@Autowired
	SpamUtils						spamUtils;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		Boolean result = true;

		Principal principal = request.getPrincipal();
		int auditRecId = request.getModel().getInteger("id");
		Auditrecord auditRecord = this.repository.findOneById(auditRecId);

		result = principal.getActiveRoleId() == auditRecord.getAuditor().getId();

		return result;
	}

	@Override
	public void bind(final Request<Auditrecord> request, final Auditrecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "job.id");
	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "title", "finalMode", "body", "job", "auditor");
	}

	@Override
	public Auditrecord findOne(final Request<Auditrecord> request) {
		assert request != null;

		Auditrecord result;

		int id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Auditrecord> request, final Auditrecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String body = entity.getBody();

		errors.state(request, !this.spamUtils.checkSpam(body), "body", "auditor.auditrecord.form.errors.spamControl");

		if (errors.hasErrors()) {
			request.getModel().setAttribute("job", entity.getJob());
			request.getModel().setAttribute("auditor", entity.getAuditor());
		}
	}

	@Override
	public void update(final Request<Auditrecord> request, final Auditrecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
