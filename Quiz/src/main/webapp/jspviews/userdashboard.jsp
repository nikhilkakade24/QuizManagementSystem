<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome ${username} </h1>
	
	<button onclick="abc1()">Take a Quiz</button>
	
	<button onclick="abc()">Log Out</button>
	<script>
		function abc() {
			location.href = "http://localhost:8080/login";
		}
		
		function abc1()
		{
			location.href = "http://localhost:8080/login/user/takequiz";	
		}
	</script>
</body>
</html>