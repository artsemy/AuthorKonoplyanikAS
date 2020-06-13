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
			<p>${drink.drink.drinkMenuId}</p>
			<c:forEach var="ingredient" items="${drink.ingredients}">
			<p>${ingredient.portionId}</p>
			</c:forEach>
			<br>
		</c:forEach>
		<form action="ServletForJsp" method="post">
			<h1>delivery</h1>
			<select name="delivery_type">
				<option value="1">foot</option>
				<option value="2">car</option>
			</select>
			<input type="hidden" name="command" value="push_order" >
			<input type="date" name="delivery_end_date">
			<input type="time" name="delivery_end_time">
			<input type="submit" name="push_order" value="send order">
		</form>
	</div>
</body>
</html>