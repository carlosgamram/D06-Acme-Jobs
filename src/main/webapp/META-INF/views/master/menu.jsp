<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">

			<acme:menu-suboption code="master.menu.anonymous.investorrecords.all" action="/anonymous/investorrecord/list"/>
			<acme:menu-suboption code="master.menu.anonymous.investorrecords.best" action="/anonymous/investorrecord/list-best"/>
			<acme:menu-separator/>
		 	<acme:menu-suboption code="master.menu.anonymous.companyrecord" action="/anonymous/companyrecord/list"/>
		 	<acme:menu-suboption code="master.menu.anonymous.companyrecords.best" action="/anonymous/companyrecord/list-best"/>
		 	<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.anonymous.announcement" action="/anonymous/announcement/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.announcement" action="/administrator/announcement/list"/>
			<acme:menu-suboption code="master.menu.administrator.challenge" action="/administrator/challenge/list"/>
      <acme:menu-suboption code="master.menu.administrator.investorrecord" action="/administrator/investorrecord/list"/>
			<acme:menu-suboption code="master.menu.administrator.companyrecord" action="/administrator/companyrecord/list"/>
      <acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.auditorrequest" action="/administrator/auditorrequest/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.spam" action="/administrator/spam/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.commercial" action="/administrator/commercial/list"/>
			<acme:menu-suboption code="master.menu.administrator.nonCommercial" action="/administrator/noncommercial/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown"/>
		
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.authenticated" access="isAuthenticated()">
      		<acme:menu-suboption code="master.menu.authenticated.investorrecords" action="/authenticated/investorrecord/list"/>
			<acme:menu-suboption code="master.menu.authenticated.announcement" action="/authenticated/announcement/list"/>
			<acme:menu-suboption code="master.menu.authenticated.challenge.active" action="/authenticated/challenge/list-all-active"/>
			<acme:menu-suboption code="master.menu.authenticated.request.active" action="/authenticated/request/list-all-active"/>
      		<acme:menu-suboption code="master.menu.authenticated.companyrecord" action="/authenticated/companyrecord/list"/>
		 	<acme:menu-suboption code="master.menu.authenticated.offer.active" action="/authenticated/offer/list-all-active"/>
		 	<acme:menu-suboption code="master.menu.authenticated.messagethread.mine" action="/authenticated/messagethread/list-mine"/>
			<acme:menu-suboption code="master.menu.authenticated.job.active" action="/authenticated/job/list-all-active"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.provider" access="hasRole('Provider')">
			<acme:menu-suboption code="master.menu.provider.request.create" action="/provider/request/create"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.create" action="/consumer/offer/create"/>
		</acme:menu-option>
		

		<acme:menu-option code="master.menu.sponsor" access="hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.sponsor.commercial.list" action="/sponsor/commercial/list-mine"/>
			<acme:menu-suboption code="master.menu.sponsor.noncommercial.list" action="/sponsor/noncommercial/list-mine"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.worker.aplication.list" action="/worker/application/list-mine"/>
    		<acme:menu-suboption code="master.menu.worker.job.list" action="/worker/job/list-mine"/>
    	</acme:menu-option>
    
		<acme:menu-option code="master.menu.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.employer.job.create" action="/employer/job/create"/>
			<acme:menu-suboption code="master.menu.employer.job.list" action="/employer/job/list-mine"/>
			<acme:menu-suboption code="master.menu.employer.application.list" action="/employer/application/list-mine"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.auditor" access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.auditor.list.has.auditrecord" action="/auditor/job/list-has-auditrecord"/>
			<acme:menu-suboption code="master.menu.auditor.list.not.has.auditrecord" action="/auditor/job/list-not-has-auditrecord"/>
			<acme:menu-suboption code="master.menu.auditor.list.mine.auditrecord" action="/auditor/auditrecord/list-mine"/>			
		</acme:menu-option>
		
	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()"/>
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()"/>

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update"/>
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create" access="!hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update" access="hasRole('Provider')"/>
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create" access="!hasRole('Consumer')"/>
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update" access="hasRole('Consumer')"/>
			 <acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create" access="!hasRole('Worker')"/> 
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update" access="hasRole('Worker')"/>
			<acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create" access="!hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update" access="hasRole('Employer')"/>
			<acme:menu-suboption code="master.menu.user-account.auditorrequest" action="/authenticated/auditorrequest/create" access="!hasRole('Auditor')"/>
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/auditor/update" access="hasRole('Auditor')"/> 
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create" access="!hasRole('Sponsor')"/>
			<acme:menu-suboption code="master.menu.user-account.sponsor" action="/authenticated/sponsor/update" access="hasRole('Sponsor')"/> 
		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()"/>
	</acme:menu-right>
</acme:menu-bar>

