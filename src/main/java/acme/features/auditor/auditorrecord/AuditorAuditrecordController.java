
package acme.features.auditor.auditorrecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditrecords.Auditrecord;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("auditor/auditrecord")
public class AuditorAuditrecordController extends AbstractController<Auditor, Auditrecord> {

	@Autowired
	private AuditorAuditrecordShowService		showService;

	@Autowired
	private AuditorAuditrecordListByJobService	listByJobService;

	@Autowired
	private AuditorAuditrecordCreateService		createService;

	@Autowired
	private AuditorAuditrecordListMineService	listMineService;

	@Autowired
	private AuditorAuditrecordUpdateService		updateService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addCustomCommand(CustomCommand.LIST_JOB, BasicCommand.LIST, this.listByJobService);
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
	}
}
