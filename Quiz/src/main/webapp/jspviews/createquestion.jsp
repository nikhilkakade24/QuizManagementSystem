<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>create question</title>
</head>
<body>
	<form action="submitquestion">
		<label for="quiztitle">Question Title</label><br> <input type="text"
			id="title" name="title" placeholder="enter question title"><br>
			
		<label for="option1">Option1</label><br> <input type="text"
			id="option1" name="options" placeholder="enter option 1 in a quiz"><br>
		<label for="option2">Option2</label><br> <input type="text"
			id="option2" name="options" placeholder="enter option 2 in a quiz"><br>
		<label for="option3">Option3</label><br> <input type="text"
			id="option3" name="options" placeholder="enter option 3 in a quiz"><br>
		<label for="option4">Option4</label><br> <input type="text"
			id="option4" name="options" placeholder="enter option 4 in a quiz"><br>
						
			
		<label for="level">Difficulty Level</label><br> <input type="text"
			id="level" name="difficultyLevel" placeholder="enter difficulty level">	<br>
		<label for="answer">Answer:</label><br> <input type="text"
			id="level" name="answer" placeholder="enter answer of question"><br>
		<label for="mark">Mark:</label><br> <input type="text"
			id="level" name="mark" placeholder="enter mark of question"><br>
			<input type="submit" value="Submit"/>
				
	</form>
</body>
</html>