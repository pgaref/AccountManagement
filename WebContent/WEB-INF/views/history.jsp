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
<!-- <div style="visibility:hidden;" id ="empty"><fieldset >
	<legend>Empty List</legend></fieldset></div> -->
<fieldset id="fieldset">
	<legend>Customer List Order by  <b><%= request.getParameter("sort") %></b></legend>
  
<table  class="table_delete">
	<tr>
	    <th><a id="id" style="color:black;text-decoration:underline" href="history?sort=id">User ID</a></th>
	    <th><a id="name" style="color:black;text-decoration:underline" href="history?sort=name">Name</a></th>
	    <th><a id="domain" style="color:black;text-decoration:underline" href="history?sort=domain">Domain</a></th>
	    <th><a id="email" style="color:black;text-decoration:underline" href="history?sort=email">Email</a></th>
	    <th><a id="phone" style="color:black;text-decoration:underline" href="history?sort=phone">Phone</a></th>
	    <th><a id="charge" style="color:black;text-decoration:underline" href="history?sort=date">Deleting Date</a></th>
	    <th><!-- <a id="charge" style="color:black;text-decoration:underline" href="history?sort=date"> -->Comments<!-- </a> --></th>
	 
	</tr>
<input id="hide" type="hidden" value ="${history}" />
<c:if test="${not empty history}">
   <c:forEach  items="${history}" var="history"  > 
	  
	    <tr class="elem">  
	    <td>${history.id}</td>  
	     <td>${history.name}</td>  
	     <td>${history.domain}</td>  
	     <td>${history.email}</td>  
	     <td>${history.mobile}</td>  
	     <td>${history.new_Date}</td>  
	     <td>${history.delComments}</td>
	   
	    </tr>  
	   </c:forEach>  
	  </c:if>

</table>
<a href="history?sort=all">Delete History</a>


	
	<script>
	 var url = window.location.toString();
	  var pathArray = url.split( '/' );
	  var ids=pathArray[pathArray.length-1];
	  
	  var test=ids.split('?');
	  var test_note=test[test.length-1];
	  
	  var test2=test_note.split('=');
	  var test_note1=test2[1];
	  
	 
	  
	if(document.getElementById("hide").value=="[]"){
		document.getElementById("fieldset").style.visibility="hidden";
		document.getElementById("formsContent").innerHTML="<fieldset ><legend>Empty List</legend></fieldset>";
		
	}
	 if (test_note1=="all"){
		
		  window.location = url.replace("sort="+test_action,"sort=id");
	  }
	
	</script>

	
</body>
</html>