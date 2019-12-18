
package acme.features.authenticated.messagethread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messagethreads.Messagethread;
import acme.entities.participant.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessagethreadShowService implements AbstractShowService<Authenticated, Messagethread> {

	@Autowired
	private AuthenticatedMessagethreadRepository repository;


	@Override
	public boolean authorise(final Request<Messagethread> request) {
		assert request != null;

		int mtId = request.getModel().getInteger("id");
		Authenticated auth = this.repository.findAuthenticatedByUserAccountId(request.getPrincipal().getAccountId());

		Participant participant = this.repository.findManyParticipantByMessagethreadId(mtId, auth.getId());

		return participant != null;

	}

	@Override
	public void unbind(final Request<Messagethread> request, final Messagethread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		Authenticated auth = this.repository.findAuthenticatedByUserAccountId(request.getPrincipal().getAccountId());
		boolean isOwner = auth.getId() == entity.getOwner().getId();
		model.setAttribute("isOwner", isOwner);
		request.unbind(entity, model, "creationMoment", "title", "owner.identity.fullName", "owner");

	}

	@Override
	public Messagethread findOne(final Request<Messagethread> request) {
		assert request != null;

		Messagethread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
