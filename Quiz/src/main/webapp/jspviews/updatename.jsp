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
	<form action="/quiz/updatedname">
	<label>Quiz Id</label>
	<input type ="text" value="${id}" name="id">
	<label>Old Name</label>
	<input type ="text" value="${name}" name="name">
	
	<label>New Name</label>
	<input type ="text"  name="newname">
	<input type = "submit" value="Submit"> 
	</form>
</body>
</html>