<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-hidden path="job.id"/>
	<acme:form-textbox code="employer.duty.form.label.job.reference"	path="job.reference" readonly="true"/>
	<acme:form-textbox code="employer.duty.form.label.title" path="title"/>
	<acme:form-textarea code="employer.duty.form.label.description" path="description"/>
	<acme:form-double code="employer.duty.form.label.percentageOfTime" path="percentageOfTime"/>
  	
  	<acme:form-submit 
		test="${command == 'create'}"
		code="employer.duty.form.button.create" 
		action="/employer/duty/create"/>
	<acme:form-submit 
		test="${command != 'create' and job.finalMode == false}"
		code="employer.duty.form.button.update" 
		action="/employer/duty/update"/>
	<acme:form-submit 
		test="${command != 'create' and job.finalMode == false}"
		code="employer.duty.form.button.delete" 
		action="/employer/duty/delete"/>
  	<acme:form-submit 
		method="get" 
		code="employer.duty.form.button.job" 
		action="/employer/job/show?id=${job.id}"/>
  	<acme:form-return code="employer.duty.form.button.return"/>
</acme:form>
