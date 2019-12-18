<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.application.form.lable.referenceNumber" path="referenceNumber" readonly="true"/>
	<acme:form-moment code="employer.application.form.lable.creationMoment" path="creationMoment" readonly="true"/>
	<acme:form-textbox code="employer.application.form.lable.statement" path="statement" readonly="true"/>
	<acme:form-textbox code="employer.application.form.lable.skills" path="skills" readonly="true"/>
	<acme:form-textbox code="employer.application.form.lable.qualifications" path="qualifications" readonly="true"/>
  	<jstl:choose>
	    <jstl:when test="${status != 'PENDING'}">
	        <acme:form-textbox code="employer.application.form.lable.status" path="status"/>
	    </jstl:when>    
	    <jstl:otherwise>
	        <acme:form-select code="employer.application.form.lable.status" path="status">
		  		<acme:form-option value="PENDING" code="employer.application.form.lable.status.pending"/>
		  		<acme:form-option value="ACCEPTED" code="employer.application.form.lable.status.accepted"/>
		  		<acme:form-option value="REJECTED" code="employer.application.form.lable.status.rejected"/>
		  	</acme:form-select>
	    </jstl:otherwise>
	</jstl:choose>
  	<jstl:if test="${status != 'ACCEPTED'}">
  		<acme:form-textbox code="employer.application.form.lable.justification" path="justification"/>
  	</jstl:if>
  	
  	<acme:form-submit 
		test="${command == 'show' and status == 'PENDING'}"
		code="employer.application.form.button.update" 
		action="/employer/application/update"/>
	<acme:form-submit 
		test="${command == 'update' and status == 'PENDING'}"
		code="employer.application.form.button.update" 
		action="/employer/application/update"/>
  	<acme:form-submit 
		method="get" 
		code="employer.application.form.button.job" 
		action="/employer/job/show?id=${job.id}"/>
  	<acme:form-return code="employer.application.form.button.return"/>
</acme:form>
