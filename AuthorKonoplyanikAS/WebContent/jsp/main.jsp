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
<title>main</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<h1>main.jsp</h1>
	<div align="right">
		<c:if test="${sessionScope.login == null}">
			<form action="signIn.jsp">
	   			<button><fmt:message key="label.signIn" /></button>
	   		</form>
		</c:if>
		<c:if test="${sessionScope.login != null}">
			<form action="ServletForJsp" method="post">
				<input type="submit" name="sing_out" value="<fmt:message key="label.signOut" />" />
			</form>
		</c:if>
	</div>
	<p>
	session.name = ${sessionScope.login}
	</p>
</body>
</html>