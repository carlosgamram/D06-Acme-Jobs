<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="worker.job.list.label.title" path="title" width="20%"/>
	<acme:list-column code="worker.job.list.label.description"	path="description"	width="30%"/>
	<acme:list-column code="worker.job.list.label.salary" path="salary" width="20%"/>
	<acme:list-column code="worker.job.list.label.deadline"	path="deadline"	width="20%"/>
</acme:list>