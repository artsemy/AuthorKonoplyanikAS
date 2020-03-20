<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Authorization</title>
</head>
<body>
	<form action="ServletForJsp" method="post">
		<input type="hidden" name="command" value="naming" /> Введите имя:<br />
		<input type="text" name="name" value="" /><br /> Введите пароль:<br />
		<input type="text" name="pass" value="" /><br />
		<input type="submit" name="sign_up" value="Регистрация" /><br />
		<input type="submit" name="sign_in" value="Вход" />
		<input type="submit" name="sign_out" value="Выход" />
		<br />
	</form>
</body>
</html>