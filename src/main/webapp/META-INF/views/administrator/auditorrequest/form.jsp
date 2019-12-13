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
	<acme:form-textbox readonly="true" code="administrator.auditorrequest.form.lable.firm" path="firm"/>
	<acme:form-textbox readonly="true" code="administrator.auditorrequest.form.lable.responsibility" path="responsibility"/>
	<acme:form-textbox readonly="true" code="administrator.auditorrequest.form.lable.authenticated.userAccount.Username" path="authenticated.userAccount.Username"/>
	
	<acme:form-select code="administrator.auditorrequest.form.lable.selection" path="status" readonly="false">
		<acme:form-option code="administrator.auditorrequest.form.lable.selection.accept" value="true"/>
		<acme:form-option code="administrator.auditorrequest.form.lable.selection.reject" value="false"/>
	</acme:form-select>
	
	<acme:form-submit 
		code="administrator.auditorrequest.form.button.update" 
		action="/administrator/auditorrequest/update"/>
  		
  	<acme:form-return 
  		code="administrator.auditorrequest.form.button.return"
  		action="/administrator/auditorrequest/list"/>
</acme:form>
