<%@tag language="java" body-content="empty" import="acme.framework.helpers.MessageHelper"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%@attribute name="picture" required="true" type="java.lang.String"%>
<%@attribute name="url" required="true" type="java.lang.String"%>
<%@attribute name="slogan" required="true" type="java.lang.String"%>


<div class="rounded">
	<a href="${url}" target="_blank"><img src="${picture}" title="${slogan}" align="top" class="img-fluid rounded" /></a>
</div>