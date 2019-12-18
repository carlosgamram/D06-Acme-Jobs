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
	<%-- <acme:form-textbox code="authenticated.participant.form.label.user" path="user.identity.fullName"/> --%>
	<acme:form-hidden path="mt.id"/>
	<jstl:choose>
	  <jstl:when  test="${command == 'create'}">
	  	<acme:form-select code="authenticated.participant.form.label.user" path="userId" readonly="false">
			<acme:form-option code="authenticated.participant.form.label.user.selected" selected="true" value="-1"/>
			<jstl:forEach items="${users}" var="u">
			  	<acme:form-option value="${u.id}" code="${u.identity.fullName}" />
			</jstl:forEach>
	  	</acme:form-select>
	  	<%-- <acme:form-errors path="user"/> --%>
	  </jstl:when>
	  <jstl:otherwise>
	  	<acme:form-textbox code="authenticated.participant.form.label.user" path="user.identity.fullName" readonly="true"/>
	  </jstl:otherwise>
	</jstl:choose>
	
	<acme:form-submit 
			test="${command == 'create'}"
			code="authenticated.participant.form.button.authenticated.participant.create" 
			action="/authenticated/participant/create"/>
	<acme:form-submit  
		test="${command != 'create' and isOwner == true and showOwner == false}"
		code="authenticated.participant.form.button.authenticated.participant.delete" 
		action="/authenticated/participant/delete"/>
	
  	<acme:form-return code="authenticated.participant.form.button.return"/>
</acme:form>
