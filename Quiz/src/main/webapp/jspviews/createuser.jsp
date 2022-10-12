<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="registeruser" method="post">
		
		<label for="fname">First Name</label><br> <input type="text"
			id="fname" name="firstName" placeholder="enter your first name" required="true"><br>
		<label for="lname">Last Name</label><br> <input type="text"
			id="lname" name="lastName" placeholder="enter your last name" required="true">
			<br/><br/>
		<label for="uname">User Name</label><br> <input type="text"
			id="uname" name="userName" placeholder="enter your user name"><br>	
			
		<label for="pwd">Password</label><br> <input type="password"
			id="pwd" name="password" placeholder="enter your password">		
			
		<input type="submit" value="Submit"/>
	</form>
</body>
</html>