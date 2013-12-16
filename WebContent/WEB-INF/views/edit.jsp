    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>  
    <html>  
    <head>  

    </head>  
 
<c:if test="${!ajaxRequest}">
<html>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<body  >

</c:if>

        
		<form:form id="form" method="post"   class="form-horizontal" modelAttribute="formBean">
		  <form:errors path="exist" />              
            <c:if test="${not empty message}">
                <div id="message" class="success">${message}</div>	
            </c:if>
            
		  	<fieldset>
		  		<legend>Personal Info</legend>
		  		<input name="id" type="hidden" value="${map.customer.ID}"/>
		  		
		  		<div class="form-group error">
					<form:label class="col-lg-2 control-label" path="name"> 
						*Name <form:errors path="name" />
					</form:label>
					<div class="col-lg-6">
						<form:input path="name" class="form-control" value="${map.customer.name}"/>
					</div>
				</div>
				
				 <div class="form-group error">
					<form:label class="col-lg-2 control-label" path="domain"> 
						*Domain <form:errors path="domain" />
					</form:label>
					<div class="col-lg-6">
						<form:input path="domain" class="form-control" value="${map.customer.domain}"/>
					</div>
				</div>	
				
				
		  		<div class="form-group success">
					<form:label class="col-lg-2 control-label" path="birthDate">            
		  				*Expiration Date <form:errors path="birthDate"/>
					</form:label>
					<div class="col-lg-6">												 
	                    <form:input class="form-control" path="birthDate" size="16" value="${map.customer.expDate}"/>	                    
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
					<form:checkbox id="email1" path="check_email" onChange= "persistCheckBox(this);" onclick="make_visible();" value="true"  /> Notify user by e-mail
				</label>
				<label class="checkbox inline" id="checkit">
					<form:checkbox id="phone1" path="check_phone" onChange= "persistCheckBox(this);" onclick="make_visible();" value="true" /> Notify user by phone
				</label>
				<!--
						EMAIL ******************************** 
				 -->
				<div class="form-group error" id="email"  >
					<form:label class="col-lg-2 control-label" path="email"> 
						*Email<form:errors path="email" />
					</form:label>
					<div class="col-lg-6">
						<form:input path="email" id="email_form" class="form-control" value="${map.customer.email}"/>
					</div>
				</div>
				<!--
						PHONE ********************************** 
				 -->
				<div class="form-group" id="phone" >
					<form:label class="col-lg-2 control-label" path="phone">
		  				*Phone
					</form:label>
					<div class="col-lg-6">
						<form:input path="phone" id="phone_form" class="form-control input-medium" value="${map.customer.mobile}"/>
					</div>
				</div>
				
				
				<div class="form-group">
					<form:label class="col-lg-2 control-label" path="inquiryDetails"> 
		  				Comments
					</form:label>
					  
					<div class="col-lg-6">
					
						<form:textarea class="form-control" path="inquiryDetails" value="${map.customer.comments}"/>
					</div>
				</div>
				 
			</fieldset> 

	        <hr/>
	        
			 <p><button type="submit" class="btn btn-primary"  value="customerList" >Save</button></p>			
		</form:form>
		
		
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/edit.js" />"></script>
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
    
    
    
    
    
    
    
    
    
    