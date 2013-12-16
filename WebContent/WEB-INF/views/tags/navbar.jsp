<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <div class="navbar navbar-inverse navbar-fixed-to">
  <div class="container">
    <div class="navbar-header">                                   
         <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        
        </div>
        <div id ="navi" class="navbar-collapse collapse">  
          <ul class="nav navbar-nav">
            <li id="t0" class="active"><a href="Javascript:change_url('home'); ">WebEasy Portal</a></li>
            <li id="t1" ><a href="Javascript:change_url('form'); ">New User</a></li>
            <!-- <li id="t2" ><a href="Javascript:change_url('delete');">Delete User</a></li> -->
            <li id="t3" ><a href="Javascript:change_url('customerList?sort=id'); ">Update User </a></li>
            <li id="t4" ><a href="Javascript:change_url('search?sort=id'); ">Search</a></li>
            <li id="t5" ><a href="Javascript:change_url('notifications?note=all&action=add'); ">Notifications</a></li>
            <!--<li id="t6" ><a href="Javascript:change_url('fileupload'); ">More</a></li>-->
          
           
          </ul>
        </div>  	      		 
  </div>
</div>
