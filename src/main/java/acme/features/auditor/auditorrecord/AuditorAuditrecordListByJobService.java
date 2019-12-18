
package acme.features.auditor.auditorrecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditrecords.Auditrecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditrecordListByJobService implements AbstractListService<Auditor, Auditrecord> {

	@Autowired
	AuditorAuditrecordRepository repository;


	@Override
	public boolean authorise(final Request<Auditrecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Auditrecord> request, final Auditrecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "finalMode", "body");

	}

	@Override
	public Collection<Auditrecord> findMany(final Request<Auditrecord> request) {
		assert request != null;

		Collection<Auditrecord> result;

		Integer id = request.getModel().getInteger("id");

		result = this.repository.findAuditrecordsByJob(id);

		return result;
	}

}
