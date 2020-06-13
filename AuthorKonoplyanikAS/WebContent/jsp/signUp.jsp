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
<title>signUp</title>
<link rel="stylesheet" href="../css/signUp.css">
</head>
<body>
	<h1>signUp.jsp</h1>
	<jsp:include page="header.jsp"></jsp:include>
	<input type="hidden" name="page_url" value="signUp.jsp"/>
	<br />
	<div class="bloc">
		<form action="ServletForJsp" method="post">
			<input type="hidden" name="command" value="sign_up" />
			<fmt:message key="label.insertLogin" /> <br />
			<input type="text" name="login" value="" /> <br />
			<fmt:message key="label.insertPassword" /> <br />
			<input type="text" name="password" value="" /> <br />
			<input type="submit" name="sign_up" value="<fmt:message key="label.signUp" />" /> <br />
		</form>
	</div>
</body>
</html>