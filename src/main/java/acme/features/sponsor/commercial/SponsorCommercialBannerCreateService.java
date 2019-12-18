
package acme.features.sponsor.commercial;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.Commercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialBannerCreateService implements AbstractCreateService<Sponsor, Commercial> {

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
	public Commercial instantiate(final Request<Commercial> request) {
		assert request != null;

		Commercial result = new Commercial();

		Principal principal = request.getPrincipal();
		Sponsor sponsor;

		sponsor = this.repository.findSponsorById(principal.getActiveRoleId());

		result.setCreditCardMonth(12);
		result.setCreditCardYear(1234);
		result.setSponsor(sponsor);

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

			//if actualYear<CCYear, the card is expired, there's no need to evaluate if actualMonth<CCMonth.
			//we have to evaluate the month if the expire year is the same as actual year.
			if (CCYear == actualYear) {
				errors.state(request, actualMonth < CCMonth, "creditCardMonth", "Sponsor.Commercial.form.errors.creditCardMonth.expired");
			}
		}
	}

	@Override
	public void create(final Request<Commercial> request, final Commercial entity) {

		this.repository.save(entity);

	}

}
