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
		  		<form:input id="days_str" path="days" type="hidden" value="${map.customer.days}"/>
		  		<form:input id="months_str" path="months" type="hidden" value="${map.customer.months}"/>
		  		<form:input id="years_str" path="years" type="hidden" value="${map.customer.years}"/>
		  		
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
					<form:select class="form-control2" path="months"  onChange="Javascript:getDays()">
				      
						<form:option value="0" id="0" label="Month"/>
					    <form:option value="1" id="January" label="January"/>
						<form:option value="2" id="February" label="February"/>
						<form:option value="3" id="March" label="March"/>
						<form:option value="4" id="April" label="April"/>
						<form:option value="5" id="May" label="May"/>
						<form:option value="6" id="June" label="June"/>
						<form:option value="7" id="July" label="July"/>
						<form:option value="8" id="August" label="August"/>
						<form:option value="9" id="September" label="September"/>
						<form:option value="10" id="October" label="October"/>
						<form:option value="11" id="November" label="November"/>
						<form:option value="12" id="December" label="December"/>
					          
					</form:select>
					<form:select class="form-control2" path="days"  id="days" name='days'>
						<form:option value="0" label="Days"/> 
					</form:select>
				    
					<form:select class="form-control2" path="years" >
				        <form:option value="0" label="Year"/>
					    <form:option value="2014" label="2014"/>
						<form:option value="2015" label="2015"/>
						<form:option value="2016" label="2016"/>
						<form:option value="2017" label="2017"/>
						<form:option value="2018" label="2018"/>
						<form:option value="2019" label="2019"/>
						<form:option value="2020" label="2020"/>
						<form:option value="2021" label="2021"/>
						<form:option value="2022" label="2022"/>
						<form:option value="2023" label="2023"/>
						<form:option value="2024" label="2024"/>
						<form:option value="2025" label="2025"/>
					          
					</form:select>		 				 
	                  <%--   <form:input class="form-control" path="birthDate" size="16" value="${map.expDate}"/> --%>	                    
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
					<form:checkbox id="email1" path="check_email" onChange= "persistCheckBox(this);" onclick="make_visible();" value="true" disabled="true" /> Notify user by e-mail
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
    
    
    
    
    
    
    
    
    
    