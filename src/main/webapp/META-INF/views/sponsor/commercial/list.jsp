<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="sponsor.commercial.list.label.slogan" path="slogan" width="40%"/>
	<acme:list-column code="sponsor.commercial.list.label.picture" path="picture" width="30%"/>
	<acme:list-column code="sponsor.commercial.list.label.url" path="url" width="30%"/>
</acme:list>

<acme:form>
		<acme:form-submit 
		method="get" 
		code="sponsor.commercial.list.button.create" 
		action="/sponsor/commercial/create"/>
</acme:form>