<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:if test="${not empty invalidDegreeInput}">
	<h2>${invalidDegreeInput}</h2>
</c:if>

<table>
	<tr>
		<th>Path Length</th><th>Path</th>
	</tr>
<c:forEach items="${result}" var="r">
	<c:forEach items="${r.value}" var="r1">
		<tr>
			<td>${r.key}</td><td>${r1}</td>
		</tr>	
	</c:forEach>
</c:forEach>
</table>
</body>
</html>