<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>drink order</title>
<link rel="stylesheet" href="../css/drinkOrder.css">
</head>
<body>
	<h1>drinkOrder.jsp</h1>
	<jsp:include page="header.jsp"></jsp:include>
	<br />
	<div class="bloc">
		<p>drink_menu_id = ${sessionScope.drink_store.drink.drinkMenuId}</p>
		<c:forEach var="item" items="${sessionScope.drink_store.ingredients}">
			<p>${item.portionId}</p>
		</c:forEach>
		<form action="ServletForJsp" method="post">
			<select name="ingredient_id">
				<option value="1">sugar</option>
				<option value="2">lemon</option>
			</select>
			<input type="hidden" name="command" value="add_ingredient">
			<input type="submit" name="add_ingredient" value="add" />
		</form>
		<form action="ServletForJsp" method="post">
			<input type="hidden" name="command" value="add_drink_to_order">
			<input type="submit" name="add_drink_to_order" value="add drink">
		</form>
	</div>
</body>
</html>