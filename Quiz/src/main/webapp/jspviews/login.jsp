<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Welcome to Quiz Management System</h1>

	<script >
		function redirect() {
			var value = document.getElementById("options").value;
			window.location.href = value;
			return;
		}
	</script>


	<form id="form">
		<select name="options" id="options">

			<option value="http://localhost:8080/login/admin/home">Admin</option>
			<option value="http://localhost:8080/login/user/home">User</option>
		</select>
		<button class="btn" type="button" onclick="redirect()">Login As</button>
	</form>

</body>
</html>