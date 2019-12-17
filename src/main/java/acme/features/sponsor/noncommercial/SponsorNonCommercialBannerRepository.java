
package acme.features.sponsor.noncommercial;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.Noncommercial;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select c from Noncommercial c where c.id = ?1")
	Noncommercial findOneNonCommercialById(int nonCommercialId);

	@Query("select c from Noncommercial c where c.sponsor.id = ?1")
	Collection<Noncommercial> findManyBySponsorId(int sponsorId);

	@Query("select sp from Sponsor sp where sp.id = ?1")
	Sponsor findSponsorById(int sponsorId);

}
