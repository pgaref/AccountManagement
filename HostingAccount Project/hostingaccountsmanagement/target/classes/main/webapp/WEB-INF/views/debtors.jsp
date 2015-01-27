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
<fieldset id="fieldset">
	<legend>Debtors List Order by  <b><%= request.getParameter("sort") %></b></legend>
  
<table class="table_delete">
	<tr>
	    <th><a id="id" style="color:black;text-decoration:underline" href="debtors?sort=id">User ID</a></th>
	    <th><a id="name" style="color:black;text-decoration:underline" href="debtors?sort=name">Name</a></th>
	    <th><a id="domain" style="color:black;text-decoration:underline" href="debtors?sort=domain">Domain</a></th>
	    <th><a id="email" style="color:black;text-decoration:underline" href="debtors?sort=email">Email</a></th>
	    <th><a id="phone" style="color:black;text-decoration:underline" href="debtors?sort=phone">Phone</a></th>
	    <th><a id="charge" style="color:black;text-decoration:underline" href="debtors?sort=charge">Charge</a></th>
	    <th><a id="charge" style="color:black;text-decoration:underline" href="debtors?sort=date">Exp Date</a></th>
	    <th>Pay</th>
	    <!-- <th>Delete</th> -->
	</tr>
	<input id="hide" type="hidden" value ="${debtors}" />
<c:if test="${not empty debtors}">
   <c:forEach  items="${debtors}" var="customer"  > 
	  
	    <tr class="elem">  
	     <td>${customer.ID}</td>  
	     <td>${customer.name}</td>  
	     <td>${customer.domain}</td>  
	     <td>${customer.email}</td>  
	     <td>${customer.mobile}</td>  
	     <td>${customer.charge}</td>  
	     <td>${customer.new_Date}</td>
	     <td><a  href="pay?id=${customer.ID}">&nbsp;Pay&nbsp;</a></td>  
     	 <%-- <td><a href="delete?id=${customer.ID}">Delete</a></td> --%>
	     
	    </tr>  
	   </c:forEach>  
	</c:if>  
</table>

	</fieldset>
	</div>
	<script>
	 
	  
	if(document.getElementById("hide").value=="[]"){
		document.getElementById("fieldset").style.visibility="hidden";
		document.getElementById("formsContent").innerHTML="<fieldset ><legend>Empty List</legend></fieldset>";
		
	}
	
	</script>

	
</body>
</html>