
package acme.entities.configuration;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface SpamRepository extends AbstractRepository {

	@Query("select s from Spam s")
	Collection<Spam> findManyAll();

}
