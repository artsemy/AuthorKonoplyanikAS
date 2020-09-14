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
<title>update order</title>
<link rel="stylesheet" href="../css/updateOrder.css">
</head>
<body>
	<h1>updateOrder.jsp</h1>
	<jsp:include page="header.jsp"></jsp:include>
	<input type="hidden" name="page_url" value="updateOrder.jsp"/>
	<br />
	<div class="bloc">
		<c:if test="${requestScope.active_order_store != null}">
		${requestScope.active_order_store.order.closeDate} ${requestScope.active_order_store.order.price}$
		<table>
			<c:forEach var="drink_store" items="${requestScope.active_order_store.drinks}">
				<tr>
					<td>
						${drink_store.drinkMenuItem.title} <br/>
						<c:forEach var="extra_store" items="${drink_store.extra}">
							${extra_store.extraMenuItem.title} <br/>
						</c:forEach>
					</td>
					<td valign="top">
						<form action="ServletForAll" method="post">
							<input type="submit" value="delete">
							<input type="hidden" name="drink_id_to_remove" value="${drink_store.id}">
							<input type="hidden" name="order_id_to_update" value="${requestScope.active_order_store.id}">
							<input type="hidden" name="command" value="update_order_remove_drink">
						</form>
					</td>
					<td valign="top">
						<form action="ServletForAll" method="post">
							<input type="submit" value="update">
							<input type="hidden" name="drink_id_to_update" value="${drink_store.id}">
							<input type="hidden" name="order_id_to_update" value="${requestScope.active_order_store.id}">
							<input type="hidden" name="command" value="goto_update_order_drink_page">
						</form>
						<br/> <br/>
					</td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</div>
</body>
</html>