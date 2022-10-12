<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>questiondisplay</title>
</head>
<body>
	<form action="deletequestions" method="post">
		Select Question which you want to delete:&nbsp; <select
			name="question">
			<c:forEach items="${questionlist}" var="question">
				<option value="${question.id}">${question.title}</option>
			</c:forEach>
		</select> <br /> <br /> <input type="submit" value="Submit" />
	</form>
	


	
</body>
</html>