<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="nav nav-list">

  <li id="name"><a href="<c:url value="Javascript:change_url('customerList?sort=name');change_color('name');" />" >Users By Name</a></li>
  <li id="date"><a href="<c:url value="Javascript:change_url('customerList?sort=date');change_color('date');" />" >Users By Date</a></li>
  <li id="debtors"><a href="<c:url value="Javascript:change_url('debtors?sort=id');change_color('debtors');" />" > List of debtors</a></li>
 
  <li id ="delete"><a href="<c:url value="Javascript:change_url('history?sort=id');change_color('delete');" />"  >Deleting Users</a></li>
   <li id="back-up"><a href="<c:url value="Javascript:change_url('RevenueSummary?output=pdf');change_color('back-up');" />"  >Back-up in file </a></li> 
</ul>