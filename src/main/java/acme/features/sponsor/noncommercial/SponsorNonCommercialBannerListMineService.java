
package acme.features.sponsor.noncommercial;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Noncommercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SponsorNonCommercialBannerListMineService implements AbstractListService<Sponsor, Noncommercial> {

	@Autowired
	SponsorNonCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<Noncommercial> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Noncommercial> request, final Noncommercial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "url");

	}

	@Override
	public Collection<Noncommercial> findMany(final Request<Noncommercial> request) {
		assert request != null;

		Collection<Noncommercial> result;
		Principal principal;

		principal = request.getPrincipal();

		result = this.repository.findManyBySponsorId(principal.getActiveRoleId());

		return result;
	}

}
