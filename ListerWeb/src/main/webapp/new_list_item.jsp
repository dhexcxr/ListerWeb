<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New List Item</title>
</head>
<body>

	<form action="OpenList" method="POST">
		<p style="text-align: center;">
			<strong>Welcome to ListerWeb - Now with 100% more Web!!</strong>
		</p>

		<p style="text-align: center; text-decoration: underline;">
			<strong>Create New List Item</strong>
		</p>

		<p style="text-align: center;">
			<label for="new_list_item_name">New List Item Name:</label>
			<input name="new_list_item_name" type="text">
		</p>

		<p style="text-align: center;">
			<input type="submit" value="Save New List Item" name="save_new_list_item"/>
		</p>

	</form>

	<p style="text-align: center;">
		<input type=button value="Back" onCLick="history.back()">
	</p>

</body>
</html>
