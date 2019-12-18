
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messages.Message;
import acme.entities.messagethreads.Messagethread;
import acme.entities.participant.Participant;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select a from Message a where a.id = ?1")
	Message findOneById(int id);

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findManyByMessagethread(int id);

	@Query("select mt from Messagethread mt where mt.id = ?1")
	Messagethread findMessageThreadById(int messageThreadid);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int authenticatedId);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedByUserAccountId(int id);

	@Query("select p from Participant p where p.messagethread.id = ?1 and p.user.id = ?2")
	Participant findManyParticipantByMessagethreadId(int mtId, int authId);
}
