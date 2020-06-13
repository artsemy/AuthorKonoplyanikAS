<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="interface" />
<html>
<head>
<meta charset="utf-8">
<title>error</title>
<link rel="stylesheet" href="../css/error.css">
</head>
	<body>
		<h1>error.jsp</h1>
		<jsp:include page="header.jsp"></jsp:include>
		<br />
		<div class="bloc">
			<h2><fmt:message key="label.error" /></h2>
			<p>
				<img alt="error image" src="../img/error.jpg">
			</p>
		</div>
	</body>
</html>