
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messagethreads.Messagethread;
import acme.entities.participant.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedParticipantShowService implements AbstractShowService<Authenticated, Participant> {

	@Autowired
	private AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		int partId = request.getModel().getInteger("id");
		Authenticated auth = this.repository.findAuthenticatedByUserAccountId(request.getPrincipal().getAccountId());
		Messagethread messagethread = this.repository.findMessagethreadByParticipantId(partId);

		Participant participant = this.repository.findManyParticipantByMessagethreadId(messagethread.getId(), auth.getId());

		return participant != null;
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Authenticated auth = this.repository.findAuthenticatedByUserAccountId(request.getPrincipal().getAccountId());
		boolean isOwner = auth.getId() == entity.getMessagethread().getOwner().getId();
		boolean showOwner = entity.getUser().getId() == entity.getMessagethread().getOwner().getId();
		model.setAttribute("isOwner", isOwner);
		model.setAttribute("showOwner", showOwner);
		request.unbind(entity, model, "user.identity.fullName");

	}

	@Override
	public Participant findOne(final Request<Participant> request) {
		assert request != null;

		Participant result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
