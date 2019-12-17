<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.noncommercial.form.label.picture" path="picture"/>
	<acme:form-textbox code="sponsor.noncommercial.form.label.slogan" path="slogan"/>
	<acme:form-textbox code="sponsor.noncommercial.form.label.url" path="url"/>
	<acme:form-integer code="sponsor.noncommercial.form.label.jingle" path="jingle"/>
	
	
	<acme:form-submit test = "${command=='show'}" 
  		code="sponsor.noncommercial.form.button.update" 
  		action="/sponsor/noncommercial/update"/>
  		
	<acme:form-submit test = "${command=='show'}" 
  		code="sponsor.noncommercial.form.button.delete" 
  		action="/sponsor/noncommercial/delete"/>
  		
  	<acme:form-submit test = "${command=='create'}" 
  		code="sponsor.noncommercial.form.button.create" 
  		action="/sponsor/noncommercial/create"/>
  		
  	<acme:form-submit test = "${command=='update'}" 
  		code="sponsor.noncommercial.form.button.update" 
  		action="/sponsor/noncommercial/update"/>
  		
  	<acme:form-submit test = "${command=='delete'}" 
  		code="sponsor.noncommercial.form.button.delete" 
  		action="/sponsor/noncommercial/delete"/>
 
  	<acme:form-return 
  		code="sponsor.noncommercial.form.button.return"/>

</acme:form>