<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tech Stack Portal</title>
</head>
<body>
<h1>Search by </h1>
<form action="./search" method="post">
<table>
	<tr>
		<td>Technology</td><td><input type="text" name="technologyName"></td>
	</tr>
	<tr>
		<td>Name</td><td><input type="text" name="personName"></td>
	</tr>
	<tr>
		<td>Project</td><td><input type="text" name="projName"></td>
	</tr>
</table>
<input type="submit"/>
</form>

<br/>
<br/>
<br/>
<a href="./addEmployee">Add Employee Detail</a>
</body>
</html>