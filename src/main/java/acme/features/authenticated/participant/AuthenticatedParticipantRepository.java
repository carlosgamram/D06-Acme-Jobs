
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messagethreads.Messagethread;
import acme.entities.participant.Participant;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedParticipantRepository extends AbstractRepository {

	@Query("select p from Participant p where p.id = ?1")
	Participant findOneById(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneAuthenticatedById(int id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedByUserAccountId(int id);

	@Query("select p from Participant p where p.messagethread.id = ?1")
	Collection<Participant> findManyByMessagethread(int id);

	@Query("select mt from Messagethread mt where mt.id = ?1")
	Messagethread findOneMessagethreadById(int id);

	@Query("select a from Authenticated a where a not in (select p.user from Participant p where p.messagethread.id = ?1)")
	Collection<Authenticated> findManyNotParticipantByMessageThread(int id);

	@Query("select p from Participant p where p.messagethread.id = ?1 and p.user.id = ?2")
	Participant findManyParticipantByMessagethreadId(int mtId, int authId);

	@Query("select p.messagethread from Participant p where p.id = ?1")
	Messagethread findMessagethreadByParticipantId(int authId);

}
