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
	background-color: #c48ec5;
}
</style>
</head>
<body>  
<% 
	String name =  request.getParameter("fullname");
	String userName = request.getParameter("userName");
	String pass = request.getParameter("pass");
	String addr = request.getParameter("address");
	String age = request.getParameter("age");
	
%>
<table id ="nat">
<tr>
	<td>Full Name</td>
	<td><%= name %></td>
</tr>
<tr>
	<td>User Name</td>
	<td><%= userName %></td>
</tr>
<tr>
	<td>Address</td>
	<td><%= addr %></td>
</tr>

</table>
<br>
use " <i> select * from student; </i> " in mysql client to verify it.
</body>
</html>