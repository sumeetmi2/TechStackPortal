<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${name}<br/>
${id} <br/>
<c:forEach items="${projects}" var="proj">
	${proj.name}<br/>
	<c:forEach items="${proj.technologies}" var = "tech">
	 	${tech}<br/>
	</c:forEach>	
</c:forEach>

</body>
</html>