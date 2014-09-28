<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="<c:url value="/resources/css/formoid-solid-green.css"/>" type="text/css" />
<head>
<title>Add Employee Info</title>
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/formoid-solid-green.js"/>"></script>
</head>
<body>
<h1>Search by </h1>

<form class="formoid-solid-green" style="background-color:#FFFFFF;font-size:14px;font-family:'Roboto',Arial,Helvetica,sans-serif;color:#34495E;max-width:480px;min-width:150px" action="./search" method="post">
<div class="title"><h2>Search</h2></div>

<div class="element-input" title="Technology">
	<label class="title">Technology</label>
	<div class="item-cont">
		<input class="large" type="text" name="technologyName"/>
		<span class="icon-place"></span>
	</div>
</div>
<div class="element-input" title="Employee Name">
	<label class="title">Name</label>
	<div class="item-cont">
		<input class="large" type="text" name="personName"/>
		<span class="icon-place"></span>
	</div>
</div>
<div class="element-input" title="Project">
	<label class="title">Project</label>
	<div class="item-cont">
		<input class="large" type="text" name="projName"/>
		<span class="icon-place"></span>
	</div>
</div>
<!-- 
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
</table> -->
<input type="submit"/>
</form>

<br/>
<br/>
<br/>
<a href="./addEmployee">Add Employee Detail</a>
</body>
</html>