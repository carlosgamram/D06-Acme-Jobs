<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
 	<jstl:if test="${command == 'show-by-auditrecord'}">
 		<acme:form-textbox code="worker.auditor.form.label.userIdentity.fullName" path="identity.fullName"/>
 		<acme:form-textbox code="worker.auditor.form.label.userIdentity.email" path="identity.email"/>
 	</jstl:if>
	<acme:form-textbox code="worker.auditor.form.label.firm" path="firm"/>
	<acme:form-textarea code="worker.auditor.form.label.responsibility" path="responsibility"/>
	
	<acme:form-submit test="${command == 'create'}" code="worker.auditor.form.button.create" action="/worker/auditor/create"/>
	<acme:form-submit test="${command == 'update'}" code="worker.auditor.form.button.update" action="/worker/auditor/update"/>
	<acme:form-return code="worker.auditor.form.button.return"/>
</acme:form>
