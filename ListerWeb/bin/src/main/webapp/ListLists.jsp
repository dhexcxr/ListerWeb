<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<body>
	Lister Lists:
	<br>	
        <c:forEach var="toDoList" items="${listerLists}">
            ${toDoList.listName} <br>
            ${toDoList.description} <br>
            -------------------------------------<br>
        </c:forEach>
        <form action="EmployeeController" method="POST">
            Update: <input type="checkbox" id="update"> Delete: <input
			type="checkbox" id="delete"
			onclick="document.getElementById('ename').disabled=this.checked;document.getElementById('enumberupdate').disabled=this.checked;"><br><br>
            <select name="id">
                <c:forEach items="${employeeList}" var="employee">
                <option value="${employee.id}">${employee.id}</option>
                 </c:forEach>
            </select>
            
            <br>
            Update Name: <input id="ename" type="text" name="ename"><br>
            Update Number: <input id="enumberupdate" type="text"
			name="enumberupdate"> <br><br>
            <button type="submit" id="updateEmployee"
			name="updateEmployee"> Update</button> <br> <br>
            <button type="submit" id="deleteEmployee"
			name="deleteEmployee"> Delete </button>
            
            
               
       
        </form>
    </body>

</body>

</html>