<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:if test="${empty persons}">
	<h1>No results found</h1>
</c:if>
<c:forEach items = "${persons}" var="person">
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
	<br/><br/>
</c:forEach>
</body>
</html>