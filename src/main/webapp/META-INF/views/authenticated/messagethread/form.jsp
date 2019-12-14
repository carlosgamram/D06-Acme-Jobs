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

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.messagethread.form.lable.owner.identity.fullName" path="owner.identity.fullName"/>
	<acme:form-textbox code="authenticated.messagethread.form.lable.title" path="title"/>
	<acme:form-moment code="authenticated.messagethread.form.lable.creationMoment" path="creationMoment"/>
	
	<acme:form-submit 
		method="get" 
		code="authenticated.messagethread.form.button.authenticated.show" 
		action="/authenticated/authenticated/show?id=${owner.id}"/>
	
	<acme:form-submit 
		method="get" 
		code="authenticated.messagethread.form.button.authenticated.list" 
		action="/authenticated/authenticated/list?id=${id}"/>
	
  	<acme:form-return code="authenticated.messagethread.form.button.return"/>
</acme:form>
<acme:form>
	<acme:form-submit 
		method="get" 
		code="authenticated.messagethread.form.button.message" 
		action="/authenticated/message/list?id=${id}"/>
			
	<acme:form-submit 
		method="get" 
		code="authenticated.messagethread.form.button.create" 
		action="/authenticated/message/create?id=${id}"/>
	
</acme:form>