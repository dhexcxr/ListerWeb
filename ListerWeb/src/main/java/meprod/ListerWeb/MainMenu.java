package meprod.ListerWeb;

import static java.lang.System.out;

import java.io.IOException;
import java.util.List;

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
	private static List<ToDoList> listerLists;

	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        HiberFunc.openDbConnection();
        listerLists = HiberFunc.getLists();
    }

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		out.println("doGet MainMenu");
		sendToMainIndex(request, response);
	}
	
	private void sendToMainIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		out.println("MainMenu.sendToMainIndex");
		out.println(listerLists.get(0).toString());
		request.setAttribute("listerLists", listerLists);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String listIndex = request.getParameter("selectedList");

		if (action.equals("create_new")) {
			RequestDispatcher rd = request.getRequestDispatcher("new_list.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (action.equals("save_new_list")) {
			String newListName = request.getParameter("new_list_name");
			ToDoList newList = new ToDoList(newListName);
			ToDoList newlySavedList = HiberFunc.saveList(newList);
			if (newlySavedList == null) {
				out.println("error saving: " + newList.toString());
			} else {
				out.println(newList.toString() + " save successful");
				listerLists.add(newlySavedList);
			}
		}
		
		if (action.equals("open")) {
			out.println("open_list_function start");
			RequestDispatcher rd = request.getRequestDispatcher("/OpenList");
			rd.forward(request, response);
			return;
		}
		
		if (action.equals("delete")) {
			out.println("MainMenu delete_list\n");

			ToDoList listToDelete = HiberFunc.getList(listIndex);

			if (listToDelete == null) {		// if something went wrong
				out.println("Database error...\n");
				out.println("listToDelete is null in delete\n");
				return;
			}
			// open list, and save again when done
			out.println("we have OPENED the listToDelete in delete!");
			out.println(listToDelete.toString());

			if (listToDelete.isListBlank()) {
				// list is empty, ok to delete
				request.setAttribute("listToDelete", listToDelete);
				
				// forward to page to confirm deletion with user
				RequestDispatcher rd = request.getRequestDispatcher("delete_list.jsp");
				rd.forward(request, response);
				return;
			}
		}
		
		if (action.equals("confirm_delete")) {
			out.println("MainMenu confirm_delete_list\n");
			
			ToDoList listToDelete = HiberFunc.getList(listIndex);

			if (listToDelete == null) {		// if something went wrong
				out.println("Database error...\n");
				out.println("listToDelete is null in confirm_delete\n");
				return;
			} else {		// open list, and save again when done
				out.println("we have OPENED the listToDelete in confirm_delete!");
				out.println(listToDelete.toString());
			}

			if (HiberFunc.deleteList(listToDelete)) {
				out.println(listToDelete.toString() + " delete list successful");
				listerLists.remove(listToDelete);
			} else {
				out.println("error deleting: " + listToDelete.toString());
			}
		}
		
		this.sendToMainIndex(request, response);
	}

	@Override
    public String getServletInfo() {
        return "Short description";
    }
}

