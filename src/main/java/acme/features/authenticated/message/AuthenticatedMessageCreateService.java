
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.SpamUtils;
import acme.entities.messages.Message;
import acme.entities.messagethreads.Messagethread;
import acme.entities.participant.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository	repository;

	@Autowired
	private SpamUtils				spamUtils;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;
		Integer messageThreadId = request.getModel().getInteger("messageThread.id");
		if (messageThreadId == null) {
			messageThreadId = request.getModel().getInteger("id");
		}
		int authId = request.getPrincipal().getAccountId();
		Authenticated auth = this.repository.findAuthenticatedByUserAccountId(authId);

		Participant par = this.repository.findManyParticipantByMessagethreadId(messageThreadId, auth.getId());

		return par != null;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.transfer(request.getModel(), "confirm");
		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("confirm", false);
		request.unbind(entity, model, "creationMoment", "title", "tags", "body", "messageThread.id");
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		assert request != null;

		Principal principal = request.getPrincipal();

		Integer messageThreadId = request.getModel().getInteger("messageThread.id");
		if (messageThreadId == null) {
			messageThreadId = request.getModel().getInteger("id");
		}

		Authenticated authenticated = this.repository.findAuthenticatedById(principal.getActiveRoleId());
		Messagethread messageThread = this.repository.findMessageThreadById(messageThreadId);

		Message result = new Message();

		result.setAuthenticated(authenticated);
		result.setMessageThread(messageThread);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmed;
		//1: Comprobar Confirmation == 'true'
		if (!errors.hasErrors("confirm")) {
			confirmed = request.getModel().getBoolean("confirm");
			errors.state(request, confirmed, "confirm", "authenticated.message.form.error.confirmation");
		}
		//2: Comprobar Spam
		if (!errors.hasErrors()) {
			errors.state(request, !this.spamUtils.checkSpam(entity.getBody()), "body", "authenticated.message.form.error.spam");
		}

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;
		assert entity != null;

		Date date = new Date(System.currentTimeMillis());

		Principal principal = request.getPrincipal();

		Integer messageThreadId = request.getModel().getInteger("messageThread.id");
		if (messageThreadId == null) {
			messageThreadId = request.getModel().getInteger("id");
		}

		Authenticated authenticated = this.repository.findAuthenticatedById(principal.getActiveRoleId());
		Messagethread messageThread = this.repository.findMessageThreadById(messageThreadId);

		entity.setAuthenticated(authenticated);
		entity.setMessageThread(messageThread);
		entity.setCreationMoment(date);

		this.repository.save(entity);

	}

}
