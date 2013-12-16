<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<ul class="nav nav-list">
 <!--  <li class="nav-header">List of Users</li> -->
  <li><a href="<c:url value="Javascript:change_url('customerList?sort=name');" />" >Users By Name</a></li>
  <li><a href="<c:url value="Javascript:change_url('customerList?sort=date');" />" >Users By Date</a></li>
  <li><a href="<c:url value="Javascript:change_url('debtors?sort=id');" />" > List of debtors</a></li>
 
  <!-- <li class="nav-header">Back-up</li> -->
  <li><a href="<c:url value="Javascript:change_url('history?sort=id');" />"  >Deleting Users</a></li>
  <%--  <li><a href="<c:url value="Javascript:change_url('/RevenueReportController/');" />" >Back-up in file </a></li>  --%>
   <li><a href="<c:url value="Javascript:change_url('back-up?output=pdf');" />" >Back-up in file </a></li> 
  <!-- <li><a href="src/db.integration/PdfRevenueReportView.java">Print Data</a></li> -->
  
  
</ul>