package meprod.ListerWeb;

import static java.lang.System.out;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OpenList extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3313388714164023579L;
	
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		out.println("OpenList.doGet function\n");
			
		String listButton = request.getParameter("open_list");
		String listIndex = null;
		
		if (listButton != null) {
//			int listIndex = Integer.parseInt(listButton.substring(listButton.lastIndexOf(' ') + 1));
			listIndex = listButton.substring(listButton.lastIndexOf(' ') + 1);
		} else {
			listIndex = Integer.toString((int) request.getSession().getAttribute("openListId"));
		}

		ToDoList listToOpen = HiberFunc.getList(listIndex);
			
		if (listToOpen == null) {		// if something went wrong
			out.println("Database error...\n");
			return;
		} else {		// open list, and save again when done
			out.println("we have OPENED the Lsit!");
			out.println(listToOpen.toString());
//			listToOpen.openList();
//			HiberFunc.saveList(listToOpen);
		}

		request.setAttribute("listToOpen", listToOpen);
		request.getSession().setAttribute("openListId", listToOpen.getId());

		RequestDispatcher rd = request.getRequestDispatcher("open_list.jsp");
		rd.forward(request, response);
		
	}
	
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		out.println("OpenList.doPost");
		
		if (request.getParameter("create_new_list_item") != null) {
//			String openListId = (String) request.getSession().getAttribute("openListId");
			RequestDispatcher rd = request.getRequestDispatcher("new_list_item.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (request.getParameter("save_new_list_item") != null) {
			// TODO stuff to save new list
			int openListId = (Integer) request.getSession().getAttribute("openListId");	// TODO change this to the list itself
			String newListName = request.getParameter("new_list_item_name");
			ToDoList openList = HiberFunc.getList(openListId);
			openList.addListItem(newListName);
			if (HiberFunc.saveList(openList)) {
				out.println(openList.toString() + " save with new List Item successful");
			} else {
				out.println("error saving: " + openList.toString());
			}
			this.doGet(request, response);
			return;
		}
		
		if (request.getParameter("back_to_main_menu") != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/MainMenu");
			rd.forward(request, response);
			return;
		}
		
		this.doGet(request, response);
	}

}
