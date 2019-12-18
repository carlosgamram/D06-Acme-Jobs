
package acme.features.sponsor.commercial;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

// Discutir si es normal poder cambiar los datos de la tarjeta de crédito
@Service
public class SponsorCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, Commercial> {

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

		Calendar actualDate = Calendar.getInstance();
		int actualMonth = actualDate.get(Calendar.MONTH);
		int actualYear = actualDate.get(Calendar.YEAR);

		if (!errors.hasErrors("creditCardYear") && !errors.hasErrors("creditCardMonth")) {
			int CCMonth = entity.getCreditCardMonth();
			int CCYear = entity.getCreditCardYear();

			errors.state(request, actualYear <= CCYear, "creditCardYear", "Sponsor.Commercial.form.errors.creditCardYear.expired");

			if (CCYear == actualYear) {
				errors.state(request, actualMonth < CCMonth, "creditCardMonth", "Sponsor.Commercial.form.errors.creditCardMonth.expired");
			}
		}

	}

	@Override
	public void update(final Request<Commercial> request, final Commercial entity) {

		this.repository.save(entity);

	}

}
