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
		<h3>${sessionScope.drink_store.drinkMenuItem.title}</h3>
			<table>
			<c:forEach var="item" items="${sessionScope.drink_store.extra}">
					<form action="ServletForJsp" method="post">
						<input type="hidden" name="command" value="remove_ingredient">
						<tr>
							<td>${item.extraMenuItem.title}</td>
							<td><input type="submit" name="remove_ingredient" value="-"></td>
							<td><input type="hidden" name="ingredient_remove_id" value="${item.id}"></td>
						</tr>
					</form>
			</c:forEach>
			</table>
		<form action="ServletForJsp" method="post">
			<select name="ingredient_id">
				<option value="1">сахар</option>
				<option value="2">молоко</option>
				<option value="3">мороженное</option>
				<option value="4">зефир</option>
				<option value="5">корица</option>
				<option value="6">сливки</option>
				<option value="7">сироп</option>
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