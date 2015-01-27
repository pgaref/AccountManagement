<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<head>
  <meta charset="utf-8">
  
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link href="<c:url value="/resources/css/bootstrap.min.css" />"
        rel="stylesheet"  type="text/css" />  
   <link href="<c:url value="/resources/css/main.css" />"
        rel="stylesheet"  type="text/css" />    

  <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

</head>
  <body >  

<div id="formsContent" style="z-index:-1;">
<fieldset id="fieldset">
	<legend>Last News </legend>
	<br/>
	<input id="hide" type="hidden" value ="${notifications}" />
	<c:if test="${not empty notifications}">
	 <c:forEach  items="${notifications}" var="customer"  > 
	 
	  
	   <legend>  Expires On ${customer.how_long} Days &nbsp; 
 	  <a id="hide"  href="home?note=${customer.ID}&action=add"><img   style="position:relative;"  src="resources/img/icon_newsletter.png" /></a>
	  <a id="del"   href="home?note=${customer.ID}&action=delete"><img   style="position:relative;"  src="resources/img/delete3.png" /></a>
	 </legend>
	      <div id="expires" class="expires">
	      <input  name="id" type="hidden" value="${map.customer.ID}"/>
	      
	     <b> Name: </b>${customer.name} &nbsp;  <b>| Domain: </b>${customer.domain}   &nbsp;
	     <b>| Contact: </b> ${customer.email} &nbsp; ${customer.mobile} &nbsp; <b>| Price:</b>  ${customer.charge} &nbsp;
	     <b>| Notifications: </b> ${customer.notifications}
	     
	      </div>
	     
     	 <br/>
	     </c:forEach>  
	</c:if>
</fieldset>
</div>

  <script>
 
 	  var url = window.location.toString();
 	  if (!url.contains("home")){
 		 window.location.href=url+"home?note=all&action=add";
 	  }
 	  
 	 // window.location = url.replace("/"+"","/home?note=all&action=add");
	  var pathArray = url.split( '/' );
	  var ids=pathArray[pathArray.length-1];
	  
	  var test=ids.split('?');
	  var test_note=test[test.length-1];
	  
	  var test2=test_note.split('=');
	  var test_note1=test2[1];
	  var action=test_note1.split('&');
	  var test_action=action[0];
	  
	  var test_note2=test2[test2.length-1];
	  

	
	  if (test_action!="all"){
	  	window.location = url.replace("note="+test_action,"note=all");
	  	//window.location = url.replace("action="+test_note2,"action=add");
	  } 
	  if (test_note2!="add"){
		  window.location = url.replace("action="+test_note2,"action=add");
	  }


		
		if(document.getElementById("hide").value=="[]"){
			document.getElementById("fieldset").style.visibility="hidden";
			document.getElementById("formsContent").innerHTML="<fieldset ><legend>Empty List</legend></fieldset>";
		}



</script>




<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
 </body>
</html>