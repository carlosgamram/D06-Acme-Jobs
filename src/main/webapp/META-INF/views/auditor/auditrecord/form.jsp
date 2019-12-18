<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="job.id"/>
	<acme:form-textbox code="auditor.auditrecord.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="auditor.auditrecord.form.label.creationMoment" path="creationMoment"/>
	</jstl:if>
	<acme:form-textarea code="auditor.auditrecord.form.label.body" path="body"/>
	<acme:form-checkbox code="auditor.auditrecord.form.label.finalMode" path="finalMode"/>
	
	
	<acme:form-submit
		test="${command == 'create'}"
		code="auditor.auditrecord.form.button.create"
		action="/auditor/auditrecord/create"/>
	
	 <acme:form-submit 
	 	test = "${(command=='show' || command=='update') && !finalMode}" 
  		code="auditor.auditrecord.form.button.update" 
  		action="/auditor/auditrecord/update"/>
	
	<acme:form-submit 
		method="get" 
		code="auditor.auditrecord.form.button.job.show" 
		action="/auditor/job/show?id=${job.id}"/>
		
	<acme:form-submit 
		method="get" 
		code="auditor.auditrecord.form.button.auditor.show" 
		action="/auditor/auditor/show?id=${auditor.id}"/>
	
  	<acme:form-return code="auditor.auditrecord.form.button.return"/>
</acme:form>