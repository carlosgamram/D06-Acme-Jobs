
package acme.features.authenticated.auditorrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorrequest.Auditorrequest;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorrequestCreateService implements AbstractCreateService<Authenticated, Auditorrequest> {

	@Autowired
	AuthenticatedAuditorrequestRepository repository;


	@Override
	public boolean authorise(final Request<Auditorrequest> request) {
		assert request != null;

		int id = request.getPrincipal().getActiveRoleId();
		Auditorrequest ar = this.repository.findOneByAuthenticatedId(id);

		if (ar != null) {
			return false;
		} else {
			return true;
		}

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

		int id = request.getPrincipal().getActiveRoleId();

		Authenticated auth = this.repository.findAuthenticatedById(id);

		result = new Auditorrequest();
		result.setAuthenticated(auth);

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

	@Override
	public void onSuccess(final Request<Auditorrequest> request, final Response<Auditorrequest> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
}
