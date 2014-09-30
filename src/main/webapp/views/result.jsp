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

<c:if test="${empty results}">
	<div class="result-element-name">
	<h2>No results found</h2>
	</div>
</c:if>
<c:forEach items = "${results}" var="result">
	<div class="result-element-name">
		<c:forEach items = "${result.result}" var="t1">
			<c:forEach items="${t1.label}" var = "t2"> ${t2}</c:forEach>
			:  ${t1.value}		<br/>
		</c:forEach>

	</div>
	<br/>
</c:forEach>

</body>
</html>