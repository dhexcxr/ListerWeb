<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ListerWeb</title>
</head>
<body>
	<p style="text-align: center;">
		<strong>Welcome to ListerWeb - Now with 100% more Web!!</strong>
	</p>

	<form action="MainMenu" method="GET">

		<p style="text-align: center;">&nbsp;</p>
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
		</p>
		
	</form>
	
	<form action="MainMenu" method="POST">
            <input type="submit" name="show_lists" value="Show List (EMP)"> &nbsp; &nbsp;<br>
            
            
        </form>
	
</body>
</html>