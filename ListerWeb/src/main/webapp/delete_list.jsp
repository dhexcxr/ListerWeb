<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="buttonAction.js"></script>
<meta charset="ISO-8859-1">
<title>Delete List ${listToDelete.listName}</title>
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
			<strong>Delete List?</strong>
		</p>

		<p style="text-align: center;">
			${listToDelete.listName}
		</p>

		<p style="text-align: center;">
			<input type="button" value="Delete List" name="confirm_delete_list" onClick="button('confirm_delete', ${listToDelete.id})">
			<input type="button" value="Back" onCLick="history.back()" />
		</p>
	</form>

</body>
</html>
