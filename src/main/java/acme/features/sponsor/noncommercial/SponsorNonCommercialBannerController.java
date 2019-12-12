
package acme.features.sponsor.noncommercial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.banners.Noncommercial;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@RequestMapping("/sponsor/noncommercial")
@Controller
public class SponsorNonCommercialBannerController extends AbstractController<Sponsor, Noncommercial> {

	@Autowired
	private SponsorNonCommercialBannerListMineService	listMineService;

	@Autowired
	private SponsorNonCommercialBannerShowService		showService;

	@Autowired
	private SponsorNonCommercialBannerCreateService		createService;

	@Autowired
	private SponsorNonCommercialBannerUpdateService		updateService;

	@Autowired
	private SponsorNonCommercialBannerDeleteService		deleteService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

	}

}
