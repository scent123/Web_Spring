<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>An Exception Has Occurred!</title>
</head>
<body>
	<h1>An Exception Has Occurred!</h1>

	<pre>${exception.getMessage()}</pre>

	<ul>
<c:forEach var="stack" items="${exception.getStackTrace()}">
		<li>${stack}</li>
</c:forEach>
	</ul>
</body>
</html>
