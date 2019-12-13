
package acme.features.authenticated.messagethread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messagethreads.Messagethread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessagethreadRepository extends AbstractRepository {

	@Query("select a from Messagethread a where a.id = ?1")
	Messagethread findOneById(int id);

	@Query("select p.messagethread from Participant p where p.user.id=?1 ")
	Collection<Messagethread> findManyMine(int id);

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findAuthenticatedById(int id);

}
