
package acme.features.authenticated.auditorrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorrequest.Auditorrequest;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorrequestCreateService implements AbstractCreateService<Authenticated, Auditorrequest> {

	@Autowired
	AuthenticatedAuditorrequestRepository repository;


	@Override
	public boolean authorise(final Request<Auditorrequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Auditorrequest> request, final Auditorrequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Auditorrequest> request, final Auditorrequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsibility", "status");
	}

	@Override
	public Auditorrequest instantiate(final Request<Auditorrequest> request) {
		Auditorrequest result;

		result = new Auditorrequest();

		return result;
	}

	@Override
	public void validate(final Request<Auditorrequest> request, final Auditorrequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Auditorrequest> request, final Auditorrequest entity) {

		this.repository.save(entity);

	}
}
