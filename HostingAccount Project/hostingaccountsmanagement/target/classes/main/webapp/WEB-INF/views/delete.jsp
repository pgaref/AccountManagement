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
<%@ page session="true" %>
<head>
<html lang="en">

</head>
<body >
<div id="formsContent" style="z-index:-1;">

<br/>
	<form:form id="form" method="post"  class="form-horizontal" modelAttribute="history">
		                
            <c:if test="${not empty message}">
                <div id="message" class="success">${message}</div>	
            </c:if>
            
		  	<fieldset >
		  		<legend>Delete User With ID:  <b><%= request.getParameter("id") %></b></legend>
		  		
		  		
		  		 <input name="id" type="hidden" value="${history.id}"/>
		  		
		  		<div class="form-group success">
		  		<form:label class="col-lg-2 control-label" path="name"> 
		  			*Name
		  		</form:label>
		  			<c:out value="${history.name}"></c:out>
		  			
				</div>
				
				 <div class="form-group success">
				 <form:label class="col-lg-2 control-label" path="domain"> 
					*Domain
				</form:label>
				 <c:out value="${history.domain}"></c:out>
				</div>	
				
				<div class="form-group success">
				<form:label class="col-lg-2 control-label" path="email"> 
						*Email
				</form:label>
				<c:out value="${history.email}"></c:out>
				</div>
				
					
				<div class="form-group success">
				<form:label class="col-lg-2 control-label" path="mobile"> 
						*Phone
				</form:label>
			    <c:out value="${history.mobile}"></c:out>
				</div>	
				
			
				<div class="form-group">
					<form:label class="col-lg-2 control-label" path="delComments"> 
		  				*Deleting Comments
					</form:label>
					<div class="col-lg-6">
						<form:textarea class="form-control" path="delComments" />
					</div>
				</div>
				<p><button type="submit" class="btn btn-primary" >Delete</button></p>	
			</fieldset>
		</form:form>

</div>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</body>
</html>