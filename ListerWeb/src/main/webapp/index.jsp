<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ListerWeb</title>
</head>
<body>
<%-- 	<jsp:include page="/MainMenu" /> --%>

	<p style="text-align: center;">
		<strong>Welcome to ListerWeb - Now with 100% more Web!!</strong>
	</p>

	<form action="MainMenu" method="GET">

<p style="text-align: center;"><strong>Welcome to ListerWeb - Now with 100% more Web!!</strong></p>
<p style="text-align: center; text-decoration: underline;"><strong>Main Menu</strong></p>
<p style="text-align: center;">&lt;Create&gt;&lt;Delete&gt;&lt;Open&gt;&lt;List&gt;</p>
<table style="height: 42px; width: 80.4836%; border-collapse: collapse; margin-left: auto; margin-right: auto;" border="1">
<tbody>
<tr style="height: 18px;">
<td style="width: 14.8718%; height: 18px; text-align: left;">ID</td>
<td style="width: 41.5384%; height: 18px;">Name</td>
<td style="width: 43.5897%; height: 18px;">Created Date</td>
</tr>

<c:forEach var="toDoList" items="${listerLists}">
            ${toDoList.description} <br>
<tr style="height: 18px;">
<td style="width: 14.8718%; height: 18px;">${toDoList.id}</td>
<td style="width: 41.5384%; height: 18px;">${toDoList.listName}</td>
<td style="width: 43.5897%; height: 18px;">${toDoList.creationDateTime}</td>
</tr>
        </c:forEach>


</tbody>
</table>

		<!-- <p style="text-align: center;">&nbsp;</p>
		<p style="text-align: center; text-decoration: underline;">
			<strong>Main Menu</strong>
		</p>
		<p style="text-align: center;">
			<input type="button" value="Create Lister List" name="create_list">
		</p>
		<p style="text-align: center;">
			<input type="button" value="Delete Lister List" name="delete_list">
		</p>
		<p style="text-align: center;">
			<input type="button" value="Open Lister List" name="open_list">
		</p>
		<p style="text-align: center;">
			<input type="button" value="Show Lister Lists" name="show_lists">
		</p> -->
		
	</form>
	
	<form action="MainMenu" method="POST">
            <input type="submit" name="show_lists" value="Show List (EMP)"> &nbsp; &nbsp;<br>
            
            
        </form>
	
</body>
</html>