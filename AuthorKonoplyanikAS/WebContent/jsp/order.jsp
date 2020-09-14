<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>order</title>
<link rel="stylesheet" href="../css/order.css">
</head>
<body>
	<h1>order.jsp</h1>
	<jsp:include page="header.jsp"></jsp:include>
	<br />
	<div class="bloc">
		<c:forEach var="drink" items="${sessionScope.order_store.drinks}">
			<form action="ServletForAll" method="post">
				<input type="hidden" name="command" value="remove_drink">
				<p>
					${drink.drinkMenuItem.title} ${drink.drinkMenuItem.price}$
					<input type="submit" name="remove_drink" value="-">
					<input type="hidden" name="drink_remove_id" value="${drink.id}">
				</p>
				<c:forEach var="ingredient" items="${drink.extra}">
				<p>${ingredient.extraMenuItem.title} ${ingredient.extraMenuItem.price}$</p>
				</c:forEach>
			</form>
			<br>
		</c:forEach>
		<h3>total:${requestScope.price}$</h3>
		<form action="ServletForAll" method="post">
			<h1>delivery</h1>
			<select name="delivery_type">
				<option value="1">foot</option>
				<option value="2">car</option>
			</select>
			<input type="hidden" name="command" value="push_order" >
			<input type="datetime-local" name="delivery_end_date_time">
			<input type="submit" name="push_order" value="send order">
		</form>
	</div>
</body>
</html>