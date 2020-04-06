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
<title>signIn</title>
</head>
<body>
	<h1>signIn.jsp</h1>
	<form action="ServletForJsp" method="post">
		<input type="hidden" name="command" value="naming" />
		<fmt:message key="label.insertLogin" /> <br />
		<input type="text" name="login" value="" /> <br />
		<fmt:message key="label.insertPassword" /> <br />
		<input type="text" name="password" value="" /> <br />
		<input type="submit" name="sign_in" value="<fmt:message key="label.signIn" />" /> <br />
	</form>
	<p>
		<a href="signUp.jsp">
			<fmt:message key="label.registration" />
		</a>
	</p>
	<a href="?lang=en"><fmt:message key="label.en" /></a>
	<a href="?lang=ru"><fmt:message key="label.ru" /></a>
</body>
</html>