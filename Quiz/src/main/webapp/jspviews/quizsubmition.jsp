<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Quiz submitted sucessfully</h1>
	<h1>${str}</h1>
	<button onclick="abc()">Admin Dashboard</button>
	<script>
		function abc() {
			location.href = "http://localhost:8080/login/admin/admindashboard";
		}
	</script>

</body>
</html>