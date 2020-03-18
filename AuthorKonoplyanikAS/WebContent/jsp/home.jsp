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
</body>
</html>