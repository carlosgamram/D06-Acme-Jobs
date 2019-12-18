
package acme.features.authenticated.participant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.participant.Participant;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/participant/")
public class AuthenticatedParticipantController extends AbstractController<Authenticated, Participant> {

	@Autowired
	private AuthenticatedParticipantListService		listService;

	@Autowired
	private AuthenticatedParticipantShowService		showService;

	@Autowired
	private AuthenticatedParticipantDeleteService	deleteService;

	@Autowired
	private AuthenticatedParticipantCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
