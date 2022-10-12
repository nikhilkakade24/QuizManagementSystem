<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Thanks for taking quiz</h1>
	<h2>Your Score is ${score}</h2>
	<button onclick="abc()">Summary Of Quiz</button>
	<script>
		function abc() {
			location.href = "http://localhost:8080/login/user/showsummary";
		}
	</script>
</body>
</html>