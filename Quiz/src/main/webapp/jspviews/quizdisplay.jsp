
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.epam.model.Quiz"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>quizdisplay</title>
</head>
<body>

	



	<form action="getquestions">
		Select Quiz from which you want to display questions:&nbsp; <select
			name="quiz">
			<c:forEach items="${quizlist}" var="quiz">
				<option value="${quiz.id}">${quiz.getTitle()}</option>
			</c:forEach>
		</select> <br /> <br /> <input type="submit" value="Submit" />
	</form>


	

</body>
</html>