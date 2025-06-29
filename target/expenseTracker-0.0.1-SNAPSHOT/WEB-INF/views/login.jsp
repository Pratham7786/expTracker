<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Login User</h1>
	<form action="authenticate" method="post"> 
		Enter Email : <input type="text" name="email" /> <br> <br>
		Enter Password : <input type="text" name="password" /> <br> <br>
		<input type="submit" value="submit">
		<p>Create Account..<a href="/signup">SignUp</a></p>
	</form>
</body>
</html>