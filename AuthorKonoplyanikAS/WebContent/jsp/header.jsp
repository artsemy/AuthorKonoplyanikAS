<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="interface" />
<link rel="stylesheet" href="../css/header.css">

<div class="header-bloc">
	<div class="header-left">
		<form action="ServletForJsp" method="get">
			<div>
				<input type="hidden" name="command" value="goto_main_page">
				<input type="image" src="../img/logo.png" alt="main">
			</div>
		</form>
		<div class="space">
		</div>
		<form action="ServletForJsp" method="get">
			<input type="hidden" name="command" value="do_nothing">
			<c:if test="${sessionScope.user_store != null}">
				<input class="button" type="submit" value="${sessionScope.user_store.name}">
			</c:if>
		</form>
		<form action="ServletForJsp" method="get">
			<input type="hidden" name="command" value="do_nothing">
			<c:if test="${sessionScope.user_store != null}">
				<input class="button" type="submit" value="${sessionScope.user_store.balance}">
			</c:if>
		</form>
		<div class="clear"></div>
	</div>
	<div class="header-right">
		<div class="header-locale">
			<form action="ServletForJsp" method="post">
				<input type="hidden" name="command" value="locale" >
				<input type="hidden" name="page_url" value="main.jsp"/>
				<input type="submit" name="lang_ru" value="<fmt:message key="label.ru" />" />
				<input type="submit" name="lang_en" value="<fmt:message key="label.en" />" />
			</form>
		</div>
		<div class="header-login">
			<c:if test="${sessionScope.user_store == null}">
				<form action="ServletForJsp" method="get">
					<input type="hidden" name="command" value="goto_sign_in_page">
					<input type="submit" name="goto_sign_in_page" value="<fmt:message key="label.signIn" />" />
		   		</form>
			</c:if>
			<c:if test="${sessionScope.user_store != null}">
				<form action="ServletForJsp" method="post">
					<input type="hidden" name="command" value="sign_out">
					<input type="submit" name="sign_out" value="<fmt:message key="label.signOut" />" />
				</form>
			</c:if>
		</div>
		<div class="clear"></div>
	</div>
	<div class="clear"></div>
</div>