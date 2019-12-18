
package acme.features.authenticated.participant;

import java.util.Collection;

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
public class AuthenticatedParticipantCreateService implements AbstractCreateService<Authenticated, Participant> {

	@Autowired
	private AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Integer authId = request.getModel().getInteger("userId");
		Authenticated auth = this.repository.findOneAuthenticatedById(authId);
		entity.setUser(auth != null ? auth : null);

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Collection<Authenticated> users = this.repository.findManyNotParticipantByMessageThread(entity.getMessagethread().getId());
		model.setAttribute("users", users);
		model.setAttribute("mt.id", entity.getMessagethread().getId());
		model.setAttribute("userId", -1);
		request.unbind(entity, model);
	}

	@Override
	public Participant instantiate(final Request<Participant> request) {
		assert request != null;

		int messageThreadId = request.getModel().getInteger("mt.id");
		Messagethread messageThread = this.repository.findOneMessagethreadById(messageThreadId);

		Participant result = new Participant();

		result.setMessagethread(messageThread);

		return result;
	}

	@Override
	public void validate(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		errors.state(request, entity.getUser() != null, "userId", "authenticated.participant.form.errors.authenticated.participant");

		if (errors.hasErrors()) {
			Collection<Authenticated> users = this.repository.findManyNotParticipantByMessageThread(entity.getMessagethread().getId());
			request.getModel().setAttribute("users", users);
			request.getModel().setAttribute("mt.id", entity.getMessagethread().getId());
			request.getModel().setAttribute("userId", -1);
		}
	}

	@Override
	public void create(final Request<Participant> request, final Participant entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}
