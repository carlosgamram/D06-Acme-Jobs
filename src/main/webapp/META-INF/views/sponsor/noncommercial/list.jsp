<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="sponsor.noncommercial.list.label.picture" path="picture" width="20%"/>
	<acme:list-column code="sponsor.noncommercial.list.label.slogan" path="slogan" width="15%"/>
	<acme:list-column code="sponsor.noncommercial.list.label.url" path="url" width="15%"/>
</acme:list>

<acme:form>
		<acme:form-submit 
		method="get" 
		code="sponsor.noncommercial.list.button.create" 
		action="/sponsor/noncommercial/create"/>
</acme:form>


