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
<title>signIn</title>
<link rel="stylesheet" href="../css/signIn.css">
</head>
<body>
	<h1>signIn.jsp</h1>
	<jsp:include page="header.jsp"></jsp:include>
	<input type="hidden" name="page_url2" value="signIn.jsp"/>
	<br />
	<div class="bloc">
		<form action="ServletForJsp" method="post">
			<input type="hidden" name="command" value="sign_in" />
			<fmt:message key="label.insertLogin" /> <br />
			<input type="text" name="login" value="" /> <br />
			<fmt:message key="label.insertPassword" /> <br />
			<input type="text" name="password" value="" /> <br />
			<input type="submit" name="sign_in" value="<fmt:message key="label.signIn" />" /> <br />
		</form>
		<br />
		<form action="ServletForJsp" method="get">
			<input type="hidden" name="command" value="goto_registration_page" />
			<input type="submit" name="goto_registration_page" value="<fmt:message key="label.registration" />" />
		</form>
	</div>
</body>
</html>