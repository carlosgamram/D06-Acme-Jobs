
package acme.features.sponsor.commercial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCommercialBannerRepository extends AbstractRepository {

	@Query("select c from Commercial c where c.id = ?1")
	Commercial findOneCommercialById(int commercialId);

	@Query("select c from Commercial c where c.sponsor.id = ?1")
	Collection<Commercial> findManyBySponsorId(int sponsorId);

	@Query("select sp from Sponsor sp where sp.id = ?1")
	Sponsor findSponsorById(int sponsorId);
}
