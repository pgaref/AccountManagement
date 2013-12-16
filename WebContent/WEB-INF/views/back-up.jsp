<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
   	
</head>

  <body >

  <div id="formsContent" style="z-index:-1;">




<h2>Spring MVC</h2>

<h3>Revenue Report</h3>

<table border="1px" cellpadding="8px">
<tr>
<td>Month</td><td>Revenue</td>
</tr>

<c:forEach items="${revenueData}" var="current">
<tr>
   <td><c:out value="${current.key}" /></td>
   <td><c:out value="${current.value}" /></td>
</tr>
</c:forEach>
</table>
</div>
</body>
</html>