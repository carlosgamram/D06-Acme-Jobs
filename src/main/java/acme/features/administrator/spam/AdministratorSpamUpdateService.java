
package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Spam;
import acme.entities.configuration.SpamUtils;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSpamUpdateService implements AbstractUpdateService<Administrator, Spam> {

	@Autowired
	private SpamUtils			spamUtils;

	@Autowired
	AdministratorSpamRepository	repository;


	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "threshold", "words");
	}

	@Override
	public Spam findOne(final Request<Spam> request) {
		assert request != null;

		Spam spam;
		int id = request.getModel().getInteger("id");

		spam = this.repository.findOneSpamById(id);

		return spam;
	}

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		this.spamUtils.setSpam(entity);
	}

}
