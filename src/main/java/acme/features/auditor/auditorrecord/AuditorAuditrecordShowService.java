
package acme.features.auditor.auditorrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecords.Auditrecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorAuditrecordShowService implements AbstractShowService<Auditor, Auditrecord> {

	@Autowired
	AuditorAuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;

		boolean result = true;
		Principal principal = request.getPrincipal();
		int auditorPrincipalId = principal.getActiveRoleId();
		Auditrecord auditRecord = this.repository.findOneById(request.getModel().getInteger("id"));

		if (!auditRecord.getFinalMode()) {
			result = auditorPrincipalId == auditRecord.getAuditor().getId();
		}
		return result;
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
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
