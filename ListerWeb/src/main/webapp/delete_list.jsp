<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete List ${listToDelete.listName}</title>
</head>
<body>

	<form action="MainMenu" method="POST">
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
			<input type="submit" value="Delete List" name="confirm_delete_list" />
			<input type="button" value="Back" onCLick="history.back()" />
		</p>

	</form>

</body>
</html>