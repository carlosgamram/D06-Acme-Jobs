<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<jstl:if test="${command == 'create'}">
		<acme:form-hidden path="principal.id"/>
	</jstl:if>
	
	<acme:form-textbox code="sponsor.commercial.form.label.picture" path="picture"/>
	<acme:form-textbox code="sponsor.commercial.form.label.slogan" path="slogan"/>
	<acme:form-textbox code="sponsor.commercial.form.label.url" path="url"/>
	<acme:form-integer code="sponsor.commercial.form.label.creditCardMonth" 
		path="creditCardMonth" placeholder="MM"/>
	<acme:form-integer code="sponsor.commercial.form.label.creditCardYear" 
		path="creditCardYear" placeholder="YYYY"/>
	<acme:form-textbox code="sponsor.commercial.form.label.creditCardName" path="creditCardName"/>
	<acme:form-textbox code="sponsor.commercial.form.label.creditCardNumber" path="creditCardNumber"/>
	<acme:form-integer code="sponsor.commercial.form.label.creditCardCVV" 
		path="creditCardCVV" placeholder="000"/>	
	<acme:form-textbox code="sponsor.commercial.form.label.creditCardType" path="creditCardType"/>
		
  	<acme:form-submit test = "${command=='show'}" 
  		code="sponsor.commercial.form.button.update" 
  		action="/sponsor/commercial/update"/>
  		
	<acme:form-submit test = "${command=='show'}" 
  		code="sponsor.commercial.form.button.delete" 
  		action="/sponsor/commercial/delete"/>
  		
  	<acme:form-submit test = "${command=='create'}" 
  		code="sponsor.commercial.form.button.create" 
  		action="/sponsor/commercial/create"/>
  		
  	<acme:form-submit test = "${command=='update'}" 
  		code="sponsor.commercial.form.button.update" 
  		action="/sponsor/commercial/update"/>
  		
  	<acme:form-submit test = "${command=='delete'}" 
  		code="sponsor.commercial.form.button.delete" 
  		action="/sponsor/commercial/delete"/>
  		
  	<acme:form-return 
  		code="sponsor.commercial.form.button.return" />
</acme:form>
