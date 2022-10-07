package meprod.ListerWeb;

import static java.lang.System.out;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

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
		out.println("doGet MainMenu");

		List<ToDoList> listerLists = HiberFunc.getLists();
		out.println(listerLists.get(0).toString());
		request.setAttribute("listerLists", listerLists);
		
		// new section
		String listerListsId = UUID.randomUUID().toString();
		request.getSession().setAttribute(listerListsId, listerLists);
		request.setAttribute("listerListsId", listerListsId);
		request.getRequestDispatcher("index.jsp").forward(request, response);		
		// end new section
		
//		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
//		rd.forward(request, response);
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		if(request.getParameter("open_list")!=null){
			out.println("open_list_function start");
			
			String listButton = request.getParameter("open_list");
//			int listIndex = Integer.parseInt(listButton.substring(listButton.lastIndexOf(' ') + 1));
			String listIndex = listButton.substring(listButton.lastIndexOf(' ') + 1);
			ToDoList listToOpen = HiberFunc.getList(listIndex);
			
			if (listToOpen == null) {		// if something went wrong
				out.println("Database error...\n");
			} else {		// open list, and save again when done
				out.println("we have OPENED the Lsit!");
				out.println(listToOpen.toString());
//				listToOpen.openList();
//				HiberFunc.saveList(listToOpen);
			}

			request.setAttribute("listToOpen", listToOpen);

			RequestDispatcher rd = request.getRequestDispatcher("open_list.jsp");
			rd.forward(request, response);
		}

		if(request.getParameter("show_lists")!=null){
			System.out.println("show list func start");
			
			List<ToDoList> listerLists = HiberFunc.getLists();
			if (listerLists == null) {
				out.println("Database error...\n");
			} else {
				System.out.println("showList func - List of Lists: " + listerLists.get(0).toString());
				request.setAttribute("listerLists", listerLists);
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}
		}
		
		if (request.getParameter("create_list") != null) {
			
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

