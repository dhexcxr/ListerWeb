<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="buttonAction.js"></script>
<meta charset="ISO-8859-1">
<title>ListerWeb</title>
</head>
<body>

	<form name="callServlet" action="MainMenu" method="POST">
	
	<input type="hidden" name="action" id="action" value=""/>
	<input type="hidden" name="selectedList" id="selectedList" value=""/>
	<input type="hidden" name="selectedListItem" id="selectedListItem" value=""/>

<!-- 		new section, this is as of yet, unused -->
		<input type="hidden" name="listerListsId" value="${listerListsId}" />
<!-- 		end new section -->

		<p style="text-align: center;">
			<strong>Welcome to ListerWeb - Now with 100% more Web!!</strong>
		</p>
		
		<p style="text-align: center; text-decoration: underline;">
			<strong>Main Menu</strong>
		</p>
		
		<p style="text-align: center;">
			<input type="button" value="Create New Lister List" name="create_new_list" onClick="button('create_new', -1)">
		</p>
		
		<table aria-label="List of Lister Lists"
			style="height: 42px; width: 90%; border-collapse: collapse;
			margin-left: auto; margin-right: auto;" border="1">
			
			<thead>
				<tr style="height: 18px;">
					<th style="width: 12%; height: 18px;">Open</th>
					<th style="width: 40%; height: 18px;">Name</th>
					<th style="width: 36%; height: 18px;">Created Date</th>
					<th style="width: 12%; height: 18px;">Delete</th>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach var="toDoList" items="${listerLists}">
					<tr style="height: 18px;">
						<td style="width: 12%; height: 18px; text-align: center;">
						<input type="button" value="Open" name="open_list" onClick="button('open', ${toDoList.id})">
						</td>
						<td style="width: 40%; height: 18px; text-align: center;">${toDoList.listName}</td>
						<td style="width: 36%; height: 18px; text-align: center;">${toDoList.creationDateTime}</td>
						<td style="width: 12%; height: 18px; text-align: center;">
							<c:if test="${toDoList.isListBlank()=='true'}">
        						<input type="button" value="Delete" name="delete_list" onClick="button('delete', ${toDoList.id})">
        					</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>

</body>
</html>
