
package acme.features.authenticated.messagethread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SpamUtils;
import acme.entities.messagethreads.Messagethread;
import acme.entities.participant.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessagethreadCreateService implements AbstractCreateService<Authenticated, Messagethread> {

	@Autowired
	private AuthenticatedMessagethreadRepository	repository;

	@Autowired
	private SpamUtils								spamUtils;


	@Override
	public boolean authorise(final Request<Messagethread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Messagethread> request, final Messagethread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Messagethread> request, final Messagethread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "title", "owner");
	}

	@Override
	public Messagethread instantiate(final Request<Messagethread> request) {
		Messagethread result = new Messagethread();

		int id = request.getPrincipal().getAccountId();
		Authenticated auth = this.repository.findAuthenticatedByUserAccountId(id);

		Participant participant = new Participant();

		participant.setUser(auth);
		participant.setMessagethread(result);

		result.setOwner(auth);

		return result;
	}

	@Override
	public void validate(final Request<Messagethread> request, final Messagethread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		errors.state(request, !this.spamUtils.checkSpam(entity.getTitle()), "title", "authenticated.messagethread.form.errors.spam.title");
	}

	@Override
	public void create(final Request<Messagethread> request, final Messagethread entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);

		Participant participant;
		participant = new Participant();

		participant.setUser(entity.getOwner());
		participant.setMessagethread(entity);

		this.repository.save(participant);
	}
}
