<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
	<fmt:setLocale value="${param.lang}" />
	<fmt:setBundle basename="interface" />
<html>
	<head>
		<meta charset="utf-8">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>error.jsp</h1>
		<fmt:message key="label.error" />
	</body>
</html>