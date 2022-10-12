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

	<h1>List Of Questions</h1>





	<form action="check">

		<div class="form-group">
			<label>Question Name</label> <input type="text" class="form-control"
				value="${question.title}" name="title" />
		</div>
		<br>
		<div class="form-group">
			<label>Enter Answer</label>
			<%
			int i = 1;
			%>

			<c:forEach items="${question.options}" var="options">

				<input type="radio" name="option" value="<%=i%>">
				<c:out value="${options}"></c:out>
				<%
				i = i + 1;
				%>
			</c:forEach>
		</div>


		
		<input  type="submit" class="btn btn-secondary" value="Sumit">

			
			
		

	</form>

	</table>



	<button onclick="abc()">Log Out</button>
	<script>
		function abc() {
			location.href = "http://localhost:8080/login/user/home";
		}
	</script>

</body>
</html>