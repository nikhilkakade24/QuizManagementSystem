<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>questionlist</title>
</head>
<body>
	<h1>List Of Questions After Delete</h1>

	<table>
		<caption>Question Information</caption>
		<tr>
			<th>Question Id</th>
			<th>Question Title</th>

		</tr>
		<c:forEach items="${questions}" var="q" varStatus="status">
			<tr>
				<td>${q.id}</td>
				<td>${q.title}</td>
			</tr>
		</c:forEach>



	</table>


	<button onclick="abc()">Admin Dashboard</button>
	<script>
		function abc() {
			location.href = "http://localhost:8080/login/admin/admindashboard";
		}
	</script>
</body>
</html>