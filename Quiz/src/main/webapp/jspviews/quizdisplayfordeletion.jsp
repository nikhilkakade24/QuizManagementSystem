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

	<form action="getquestionsfordeletion">
		Select Quiz from which you want to delete questions:&nbsp; <select
			name="quiz">
			<c:forEach items="${quizlist}" var="quiz">
				<option value="${quiz.id}">${quiz.getTitle()}</option>
			</c:forEach>
		</select> <br /> <br /> <input type="submit" value="Submit" />
	</form>

</body>
</html>