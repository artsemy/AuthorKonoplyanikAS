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
<title>update order drink</title>
<link rel="stylesheet" href="../css/updateOrderDrink.css">
</head>
<body>
	<h1>updateOrderDrink.jsp</h1>
	<jsp:include page="header.jsp"></jsp:include>
	<input type="hidden" name="page_url" value="updateOrderDrink.jsp"/>
	<br />
	<div class="bloc">
		<c:if test="${requestScope.drink_store_to_update != null}">
		<table>
			<tr>
				<td>${requestScope.drink_store_to_update.drinkMenuItem.title}</td>
				<td>${requestScope.drink_store_to_update.drinkMenuItem.price}$</td>
				<td>
					<form action="ServletForAll" method="post">
						<input type="submit" value="delete">
						<input type="hidden" name="drink_id_to_remove" value="${requestScope.drink_store_to_update.id}">
						<input type="hidden" name="order_id_to_update" value="${requestScope.order_id_to_update}">
						<input type="hidden" name="command" value="update_order_remove_drink">
					</form>
				</td>
			</tr>
			<c:forEach var="extra" items="${requestScope.drink_store_to_update.extra}">
				<tr>
					<td>${extra.extraMenuItem.title}</td>
					<td>${extra.extraMenuItem.price}$</td>
					<td>
						<form action="ServletForAll" method="post">
							<input type="submit" value="delete">
							<input type="hidden" name="extra_id_to_remove" value="${extra.id}">
							<input type="hidden" name="drink_id_to_update" value="${requestScope.drink_store_to_update.id}">
							<input type="hidden" name="command" value="update_order_remove_extra">
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>