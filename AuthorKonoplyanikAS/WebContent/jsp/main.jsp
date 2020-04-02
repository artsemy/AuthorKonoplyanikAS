<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>main</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<div>
		<form action="ServletForMainJsp" method="post">
			<input type="submit" name="main" value="main" />
			<input type="submit" name="sing_in" value="signIn" />
		</form>	
	</div>
</body>
</html>