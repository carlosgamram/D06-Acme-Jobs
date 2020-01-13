<%@page language="java"
 import="acme.entities.banners.Banner" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<%
	Banner banner = (Banner) pageContext.getAttribute("banner");
%>

<acme:form-image picture="${banner.picture}" url="${banner.url}" slogan="${banner.slogan}"/>
