<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>questionlibrary</title>
</head>
<body>
	
	<table>
		<caption>Question Library</caption>
		<tr>
			<th>Question Id</th>
			<th>Question Title</th>
			<th>Options</th>
			<th>DifficultyLevel</th>
			<th>Answer</th>
			<th>Mark</th>
		</tr>
		<c:forEach items="${questions}" var="question" varStatus="status">
			<tr>
				<td>${question.id}</td>
				<td>${question.title}</td>
				<td>${question.options}</td>
				<td>${question.difficultyLevel}</td>
				<td>${question.answer}</td>
				<td>${question.mark}</td>
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