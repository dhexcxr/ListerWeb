<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="buttonAction.js"></script>
<meta charset="ISO-8859-1">
<title>Check Off List Item ${listItemToCheckOff.listItemName}</title>
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
			<strong>Check Off List Item?</strong>
		</p>

		<p style="text-align: center;">
			${listItemToCheckOff.listItemName}
		</p>

		<p style="text-align: center;">
			<input type="button" value="Check Off List Item" name="confirm_check_off_list_item" onClick="button('confirm_check_off_list_item', ${currentList.id}, ${listItemToCheckOff.toDoListItemNum})">
			<input type="button" value="Back" onCLick="history.back()" />
		</p>

	</form>

</body>
</html>
