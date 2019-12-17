
package acme.features.administrator.auditorrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditorrequest.Auditorrequest;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorrequestUpdateService implements AbstractUpdateService<Administrator, Auditorrequest> {

	@Autowired
	AdministratorAuditorrequestRepository			repository;

	@Autowired
	AdministratorAuditorrequestAuditorRepository	auditorrepository;


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
	public Auditorrequest findOne(final Request<Auditorrequest> request) {
		assert request != null;

		Auditorrequest result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Auditorrequest> request, final Auditorrequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Auditorrequest> request, final Auditorrequest entity) {
		assert request != null;
		assert entity != null;

		Auditor account = new Auditor();
		UserAccount user = entity.getAuthenticated().getUserAccount();

		if (entity.getStatus() == true) {
			account.setUserAccount(user);
			account.setFirm(entity.getFirm());
			account.setResponsibility(entity.getResponsibility());
			this.auditorrepository.save(account);
		}

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
