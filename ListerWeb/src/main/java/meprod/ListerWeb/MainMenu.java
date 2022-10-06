package meprod.ListerWeb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainMenu extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4450333060913498431L;


	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		if(request.getParameter("show_lists")!=null){
			System.out.println("dude");
//			return;
			List<ToDoList> listerLists = HiberFunc.getLists();
			System.out.println(listerLists.get(0).toString());
//			employeeList = employeeDaoImpl.showAllEmployees();
			request.setAttribute("listerLists", listerLists);
			RequestDispatcher rd = request.getRequestDispatcher("ListLists.jsp");
			rd.forward(request, response);
		}

//		if(request.getParameter("updateEmployee")!=null){
//			int id1 = Integer.parseInt(request.getParameter("id"));
//			String ename = request.getParameter("ename");
//			String enumberupdate = request.getParameter("enumberupdate");
//			employeeDaoImpl.updateEmployee(id1, ename, enumberupdate);
//
//			RequestDispatcher rd = request.getRequestDispatcher("EmployeeAdd.jsp");
//			rd.forward(request, response);

//		}

//		if(request.getParameter("deleteEmployee")!=null){
//			int id2 = Integer.parseInt(request.getParameter("id"));
//			employee.setId(id2);
//			employeeDaoImpl.deleteEmployee(employee);
//			RequestDispatcher rd = request.getRequestDispatcher("EmployeeAdd.jsp");
//			rd.forward(request, response);
//		}
		
	}

	
	@Override
    public String getServletInfo() {
        return "Short description";
    }
	
}

