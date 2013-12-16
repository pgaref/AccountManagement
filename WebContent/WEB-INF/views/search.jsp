<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
   	
</head>

  <body >
  <div id="formsContent" style="z-index:-1;">
  <fieldset>
	<legend>Searching List 
	</legend>   
  	<form:form id="form" name="form"  method="post" modelAttribute="searchBean" class="form-horizontal" role="form">  
  		<div class="form-group error">
					<form:label class="col-lg-2 control-label" path="word"> 
						 <p class="sup">Search by</p><form:errors path="word" />
					</form:label>
						<form:input path="word" id="word" class="form-control1" placeholder="searching word"/> 
					
						 <form:select class="form-control1" path="category">
						   <form:option value="all" label="All"/>All
						   <form:option value="name" label="Name"/>
						   <form:option value="domain" label="Domain"/>
						   <form:option value="email" label="Email"/>
						   <form:option value="phone" label="Phone"/>
						</form:select>
						<input name="hide" id="hide" type="hidden" value="${empty_list}"/>
					<button type="submit"  class="btn btn-primary" >Search</button>
					
		</div>	
		
	<br/>	<br/>
	
	<div id="no_result"></div>
	<fieldset id="fieldset" >
	<legend>Customer List Order by ${category} <b></b></legend>
	  
	<table class="table_delete">
		<tr>
		    <th><a id="id" style="color:black;text-decoration:underline" href="search?sort=id">User ID</a></th>
		    <th><a id="name" style="color:black;text-decoration:underline" href="search?sort=name">Name</a></th>
		    <th><a id="domain" style="color:black;text-decoration:underline" href="search?sort=domain">Domain</a></th>
		    <th><a id="email" style="color:black;text-decoration:underline" href="search?sort=email">Email</a></th>
		    <th><a id="phone" style="color:black;text-decoration:underline" href="search?sort=phone">Phone</a></th>
		    <th><a id="charge" style="color:black;text-decoration:underline" href="search?sort=charge">Charge</a></th>
		    <th><a id="charge" style="color:black;text-decoration:underline" href="search?sort=date">Exp Date</a></th>
		    <th>Edit</th>
		    <th>Delete</th>
		</tr>
		<input id="hide" type="hidden" value ="${customerList}" />
	<c:if test="${not empty customerList}">
	   <c:forEach  items="${customerList}" var="customer"  > 
		  
		    <tr class="elem">  
		     <td>${customer.ID}</td>  
		     <td>${customer.name}</td>  
		     <td>${customer.domain}</td>  
		     <td>${customer.email}</td>  
		     <td>${customer.mobile}</td>  
		     <td>${customer.charge}</td>  
		     <td>${customer.new_Date}</td>
		     <td><a  href="edit?id=${customer.ID}">Edit</a></td>  
	     	 <td><a href="delete?id=${customer.ID}">Delete</a></td>
		     
		    </tr>  
		   </c:forEach>  
	</c:if>
	</table>

	</fieldset>
		
			
	</form:form>
</fieldset>


		
 

 </div>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/search.js" />"></script>


  </body>
</html>
