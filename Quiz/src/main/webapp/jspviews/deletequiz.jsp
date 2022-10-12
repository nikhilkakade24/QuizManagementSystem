<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>deletequiz</title>
</head>
<body>

	<form action="deletequiz">
		<label>Quiz Id</label> <input type="text" value="${id}" name="id">

		<label>Quiz Name</label> <input type="text" value="${name}"
			name="name"> <input type="submit" value="Delete Quiz">
	</form>


</body>
</html>