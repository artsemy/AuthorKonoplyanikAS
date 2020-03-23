<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>home</title>
</head>
<body>
    <c:out value="${requestScope.result}" />
	<c:out value="${sessionScope.name}" />
	<p>
		<c:forEach items="${requestScope.array}" var="element">
			<c:out value="${element}"/> <br />
		</c:forEach>
	</p>
</body>
</html>