<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ListerWeb</title>
</head>
<body>

	<form action="MainMenu" method="GET">
		<!--  -->
	</form>

	<form action="MainMenu" method="POST">
<!-- 		new section -->
		<input type="hidden" name="listerListsId" value="${listerListsId}" />
<!-- 		end new section -->
		<p style="text-align: center;">
			<strong>Welcome to ListerWeb - Now with 100% more Web!!</strong>
		</p>
		
		<p style="text-align: center; text-decoration: underline;">
			<strong>Main Menu</strong>
		</p>
		
		<p style="text-align: center;">
			<input type="submit" value="Create New Lister List" name="create_new_list">
<!-- 			<input type="button" value="Delete Lister List" name="delete_list"> -->
		</p>
		
		<table
			style="height: 42px; width: 90%; border-collapse: collapse;
			margin-left: auto; margin-right: auto;" border="1">
			
			<tbody>
				<tr style="height: 18px;">
					<td style="width: 12%; height: 18px; text-align: center;">Open</td>
					<td style="width: 5%; height: 18px; text-align: center;">ID</td>
					<td style="width: 35%; height: 18px; text-align: center;">Name</td>
					<td style="width: 36%; height: 18px; text-align: center;">Created Date</td>
					<td style="width: 12%; height: 18px; text-align: center;">Delete</td>
				</tr>

				<c:forEach var="toDoList" items="${listerLists}">
					<tr style="height: 18px;">
						<td style="width: 12%; height: 18px; text-align: center;">
							<input type="submit" value="Open ${toDoList.id}" name="open_list">
						</td>
						<td style="width: 5%; height: 18px; text-align: center;">${toDoList.id}</td>
						<td style="width: 35%; height: 18px; text-align: center;">${toDoList.listName}</td>
						<td style="width: 36%; height: 18px; text-align: center;">${toDoList.creationDateTime}</td>
						<td style="width: 12%; height: 18px; text-align: center;">
							<input type="submit" value="Delete ${toDoList.id}" name="delete_list">
						</td>
					</tr>
				</c:forEach>
			</tbody>
			
		</table>
			
	</form>

</body>
</html>