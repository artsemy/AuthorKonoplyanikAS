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
		<title>Insert title here</title>
	</head>
	<body>
		<h1>error.jsp</h1>
		<fmt:message key="label.error" />
		<form action="ServletForJsp" method="post">
			<input type="hidden" name="locale">
			<input type="hidden" name="page_url" value="error.jsp"/>
			<input type="submit" name="lang_ru" value="<fmt:message key="label.ru" />" />
			<input type="submit" name="lang_en" value="<fmt:message key="label.en" />" />
		</form>
		<form action="main.jsp">
    		<button>main</button>
    	</form>
	</body>
</html>