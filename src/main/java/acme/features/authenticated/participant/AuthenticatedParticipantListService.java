
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messagethreads.Messagethread;
import acme.entities.participant.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipantListService implements AbstractListService<Authenticated, Participant> {

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;

		boolean result;
		int mtId;
		Messagethread mt;
		Authenticated auth;
		Principal principal;

		mtId = request.getModel().getInteger("id");
		mt = this.repository.findOneMessagethreadById(mtId);
		auth = mt.getOwner();
		principal = request.getPrincipal();
		result = auth.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "user.identity.fullName");

	}

	@Override
	public Collection<Participant> findMany(final Request<Participant> request) {
		assert request != null;

		Collection<Participant> result;

		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findManyByMessagethread(id);

		return result;
	}

}
