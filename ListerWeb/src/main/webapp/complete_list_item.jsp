<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="OpenList" method="POST">
		<p style="text-align: center;">
			<strong>Welcome to ListerWeb - Now with 100% more Web!!</strong>
		</p>

		<p style="text-align: center; text-decoration: underline;">
			<strong>Check Off List Item?</strong>
		</p>

		<p style="text-align: center;">
			// TODO get list name
		</p>

		<p style="text-align: center;">
			<input type="submit" value="Check Off List Item" name="confirm_check_off_list_item" />
			<input type="button" value="Back" onCLick="history.back()" />
		</p>

	</form>

	<p style="text-align: center;">
		<input type=button value="Back" onCLick="history.back()">
	</p>

</body>
</html>