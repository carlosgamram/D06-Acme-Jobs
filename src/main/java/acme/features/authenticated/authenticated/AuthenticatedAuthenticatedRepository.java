
package acme.features.authenticated.authenticated;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuthenticatedRepository extends AbstractRepository {

	@Query("select a from Authenticated a where a.id = ?1")
	Authenticated findOneById(int id);

	@Query("select a from Authenticated a")
	Collection<Authenticated> findAllAuthenticated();

	@Query("select a from Authenticated a where a not in (select p.user from Participant p where p.messagethread.id = ?1)")
	Collection<Authenticated> findManyAuthenticatedByMessageThread(int id);
}
