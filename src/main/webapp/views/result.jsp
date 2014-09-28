<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<link rel="stylesheet" href="<c:url value="/resources/css/formoid-solid-green.css"/>" type="text/css" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee Info</title>

<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.0.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/formoid-solid-green.js"/>"></script>

</head>
<body class="blurBg-false" style="background-color:#EBEBEB">

<div class="formoid-solid-green" style="max-width:621px;min-width:150px;line-height:0.14">
	<div class="title"><h2>Following results match your search criteria:</h2></div>
</div>

<c:if test="${empty persons}">
	<h1>No results found</h1>
</c:if>
<c:forEach items = "${persons}" var="person">
	<div class="result-element-name">
		<b>Name: </b>${person.name}<br/>
		<c:forEach items="${person.projects}" var="proj">
		<b>Project: </b>
		${proj.name}<br/>
		<b>Technologies: </b> 
		<c:forEach items="${proj.technologies}" var = "tech">
	 	${tech}
		</c:forEach>
		<br/>	
		</c:forEach>
	</div>
</c:forEach>

</body>
</html>