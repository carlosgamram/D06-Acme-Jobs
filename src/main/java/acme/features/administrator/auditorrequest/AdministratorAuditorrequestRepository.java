
package acme.features.administrator.auditorrequest;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditorrequest.Auditorrequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuditorrequestRepository extends AbstractRepository {

	@Query("select a from Auditorrequest a where a.id = ?1")
	Auditorrequest findOneById(int id);

	@Query("select a from Auditorrequest a where a.status = null")
	Collection<Auditorrequest> findMany();
}
