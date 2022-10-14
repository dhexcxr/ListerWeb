<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="buttonAction.js"></script>
<meta charset="ISO-8859-1">
<title>Create New List Item</title>
</head>
<body>

	<form name="callServlet" action="OpenList" method="POST">
	
		<input type="hidden" name="action" id="action" value=""/>
		<input type="hidden" name="selectedList" id="selectedList" value=""/>
		<input type="hidden" name="selectedListItem" id="selectedListItem" value=""/>
	
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
			<input type="button" value="Save New List Item" name="save_new_list_item" onClick="button('save_new_list_item', ${currentListId})">
		</p>

		<p style="text-align: center;">
<!-- 		<input type=button value="Back" onCLick="history.back()"> -->
		<input type=button value="Back to List Menu" name="back_to_list_menu" onClick="button('back_to_list_menu', ${currentListId})">
	</p>

	</form>

</body>
</html>
