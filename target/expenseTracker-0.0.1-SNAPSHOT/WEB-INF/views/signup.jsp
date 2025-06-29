<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>User SignUp Page</h1>
	<form method="post" action="saveuser" enctype="multipart/form-data">
		First Name : <input type="text" name="firstname" /> <br><br>
		Last Name  : <input type="text" name="lastname" /> <br><br>
		email     : <input type="text" name="email" /><br><br>
		password  : <input type="text" name="password" /> <br><br>
		gender    : <input type="radio" name="gender" value="male" />:Male <input type="radio" name="gender" value="female" />:Female <br><br>  
		ProfilePic: <input type="file" name="profilePic"/> <br><br>
		<input type="submit" value="submit">
		<p>Already a user..?<a href="/login">Login</a></p>
	</form>
</body>
</html>