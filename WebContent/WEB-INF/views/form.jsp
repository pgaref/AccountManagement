<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<c:if test="${!ajaxRequest}">
<html>

<body  >

</c:if>
	<div id="formsContent" style="z-index:-1;">
		<!-- <h3>Create Customer Account Form</h3> -->
		
        
		<form:form id="form" method="post" modelAttribute="formBean" class="form-horizontal" role="form">  
		<form:errors path="exist" />               
            <c:if test="${not empty message}">
                <div id="message" class="success">${message}</div>	
            </c:if>
            <s:bind path="*">
                <c:if test="${status.error}">
                    <div id="message" class="error">Form has errors</div>
                </c:if>
            </s:bind>
			
		  	<fieldset>
		  		<legend>Personal Info</legend>
		  		
		  		<div class="form-group error">
					<form:label class="col-lg-2 control-label" path="name"> 
						*Name <form:errors path="name" />
					</form:label>
					<div class="col-lg-6">
						<form:input path="name" class="form-control" placeholder="name"/> 
					</div>
				</div>	
				
				<div class="form-group error">
					<form:label class="col-lg-2 control-label" path="domain"> 
						*Domain <form:errors path="domain" />
					</form:label>
					<div class="col-lg-6">
						<form:input path="domain" class="form-control" placeholder="domain"/>
					</div>
				</div>	
				
				
		  		<div class="form-group success">
					<form:label class="col-lg-2 control-label" path="birthDate">            
		  				*Expiration Date <form:errors path="birthDate"/>
					</form:label>
					<div class="col-lg-6">												 
	                    <form:input class="form-control" path="birthDate" size="16" placeholder="yyyy-mm-dd"/>	                    
					</div>
				</div>
					 
		  	</fieldset>
	
            <hr/>
          
	
			<fieldset >
				<legend>Additional Info</legend>
				
				Select one or two:
				<br/>
				<form:errors path="check" />
				
				 <label class="checkbox inline" id="checkit">					
					<form:checkbox id="email1" path="additionalInfo[email]" onChange= "persistCheckBox(this);" onclick="make_visible();" value="true"  /> Notify user by e-mail
				</label>
				<label class="checkbox inline" id="checkit">
					<form:checkbox id="phone1" path="additionalInfo[phone]" onChange= "persistCheckBox(this);" onclick="Javascript:make_visible();" value="true" /> Notify user by phone
				</label> 
				<!--
						EMAIL ******************************** 
				 -->
				<div class="form-group error" id="email"  >
					<form:label class="col-lg-2 control-label" path="email"> 
						*Email<form:errors path="email" />
					</form:label>
					<div class="col-lg-6">
						<form:input path="email" class="form-control" placeholder="someone@example.com"/>
					</div>
				</div>
				<!--
						PHONE ********************************** 
				 -->
				<div class="form-group" id="phone" >
					<form:label class="col-lg-2 control-label" path="phone">
		  				*Phone<form:errors path="phone" />
					</form:label>
					<div class="col-lg-6">
						<form:input path="phone" class="form-control input-medium" placeholder="030-697-7567888"/>
					</div>
				</div>
				
				
				<div class="form-group">
					<form:label class="col-lg-2 control-label" path="inquiryDetails"> 
		  				Comments
					</form:label>
					<div class="col-lg-6">
						<form:textarea class="form-control" path="inquiryDetails" />
					</div>
				</div>
				
			</fieldset>

		  	</p>
		
	        <hr/>
			<p><button type="submit" class="btn btn-primary" >Submit</button></p>				
		</form:form>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/form.js" />"></script>
		<script type="text/javascript">
		
			$(document).ready(function() {
				
			$("#form").submit(function() { 
				
				$.post($(this).attr("action"), $(this).serialize(), function(html) {
					
					$("#formsContent").replaceWith(html);
					$('html, body').animate({ scrollTop: $("#message").offset().top }, 500);
				});
				
				return false;  
				});			
			});
			  
		</script>
	</div>

    <c:if test="${!ajaxRequest}">

    </body>
</html>
</c:if>