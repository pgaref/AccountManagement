<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@page import=" db.integration.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Account Managment</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <link href="<c:url value="/resources/css/bootstrap.min.css" />"
        rel="stylesheet"  type="text/css" />  
   <link href="<c:url value="/resources/css/main.css" />"
        rel="stylesheet"  type="text/css" />    

	<%@ include file="/WEB-INF/classes/Beans.xml" %>
  <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
  <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
  <![endif]-->

</head>
<!-- <body onload="start();make_visible_search_list();restorePersistedCheckBoxes();"> -->
<!-- <body onload="menu2();restorePersistedCheckBoxes();make_visible_search_list();make_visible_if();make_visible();">  -->
<body onload="menu2();restorePersistedCheckBoxes();"> 
    <div id="wrap">
        
        <c:import url="/WEB-INF/views/tags/navbar.jsp"/>        
        
        <div class="container">
            <div class="row">
                <div class="col-md-2">
                  <div class="well sidebar-nav">
                    <c:import url="/WEB-INF/views/tags/menu.jsp"/>  
                    
                  </div>
                </div>
                
               <div class="col-md-10">         
                    <div class="jumbotron">
                      <c:import url="/WEB-INF/views/tags/banner.jsp"/>       
                      <div class="row">
                       
                       <div class="col-md-12">
                          <div class="container">
                            
                          </div>
                          	<decorator:body />
                        
                      </div> 
                                      
                    </div><!--/col-->
                    </div>
                    
                    
                </div><!--/row-->                   
            </div><!--/col-->
          </div><!--/row-->
            
          <hr class="soften">                       
    </div>

    <c:import url="/WEB-INF/views/tags/footer.jsp"/>     
   
	<!-- Placed at the end of the document so the pages load faster -->
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.0-min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/demo.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/json2.js" />"></script> 
  <script type="text/javascript" src="<c:url value="/resources/js/date.format.js" />"></script>
</body>
</html>