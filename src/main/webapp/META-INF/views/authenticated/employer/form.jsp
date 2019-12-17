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
 	<jstl:if test="${command == 'show-by-job'}">
 		<acme:form-textbox code="authenticated.employer.employer.form.label.userIdentity.fullName" path="identity.fullName"/>
 		<acme:form-textbox code="authenticated.employer.employer.form.label.userIdentity.email" path="identity.email"/>
 	</jstl:if>
 	
	<acme:form-textbox code="authenticated.employer.employer.form.label.company" path="company"/>
	<acme:form-textbox code="authenticated.employer.employer.form.label.sector" path="sector"/>
	
	<acme:form-submit test="${command == 'create'}" 
		code="authenticated.employer.employer.form.button.create" action="/authenticated/employer/create"/>
	
	<acme:form-submit test="${command == 'update'}" 
		code="authenticated.employer.employer.form.button.update" action="/authenticated/employer/update"/>
		
	<acme:form-return code="authenticated.employer.employer.form.button.return"/>
</acme:form>
