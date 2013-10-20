<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<c:if test="${!ajaxRequest}">
<html>
<body>
</c:if>
	<div id="formsContent">
		<h2>Create Customer Account Form</h2>
		
        
		<form:form id="form" method="post" modelAttribute="formBean" class="form-horizontal" role="form">                 
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
						Name <form:errors path="name" />
					</form:label>
					<div class="col-lg-6">
						<form:input path="name" class="form-control" placeholder="name"/>
					</div>
				</div>	
				<div class="form-group warning">
					<form:label class="col-lg-2 control-label" path="age">
		  				Surname <form:errors path="age" />
					</form:label>
					<div class="col-lg-6">
						<form:input class="form-control" path="age" />
					</div>
				</div>
		  		<div class="form-group success">
					<form:label class="col-lg-2 control-label" path="birthDate">            
		  				Birth Date <form:errors path="birthDate"/>
					</form:label>
					<div class="col-lg-6">												 
	                    <form:input class="form-control" path="birthDate" size="16" placeholder="yyyy-mm-dd"/>	                    
					</div>
				</div>
		 		<div class="form-group">
					<form:label class="col-lg-2 control-label" path="currency">
		  				e-mail <form:errors path="currency" />
					</form:label>
					<div class="col-lg-6">
						<div class="input-prepend input-append">
							<span class="add-on"></span><form:input path="currency" class="input-medium"/><span class="add-on">.00</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<form:label class="col-lg-2 control-label" path="phone">
		  				Phone <form:errors path="phone" />
					</form:label>
					<div class="col-lg-6">
						<form:input path="phone" class="form-control input-medium" placeholder="(+30) 697-756788)"/>
					</div>
				</div>
		  		<div class="form-group">
					<form:label class="col-lg-2 control-label" path="percent">
		  				Date <form:errors path="percent" />
					</form:label>
					<div class="col-lg-6">
						<form:input path="percent" class="form-control input-small" placeholder="yyyy-mm-dd"/>
					</div>
				</div>		 
		  	</fieldset>
	
            <hr/>
            
			<fieldset>
				<legend>Details</legend>
				<div class="form-group">
					<form:label class="col-lg-2 control-label" path="inquiry">
						Type (select one)
					</form:label>
					<div class="col-lg-6">
						<form:select class="form-control" path="inquiry">
							<form:option class="form-control" value="comment">Comment</form:option>
							<form:option class="form-control" value="feedback">Feedback</form:option>
							<form:option class="form-control" value="suggestion">Suggestion</form:option>
						</form:select>
					</div>
				</div>
				<div class="form-group">
					<form:label class="col-lg-2 control-label" path="inquiryDetails"> 
		  				Details
					</form:label>
					<div class="col-lg-6">
						<form:textarea class="form-control" path="inquiryDetails" />
					</div>
				</div>						  	
		  	</fieldset>
	
			<fieldset >
				<legend>Additional Info</legend>
				<label class="checkbox inline">					
					<form:checkbox path="additionalInfo[mvc]" value="true" /> notify user by e-mail
				</label>
				<label class="checkbox inline">
					<form:checkbox path="additionalInfo[java]" value="true" /> notigy user by phone
				</label>
			</fieldset>

		  	</p>
			
			<fieldset >
				<legend>Are these information correct?</legend>
				<label class="radio">
					<form:radiobutton path="subscribeNewsletter" value="true" />
					Yes
				</label>
				<label class="radio">
					<form:radiobutton path="subscribeNewsletter" value="false" />
					No
				</label>
			</fieldset>
	        <hr/>
			<p><button type="submit" class="btn btn-primary">Submit</button></p>				
		</form:form>

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