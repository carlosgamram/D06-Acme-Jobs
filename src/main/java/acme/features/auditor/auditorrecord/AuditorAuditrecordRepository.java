
package acme.features.auditor.auditorrecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditrecords.Auditrecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditrecordRepository extends AbstractRepository {

	@Query("select ar from Auditrecord ar where ar.id = ?1")
	Auditrecord findOneById(int id);

	@Query("select ar from Auditrecord ar where ar.job.id = ?1")
	Collection<Auditrecord> findAuditrecordsByJob(Integer id);

	@Query("select j from Job j where j.id = ?1")
	Job findJobById(int jobId);

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findAuditorById(int roleId);

	@Query("select ar from Auditrecord ar where ar.auditor.id = ?1")
	Collection<Auditrecord> findManyMineAuditrecords(int roleId);
}
