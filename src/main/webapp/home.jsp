<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display</title>
<style>
table#nat{
	width: 50%;
	background-color: #3AC6AA;
}
</style>
</head>
<body>  
<% 
	String firstname =  request.getParameter("firstname");
	String lastname = request.getParameter("lastname");
	String userName = request.getParameter("userName");
	String pass = request.getParameter("pass");
	String addr = request.getParameter("address");
	String age = request.getParameter("age");
	
%>
<table id ="nat">
<tr>
	<td>
		<c:if test="${not empty param.error}" >
			<%= request.getParameter("error") %>
			
		</c:if>
	</td>
</tr>
<tr>
	<td>First Name</td>
	<td><%= firstname %></td>
</tr>
<tr>
	<td>Last Name</td>
	<td><%= lastname %></td>
</tr>
<tr>
	<td>User Name</td>
	<td><%= userName %></td>
</tr>
<tr>
	<td>Address</td>
	<td><%= addr %></td>
</tr>
<tr>
	<td>Age</td>
	<td><%= age %></td>
</tr>

</table>
<br>
use " <i> select * from student; </i> " in mysql client to verify it.
</body>
</html>