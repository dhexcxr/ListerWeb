package meprod.ListerWeb;

import static java.lang.System.out;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainMenu extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4450333060913498431L;

	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        // TODO maybe do database connection setup here, or load listerLists
        
        // TODO make loading screen for inital load
    }

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		out.println("doGet MainMenu");

		List<ToDoList> listerLists = HiberFunc.getLists();
		out.println(listerLists.get(0).toString());
		request.setAttribute("listerLists", listerLists);
		
		// new section
		// TODO check if this has already been stored and place it in the same place so
			// we don't create 30,000 copies of this on the server
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
		
		if (request.getParameter("create_new_list") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("new_list.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (request.getParameter("save_new_list") != null) {
			// TODO stuff to save new list
			String newListName = request.getParameter("new_list_name");
			ToDoList newList = new ToDoList(newListName);
			if (HiberFunc.saveList(newList)) {
				out.println(newList.toString() + " save successful");
			} else {
				out.println("error saving: " + newList.toString());
			}
			this.doGet(request, response);
			return;
		}

		if (request.getParameter("open_list") != null){
			out.println("open_list_function start");
			
			RequestDispatcher rd = request.getRequestDispatcher("/OpenList");
			rd.forward(request, response);
			return;
		}

		if (request.getParameter("show_lists") != null){
			// TODO i dont think this is being called from anywhere
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
			return;
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
		this.doGet(request, response);
	}

	
	@Override
    public String getServletInfo() {
        return "Short description";
    }
	
}

