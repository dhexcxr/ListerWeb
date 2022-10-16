package meprod.ListerWeb;

import static java.lang.System.out;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;

public class MainMenu extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4450333060913498431L;
	private List<ToDoList> listerLists;

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
	
	private void sendToMainIndex(HttpServletRequest request, HttpServletResponse response) {
		out.println("MainMenu.sendToMainIndex");
		out.println(listerLists.get(0).toString());
		
		// if we're returning from OpenLists get the updated listerLists from the session.attribute
		List<ToDoList> sessionListerLists = null;
		Object objectListerLists = request.getSession().getAttribute("listerLists");
		// if the object we just retrieved from the session.attribute is in fact our listerList 
		if (objectListerLists instanceof List<?> castListerLists) {
			sessionListerLists = new ArrayList<>();
			for (Object objectToDoList : castListerLists) {
				if (objectToDoList instanceof ToDoList toDoList) {
					sessionListerLists.add(toDoList);
				}
			}
			// then copy it into our local variable and remove the session.attribute
			listerLists = sessionListerLists;
			request.getSession().removeAttribute("listerLists");
		}
		
		request.setAttribute("listerLists", listerLists);
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ServletException | IOException ex) {
			goToErrorPage(ex);
		}
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		String action = request.getParameter("action");
		String listIndexId = request.getParameter("selectedListId");

		if (action.equals("create_new")) {
			goToCreateNewListPage(request, response);
		}
		
		if (action.equals("save_new_list")) {
			saveNewList(request, response);
		}
		
		if (action.equals("open")) {
			out.println("open_list_function start");
			openList(request, response);
		}
		
		if (action.equals("delete")) {
			out.println("MainMenu delete_list\n");
			goToListDeletePage(request, response, listIndexId);
		}
		
		if (action.equals("confirm_delete")) {
			out.println("MainMenu confirm_delete_list\n");
			confirmListDelete(request, response, listIndexId);
		}
		
		if (action.equals("")) {
			// reload Main Menu
			sendToMainIndex(request, response);
		}
	}

	private void goToCreateNewListPage(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher("new_list.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException ex) {
			goToErrorPage(ex);
		}
	}

	private void saveNewList(HttpServletRequest request, HttpServletResponse response) {
		String newListName = request.getParameter("new_list_name");
		ToDoList newList = new ToDoList(newListName);
		try {
			ToDoList newlySavedList = HiberFunc.saveList(newList);
			listerLists.add(newlySavedList);
		} catch (HibernateException ex) {
			goToErrorPage(ex);
		}
		sendToMainIndex(request, response);
	}

	private void openList(HttpServletRequest request, HttpServletResponse response) {
		// open selected list from listerLists, list ID is set by JavaScript called from index.jsp
		request.getSession().setAttribute("listerLists", listerLists);
		RequestDispatcher rd = request.getRequestDispatcher("/OpenList");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException ex) {
			goToErrorPage(ex);
		}
	}

	private void goToListDeletePage(HttpServletRequest request, HttpServletResponse response, String listIndexId) {
		try {
			ToDoList listToDelete = HiberFunc.getList(listIndexId);
			if (listToDelete.isListBlank()) {		// TODO probably don't need this, delete button doesn't appear unless list is blank
				// list is empty, ok to delete
				request.setAttribute("listToDelete", listToDelete);
				// forward to page to confirm deletion with user
				RequestDispatcher rd = request.getRequestDispatcher("delete_list.jsp");
				rd.forward(request, response);
			}
		} catch (HibernateException | ServletException | IOException ex) {
			goToErrorPage(ex);
		}
	}

	private void confirmListDelete(HttpServletRequest request, HttpServletResponse response, String listIndexId) {
		try {
			ToDoList listToDelete = HiberFunc.getList(listIndexId);
			HiberFunc.deleteList(listToDelete);
			listerLists.remove(listToDelete);
			this.sendToMainIndex(request, response);
		} catch (HibernateException ex) {
			goToErrorPage(ex);
		}
	}
	
	private void goToErrorPage(Exception ex) {
		// TODO make and send to error page
		if (ex instanceof HibernateException) {
			out.println("Database error...\n");
		} else if (ex instanceof ServletException || ex instanceof IOException) {
			out.println("Server error...\n");
		}
		ex.printStackTrace();
	}

	@Override
    public String getServletInfo() {
        return "Short description";
    }
}

