<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>submit</title>
</head>
<body>
	<h1>Welcome ${username}</h1>

	<script>
		function redirect() {
			var value = document.getElementById("options").value;
			window.location.href = value;
			return;
		}
		function redirect1() {
			var value = document.getElementById("button").value;
			window.location.href = value;
			return;
		}
	</script>
	<form id="form">
		<select name="options" id="options">

			<option value="http://localhost:8080/quiz/create">Create
				Quiz</option>
			<option value="http://localhost:8080/quiz/quizlist">Update
				Quiz</option>
			<option value="http://localhost:8080/quiz/quizlist">Delete
				Quiz</option>

			<option value="http://localhost:8080/quiz/quizlist">Display
				All Quiz</option>
			<option value="http://localhost:8080/quiz/getquiz">Display
				questions in a quiz</option>
			<option value="http://localhost:8080/quiz/getquizfordeletion">Delete
				Questions in a quiz</option>
			<option value="http://localhost:8080/question/questionlibrary">Get
				Questions Library</option>


		</select>
		<button class="btn" type="button" onclick="redirect()">Choose
			Options</button>
	</form>



	<button onclick="location.href='http://localhost:8080/login' ">Log
		Out</button>


</body>
</html>