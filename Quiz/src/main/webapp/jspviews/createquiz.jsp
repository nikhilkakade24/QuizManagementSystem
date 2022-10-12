<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="submitquiz">
		
		<label for="quiztitle">Quiz Title</label><br> <input type="text"
			id="title" name="title" placeholder="enter quiz name"><br>
		<label for="lname">Number Of Questions in a Quiz:</label><br> <input type="text"
			id="number" name="number" placeholder="enter number of questions in a quiz">
			<br/><br/>
		<label for="marks">Total marks in a Quiz:</label><br> <input type="text"
			id="marks" name="totalMarks" placeholder="enter total marks in a quiz">	
			
		<input type="submit" value="Submit"/>
	</form>

</body>
</html>