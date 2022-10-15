<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="buttonAction.js"></script>
<meta charset="ISO-8859-1">
<title>Delete List Item ${listItemToDelete.listItemName}</title>
</head>
<body>

	<form name="callServlet" action="OpenList" method="POST">
	
		<input type="hidden" name="action" id="action" value=""/>
		<input type="hidden" name="selectedListId" id="selectedListId" value=""/>
		<input type="hidden" name="selectedListItem" id="selectedListItem" value=""/>
		
		<p style="text-align: center;">
			<strong>Welcome to ListerWeb - Now with 100% more Web!!</strong>
		</p>

		<p style="text-align: center; text-decoration: underline;">
			<strong>Delete List Item?</strong>
		</p>

		<p style="text-align: center;">
			${listItemToDelete.listItemName}
		</p>

		<p style="text-align: center;">
			<input type="button" value="Delete List Item" name="confirm_delete_list_item" onClick="button('confirm_delete_list_item', ${currentList.id}, ${listItemToDelete.toDoListItemNum})">
			<input type="button" value="Back" onCLick="history.back()" />
		</p>

	</form>

</body>
</html>
