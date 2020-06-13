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
	<jsp:include page="header.jsp"></jsp:include>
	<br />
	<p>session.name = ${sessionScope.login}</p>
	<p>session.locale = ${sessionScope.locale}</p>
	<div class="bloc">
		<div class="body-menu">
			<table>
				<tr>
					<td>
						<form action="ServletForJsp" method="post">
							<input type="hidden" name="command" value="add_drink"/>
							<input type="hidden" name="coffee_id" value="1"/>
							<input class="menu-item" type="image" src="../img/coffeeCup.png" alt="coffee cup" >
						</form>
					</td>
					<td>
						<form action="ServletForJsp" method="post">
							<input type="hidden" name="command" value="add_drink"/>
							<input type="hidden" name="coffee_id" value="2"/>
							<input class="menu-item" type="image" src="../img/coffeeCup.png" alt="coffee cup" >
						</form>
					</td>
					<td>
						<form action="ServletForJsp" method="post">
							<input type="hidden" name="command" value="add_drink"/>
							<input type="hidden" name="coffee_id" value="3"/>
							<input class="menu-item" type="image" src="../img/coffeeCup.png" alt="coffee cup" >
						</form>
					</td>
					<td>
						<form action="ServletForJsp" method="post">
							<input type="hidden" name="command" value="add_drink"/>
							<input type="hidden" name="coffee_id" value="4"/>
							<input class="menu-item" type="image" src="../img/coffeeCup.png" alt="coffee cup" >
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<form action="ServletForJsp" method="post">
							<input type="hidden" name="command" value="add_drink"/>
							<input type="hidden" name="coffee_id" value="5"/>
							<input class="menu-item" type="image" src="../img/coffeeCup.png" alt="coffee cup" >
						</form>
					</td>
					<td>
						<form action="ServletForJsp" method="post">
							<input type="hidden" name="command" value="add_drink"/>
							<input type="hidden" name="coffee_id" value="6"/>
							<input class="menu-item" type="image" src="../img/coffeeCup.png" alt="coffee cup" >
						</form>
					</td>
					<td>
						<form action="ServletForJsp" method="post">
							<input type="hidden" name="command" value="add_drink"/>
							<input type="hidden" name="coffee_id" value="7"/>
							<input class="menu-item" type="image" src="../img/coffeeCup.png" alt="coffee cup" >
						</form>
					</td>
					<td></td>
				</tr>
			</table>
		</div>
		<div class="body-order">
			<h2>order</h2>
			<c:forEach var="item" items="${sessionScope.order_store.drinks}">
				<p>${item.drink.drinkMenuId}</p>
			</c:forEach>
			<form action="ServletForJsp" method="get">
				<input type="hidden" name="command" value="goto_order_page" >
				<input type="submit" name="order" value="<fmt:message key="label.order" />" >
			</form>
		</div>
		<div class="clear"></div>
	</div>
</body>
</html>