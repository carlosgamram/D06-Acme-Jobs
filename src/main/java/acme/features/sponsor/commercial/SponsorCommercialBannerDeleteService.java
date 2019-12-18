
package acme.features.sponsor.commercial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class SponsorCommercialBannerDeleteService implements AbstractDeleteService<Sponsor, Commercial> {

	@Autowired
	SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<Commercial> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Commercial> request, final Commercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Commercial> request, final Commercial entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "url", "creditCardCVV", "creditCardMonth", "creditCardName", "creditCardNumber", "creditCardYear", "creditCardType");

	}

	@Override
	public Commercial findOne(final Request<Commercial> request) {
		assert request != null;

		Commercial result;

		int commercialId = request.getModel().getInteger("id");
		result = this.repository.findOneCommercialById(commercialId);

		return result;
	}

	@Override
	public void validate(final Request<Commercial> request, final Commercial entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Commercial> request, final Commercial entity) {
		this.repository.delete(entity);

	}

}
