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

	<form action="MainMenu" method="POST"> <!-- maybe make this a different action  -->
		<p style="text-align: center;">
			<strong>Welcome to ListerWeb - Now with 100% more Web!!</strong>
		</p>

		<p style="text-align: center; text-decoration: underline;">
			<strong>${listToOpen.listName} Menu</strong>
		</p>

		<p style="text-align: center;">
			<input type="button" value="Create Lister List Item" name="create_list_item">
<!-- 			<input type="button" value="Delete Lister List Item" name="delete_list_item"> -->
		</p>

		<table
			style="height: 42px; width: 90%; border-collapse: collapse; margin-left: auto; margin-right: auto;"
			border="1">

			<tbody>
				<tr style="height: 18px;">
					<td style="width: 8%; height: 18px; text-align: center;">Check Off</td>
					<td style="width: 3%; height: 18px; text-align: center;">ID</td>
					<td style="width: 21%; height: 18px; text-align: center;">List Item Name</td>
					<td style="width: 25%; height: 18px; text-align: center;">Created Date</td>
					<td style="width: 20%; height: 18px; text-align: center;">Complete</td>
					<td style="width: 25%; height: 18px; text-align: center;">Completed Date</td>
					<td style="width: 8%; height: 18px; text-align: center;">Delete</td>
				</tr>

				<c:forEach var="listItem" items="${listToOpen.listItems}">
					<tr style="height: 18px;">
						<td style="width: 8%; height: 18px; text-align: center;">
							<input type="submit" value="Check Off ${listItem.id}" name="check_off_list_item">
						</td>
						<td style="width: 3%; height: 18px; text-align: center;">${listItem.id}</td>
						<td style="width: 21%; height: 18px; text-align: center;">${listItem.listItemName}</td>
						<td style="width: 25%; height: 18px; text-align: center;">${listItem.creationDateTime}</td>
						<td style="width: 20%; height: 18px; text-align: center;">${listItem.done}</td>
						<td style="width: 25%; height: 18px; text-align: center;">${listItem.finishedDateTime}</td>
						<td style="width: 8%; height: 18px; text-align: center;">
							<input type="button" value="Delete ${listItem.id}" name="delete_list_item">
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	</form>

</body>
</html>