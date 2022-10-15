<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="buttonAction.js"></script>
<meta charset="ISO-8859-1">
<title>Create New List</title>
</head>
<body>

	<form name="callServlet" action="MainMenu" method="POST">
	
		<input type="hidden" name="action" id="action" value=""/>
		<input type="hidden" name="selectedListId" id="selectedListId" value=""/>
		<input type="hidden" name="selectedListItem" id="selectedListItem" value=""/>
	
		<p style="text-align: center;">
			<strong>Welcome to ListerWeb - Now with 100% more Web!!</strong>
		</p>

		<p style="text-align: center; text-decoration: underline;">
			<strong>Create New List</strong>
		</p>

		<p style="text-align: center;">
			<label for="new_list_name">New List Name:</label>
			<input name="new_list_name" type="text">
		</p>

		<p style="text-align: center;">
			<input type="button" value="Save New List" name="save_new_list" onClick="button('save_new_list', -1)">
		</p>

	</form>

	<p style="text-align: center;">
		<input type=button value="Back" onCLick="history.back()">
	</p>

</body>
</html>
