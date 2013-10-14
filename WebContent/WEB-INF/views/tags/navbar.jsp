<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="navbar navbar-inverse navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">                                   
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">WebEasy Portal</a>
        </div>
        <div class="navbar-collapse collapse">  
          <ul class="nav navbar-nav">
            <li class="active"><a href="<c:url value="/" />">Home</a></li>
            <li><a href="<c:url value="/getstarted" />">Account</a></li>
            <li><a href="<c:url value="/errors-validations" />">Errors &amp; Database</a></li>
            <li><a href="<c:url value="/form" />">New User</a></li>
            <li><a href="<c:url value="/fileupload" />">Backup</a></li>
            <li><a href="<c:url value="/misc" />">More</a></li>
          </ul>
        </div>   			      		 
  </div>
</div>