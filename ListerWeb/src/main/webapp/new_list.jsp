<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="MainMenu" method="POST">
		<p style="text-align: center;">
			<strong>Welcome to ListerWeb - Now with 100% more Web!!</strong>
		</p>

		<p style="text-align: center; text-decoration: underline;">
			<strong>Create New List</strong>
		</p>

		<p style="text-align: center;">
			<label for="create_list_item">New List Name:</label>
			<input name="new_list_name" type="text">
		</p>

		<p style="text-align: center;">
			<input type="submit" value="Save New List" name="save_new_list"/>
		</p>

	</form>

	<p style="text-align: center;">
		<input type=button value="Back" onCLick="history.back()">
	</p>

</body>
</html>