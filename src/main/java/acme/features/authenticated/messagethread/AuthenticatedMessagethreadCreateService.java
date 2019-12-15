
package acme.features.authenticated.messagethread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	AuthenticatedMessagethreadRepository repository;


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
		Messagethread result;
		Authenticated owner;

		result = new Messagethread();
		owner = new Authenticated();

		Participant participant;
		participant = new Participant();

		participant.setUser(owner);
		participant.setMessagethread(result);

		result.setOwner(owner);

		return result;
	}

	@Override
	public void validate(final Request<Messagethread> request, final Messagethread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Messagethread> request, final Messagethread entity) {
		Date moment;
		Messagethread result;

		int id = request.getPrincipal().getAccountId();
		Authenticated auth = this.repository.findAuthenticatedById(id);

		result = new Messagethread();
		result.setOwner(auth);

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);

		Participant participant;
		participant = new Participant();

		participant.setUser(auth);
		participant.setMessagethread(result);

		this.repository.save(participant);
	}
}
