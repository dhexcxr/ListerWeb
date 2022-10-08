<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${listToOpen.listName} Menu</title>
</head>
<body>

	<form action="OpenList" method="POST">
		<p style="text-align: center;">
			<strong>Welcome to ListerWeb - Now with 100% more Web!!</strong>
		</p>

		<p style="text-align: center; text-decoration: underline;">
			<strong>${listToOpen.listName} Menu</strong>
		</p>

		<p style="text-align: center;">
			<input type="submit" value="Create Lister List Item" name="create_new_list_item">
		</p>

		<table aria-label="List of a Lister List's Lister Items"
			style="height: 42px; width: 90%; border-collapse: collapse; margin-left: auto; margin-right: auto;"
			border="1">

			<thead>
				<tr style="height: 18px;">
					<th style="width: 8%; height: 18px; text-align: center;">Check Off</th>
					<th style="width: 3%; height: 18px; text-align: center;">ID</th>
					<th style="width: 21%; height: 18px; text-align: center;">List Item Name</th>
					<th style="width: 25%; height: 18px; text-align: center;">Created Date</th>
					<th style="width: 20%; height: 18px; text-align: center;">Complete</th>
					<th style="width: 25%; height: 18px; text-align: center;">Completed Date</th>
					<th style="width: 8%; height: 18px; text-align: center;">Delete</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="listItem" items="${listToOpen.listItems}">
					<tr style="height: 18px;">
						<td style="width: 8%; height: 18px; text-align: center;">
<%-- 							use some JS here: if ${listItem.done} === true { --%>
<!-- 													[display "Done" or "Checked off"] -->
<!-- 													} else { -->
<!-- 													[display the "Check off... button] " -->
<!-- 													} -->
<!-- 							then we can remove check for Done in Servlet and remote Complete column in this table -->
							<input type="submit" value="Check Off ${listItem.toDoListItemNum}" name="check_off_list_item">
						</td>
						<td style="width: 3%; height: 18px; text-align: center;">${listItem.toDoListItemNum}</td>
						<td style="width: 21%; height: 18px; text-align: center;">${listItem.listItemName}</td>
						<td style="width: 25%; height: 18px; text-align: center;">${listItem.creationDateTime}</td>
						<td style="width: 20%; height: 18px; text-align: center;">${listItem.done}</td>
						<td style="width: 25%; height: 18px; text-align: center;">${listItem.finishedDateTime}</td>
						<td style="width: 8%; height: 18px; text-align: center;">
							<input type="submit" value="Delete ${listItem.toDoListItemNum}" name="delete_list_item">
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		
		<p style="text-align: center;">
			<input type=submit value="Back to Main Menu" name="back_to_main_menu">
<!-- 			TODO make Back button always go back to main menu, not directly back
					if for instance the user just made a new list item, we don't want to go back to the New List Item screen
				also, remove openListId attribute-->
		</p>
	</form>

</body>
</html>
