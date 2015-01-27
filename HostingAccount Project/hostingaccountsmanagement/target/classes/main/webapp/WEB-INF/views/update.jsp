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

<!DOCTYPE html>
<html lang="en">
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.0-min.js" />"></script>
<body>
<div id="formsContent" style="z-index:-1;">
<form:form id="form" method="post"  >
<fieldset >
	<legend>Notifications </legend>
	<br/>
	
	 <c:forEach  items="${notifications}" var="customer"  > 
	 
	  
	   <legend>  Expires On ${customer.how_long} Days &nbsp; 
<%-- <a href="notifications?${customer.id}"> --%><button type="submit" name="${customer.ID}" class="btn btn-primary" ><img   style="position:relative;aligh:center;width:30%;height:30%;"  src="resources/img/icon_newsletter.png" /></button>
	 </legend>
	      <div id="expires" class="expires">
	      
	      
	     <b> Name: </b>${customer.name} &nbsp;  <b>| Domain: </b>${customer.domain}   &nbsp;
	     <b>| Contact: </b> ${customer.email} &nbsp; ${customer.mobile} &nbsp; <b>| Price:</b>  ${customer.charge} &nbsp;
	     <b>| Notifications: </b> ${customer.how_many}
	      </div>
	     <%-- <td>${customer.new_Date}</td>
	     <td><a  href="edit?id=${customer.ID}">Edit</a></td>  
     	 <td><a href="delete?id=${customer.ID}">Delete</a></td> --%>
     	 <br/>
	     </c:forEach>  
	
	 <button type="submit" id="b1" value="true"> 1 </button>
<button type="submit" id="b2" value="true"> 2 </button>

	</fieldset>
	</form:form>
	</div>


</body>
</html>