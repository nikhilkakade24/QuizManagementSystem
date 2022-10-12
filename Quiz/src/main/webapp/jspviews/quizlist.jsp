
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
	<h1>List Of Quiz</h1>
	<table> <caption>Quiz Information</caption>
		<tr>
			<th>Quiz Id</th>
			<th>Quiz Title</th>
			<th>Update Name</th>
			<th>Delete Quiz</th>
		</tr>
		<c:forEach items="${quiz}" var="q" varStatus="status">
			<tr>
				<td>${q.id}</td>
				<td>${q.title}</td>
				<td><a href="/quiz/changename?id=${q.id}">Update Name</a>
				<td><a href="/quiz/displayquizfordeletion?id=${q.id} ">Delete Quiz</a>
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