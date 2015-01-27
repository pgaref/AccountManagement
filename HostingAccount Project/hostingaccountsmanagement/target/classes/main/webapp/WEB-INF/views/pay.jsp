<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@page import="java.util.List"%>
<%@page import=" java.text.ParseException" %>
<%@page import=" java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@page import=" java.util.ListIterator" %>
<%@ page session="false" %>
<head>
<html lang="en">

</head>
<body >
<div id="formsContent" style="z-index:-1;">

<br/>
	<form:form id="form" method="post"  class="form-horizontal" modelAttribute="customer">
		   <form:errors path="exist" />               
            <c:if test="${not empty message}">
                <div id="message" class="success">${message}</div>	
            </c:if>
            <s:bind path="*">
                <c:if test="${status.error}">
                    <div id="message" class="error">Form has errors</div>
                </c:if>
            </s:bind>
            
		  	<fieldset >
		  		<legend>Delete User With ID:  <b><%= request.getParameter("id") %></b></legend>
		  		
		  		
		  		 <input name="id" type="hidden" value="${customer.ID}"/>
		  		
		  		<div class="form-group success">
		  		<form:label class="col-lg-2 control-label" path="name"> 
		  			*Name
		  		</form:label>
		  			<c:out value="${customer.name}"></c:out>
		  			
				</div>
				
				 <div class="form-group success">
				 <form:label class="col-lg-2 control-label" path="domain"> 
					*Domain
				</form:label>
				 <c:out value="${customer.domain}"></c:out>
				</div>	
				
				<div class="form-group success">
				<form:label class="col-lg-2 control-label" path="email"> 
						*Email
				</form:label>
				<c:out value="${customer.email}"></c:out>
				</div>
				
					
				<div class="form-group success">
				<form:label class="col-lg-2 control-label" path="mobile"> 
						*Phone
				</form:label>
			    <c:out value="${customer.mobile}"></c:out>
				</div>	
				
				<div class="form-group success">
				<form:label class="col-lg-2 control-label" path="charge"> 
						*Charge
				</form:label>
			    <c:out value="${customer.charge}"></c:out>
				</div>
				
				
				<div class="form-group error" id="new_charge"  >
					<form:label class="col-lg-2 control-label" path="new_charge"> 
						*Payment<br/> value<form:errors path="new_charge" />
					</form:label>
					<div class="col-lg-6">
						<form:input path="new_charge" class="form-control" placeholder="€"/>
					</div>
				</div>
			
				
				<p><button type="submit" class="btn btn-primary" >Pay</button></p>	
			</fieldset>
		</form:form>

</div>


</body>
</html>