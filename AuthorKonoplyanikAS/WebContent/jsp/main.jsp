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
<title>main</title>
<link rel="stylesheet" href="../css/main.css">
</head>
<body>
	<h1>main.jsp</h1>
	<div class="header-bgc">
		<div class="header-logo">
			<form action="main.jsp">
				<input class="logo" type="image" src="../img/logo.png" alt="main">
			</form>
		</div>
		<div class="header-login">
			<c:if test="${sessionScope.login == null}">
				<form action="signIn.jsp">
		   			<button class="header-btn"><fmt:message key="label.signIn" /></button>
		   		</form>
			</c:if>
			<c:if test="${sessionScope.login != null}">
				<form action="ServletForJsp" method="post">
					<input class="header-btn" type="submit" name="sign_out" value="<fmt:message key="label.signOut" />" />
				</form>
			</c:if>
		</div>
	</div>
	<div class="clear"></div>
	<p>session.name = ${sessionScope.login}</p>
	<p>session.locale = ${sessionScope.locale}</p>
	<form action="ServletForJsp" method="post">
		<input type="hidden" name="locale">
		<input type="hidden" name="page_url" value="main.jsp"/>
		<input type="submit" name="lang_ru" value="<fmt:message key="label.ru" />" />
		<input type="submit" name="lang_en" value="<fmt:message key="label.en" />" />
	</form>
	<div>
		<table>
			<tr>
				<td>
					<form action="ServletForJsp" method="post">
						<input type="hidden" name="add_drink" value="add_drink"/>
						<input type="hidden" name="coffee_id" value="1"/>
						<input type="image" src="../img/coffeeCup.png" alt="coffee cup" >
					</form>
				</td>
				<td>
					<form action="ServletForJsp" method="post">
						<input type="hidden" name="add_drink" value="add_drink"/>
						<input type="hidden" name="coffee_id" value="2"/>
						<input type="image" src="../img/coffeeCup.png" alt="coffee cup" >
					</form>
				</td>
				<td>
					<form action="ServletForJsp" method="post">
						<input type="hidden" name="add_drink" value="add_drink"/>
						<input type="hidden" name="coffee_id" value="3"/>
						<input type="image" src="../img/coffeeCup.png" alt="coffee cup" >
					</form>
				</td><td>
					<form action="ServletForJsp" method="post">
						<input type="hidden" name="add_drink" value="add_drink"/>
						<input type="hidden" name="coffee_id" value="4"/>
						<input type="image" src="../img/coffeeCup.png" alt="coffee cup" >
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<form action="ServletForJsp" method="post">
						<input type="hidden" name="add_drink" value="add_drink"/>
						<input type="hidden" name="coffee_id" value="5"/>
						<input type="image" src="../img/coffeeCup.png" alt="coffee cup" >
					</form>
				</td>
				<td>
					<form action="ServletForJsp" method="post">
						<input type="hidden" name="add_drink" value="add_drink"/>
						<input type="hidden" name="coffee_id" value="6"/>
						<input type="image" src="../img/coffeeCup.png" alt="coffee cup" >
					</form>
				</td>
				<td>
					<form action="ServletForJsp" method="post">
						<input type="hidden" name="add_drink" value="add_drink"/>
						<input type="hidden" name="coffee_id" value="7"/>
						<input type="image" src="../img/coffeeCup.png" alt="coffee cup" >
					</form>
				</td>
				<td></td>
			</tr>
		</table>
	</div>
</body>
</html>