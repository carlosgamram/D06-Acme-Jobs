
package acme.features.sponsor.noncommercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Noncommercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorNonCommercialBannerCreateService implements AbstractCreateService<Sponsor, Noncommercial> {

	@Autowired
	SponsorNonCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<Noncommercial> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Noncommercial> request, final Noncommercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Noncommercial> request, final Noncommercial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "url", "jingle");
	}

	@Override
	public Noncommercial instantiate(final Request<Noncommercial> request) {
		assert request != null;

		Noncommercial result = new Noncommercial();
		;
		Principal principal = request.getPrincipal();
		Sponsor sponsor;

		sponsor = this.repository.findSponsorById(principal.getActiveRoleId());

		result.setSponsor(sponsor);

		return result;
	}

	@Override
	public void validate(final Request<Noncommercial> request, final Noncommercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Noncommercial> request, final Noncommercial entity) {

		this.repository.save(entity);
	}

}
