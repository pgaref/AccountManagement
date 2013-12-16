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
  
<%-- <c:set var="myRequestModel" value="${notifications}" scope="request" />
<jsp:include page="/WEB-INF/views/notifications.jsp">
   <jsp:param name="notifications" value="${notifications} "/>
</jsp:include> --%>
<%-- <c:import url="/WEB-INF/views/notifications.jsp"/> --%> 

    <%--  <div class="row">     
        <div class="col-md-4"> 
          <h3> 
              Click and Learn</br>
          </h3>
          <ul>
            <li> AAAAASimple Ajax @Controller </li>
            <li> Mapping Requests </li>
            <li> Obtaining Request Data </li>
            <li> Generating Responses </li>
            <li> Rendering Views </li>
            <li> Forms </li>
            <li> File Upload </li>
            <li> Validation </li>                
            <li> Exception Handling </li>
            <li> Message Converters </li>
            <li> Type Conversion </li>                
            <li> Layout Decoration (Sitemesh) </li>            
          <ul>                                
          </h3>           
        </div> 
        <div class="col-md-8"> 
          <h3> 
              Next Steps? Read the code.</br>
          </h3>    

          <ol>
            <li>Clone: <br/> <code> git clone git: </code> </li>
            <li>Build: <br/> <code> $ mvn clean install </code> </li>
            <li>Run: <br/>  <code> $ mvn jetty:run </code></li>
            <li>See: <br/>  <code>http://localhost:8080/spring-mvc-showcase/ </code> </li>                                
          </ol> 
          </h3>                             
        </div> 
     </div> --%>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
 </body>
</html>