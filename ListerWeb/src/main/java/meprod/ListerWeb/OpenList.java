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
		
		// TODO refactor this UI prep into its own method so we don't have to call this.doGet
			
		String listButton = request.getParameter("open_list");
		String listIndex = request.getParameter("selectedList");
		
		ToDoList listToOpen = HiberFunc.getList(listIndex);
			
		if (listToOpen == null) {		// if something went wrong
			out.println("Database error...\n");
			return;
		}
		// open list for display
		out.println("we have OPENED the Lsit!");
		out.println(listToOpen.toString());
		
		// save list and index to attributes so we can use them in open_list.jsp (and other places)
		request.setAttribute("listToOpen", listToOpen);
		request.getSession().setAttribute("openListId", listToOpen.getId());
		
		// forward to List Menu of the list user requested to open
		RequestDispatcher rd = request.getRequestDispatcher("open_list.jsp");
		rd.forward(request, response);
	}
	
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		out.println("OpenList.doPost");
		
		String action = request.getParameter("action");
		String selectedList = request.getParameter("selectedList");
		String selectedListItem = request.getParameter("selectedListItem");

		if (action.equals("create_new_list_item")) {
			request.setAttribute("currentListId", selectedList);
			RequestDispatcher rd = request.getRequestDispatcher("new_list_item.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (action.equals("save_new_list_item")) {
//			int openListId = (Integer) request.getSession().getAttribute("openListId");	// TODO change this to the list itself
			String newListName = request.getParameter("new_list_item_name");
			ToDoList openList = HiberFunc.getList(selectedList);
			openList.addListItem(newListName);
			if (HiberFunc.saveList(openList)) {
				out.println(openList.toString() + " save with new List Item successful");
			} else {
				out.println("error saving: " + openList.toString());
			}
			this.doGet(request, response);		// TODO see if I can remove these doGets and returns, and let fallthrough to doGet at end
			return;
		}
		
		if (action.equals("check_off")) {
			out.println("OpenList check_off_list_item function\n");

			int openListId = (Integer) request.getSession().getAttribute("openListId");	// TODO change this to the list itself
			ToDoList currentOpenList = HiberFunc.getList(openListId);
			if(currentOpenList.isListItemComplete(selectedListItem)) {
				// TODO tell user List Item is already done
					// maybe use request.out.systemWriter() or whatever
			} else {
				request.getSession().setAttribute("listItemIndex", selectedListItem);		// TODO remove this session attribute	
				request.setAttribute("listItemToCheckOff", currentOpenList.getListItem(selectedListItem));
				request.setAttribute("currentList", currentOpenList);
				RequestDispatcher rd = request.getRequestDispatcher("complete_list_item.jsp");
				rd.forward(request, response);
				return;
			}			
		}
		
		if (action.equals("confirm_check_off_list_item")) {
			int openListId = Integer.parseInt(request.getParameter("selectedList"));	// TODO change this to the list itself
			ToDoList currentOpenList = HiberFunc.getList(openListId);
			
			String listItemIndex2 = request.getParameter("selectedListItem");
			
			if (currentOpenList == null) {		// if something went wrong
				out.println("Database error...\n");
				return;
			}
			// open list, and save again when done
			out.println("we have OPENED the Lsit!");
			out.println(currentOpenList.toString());
			
			currentOpenList.checkOffListItem(listItemIndex2);
			
			if (HiberFunc.saveList(currentOpenList)) {
				out.println(currentOpenList.toString() + " save with check off List Item successful");
			} else {
				out.println("error saving: " + currentOpenList.toString());
			}
			this.doGet(request, response);		// TODO see if I can remove these doGets and returns, and let fallthrough to doGet at end
			return;
		}

		if (action.equals("delete")) {
			out.println("OpenList delete_list_item function\n");
			
			int openListId = (Integer) request.getSession().getAttribute("openListId");	// TODO change this to the list itself
			ToDoList currentOpenList = HiberFunc.getList(openListId);
			if(currentOpenList.isListItemComplete(selectedListItem)) {
				// TODO tell user List Item is complete and cannot be deleted
					// maybe use request.out.systemWriter() or whatever
			} else {
				request.getSession().setAttribute("listItemIndex", selectedListItem);		// TODO remove this session attribute
				request.setAttribute("listItemToDelete", currentOpenList.getListItem(selectedListItem));
				request.setAttribute("currentList", currentOpenList);
				RequestDispatcher rd = request.getRequestDispatcher("delete_list_item.jsp");
				rd.forward(request, response);
				return;
			}			
		}
		
		if (action.equals("confirm_delete_list_item")) {
			int openListId = (Integer) request.getSession().getAttribute("openListId");	// TODO change this to the list itself
			ToDoList currentOpenList = HiberFunc.getList(openListId);
			
			if (currentOpenList == null) {		// if something went wrong
				out.println("Database error...\n");
				return;
			}
			// open list, and save again when done
			out.println("we have OPENED the Lsit!");
			out.println(currentOpenList.toString());
			
			currentOpenList.deleteListItem(selectedListItem);
			
			if (HiberFunc.saveList(currentOpenList)) {
				out.println(currentOpenList.toString() + " save after deleting new List Item successful");
			} else {
				out.println("error saving: " + currentOpenList.toString());
			}
			this.doGet(request, response);		// TODO see if I can remove these doGets and returns, and let fallthrough to doGet at end
			return;
		}

		if (request.getParameter("back_to_main_menu") != null) {
			request.getSession().removeAttribute("openListId");		// remove current open list ID when we leave the List Menu
			RequestDispatcher rd = request.getRequestDispatcher("/MainMenu");
			rd.forward(request, response);
			return;
		}
		
		this.doGet(request, response);		// TODO get request parameter that is sent when MainMenu servlet calls this servlet from
												// open_list section, then after refactoring open_list.jsp UI setup from this.doGet() into
												// its own method, have an if (request.getParamete("[that Parameter]" != null) section in here
												// that will call that open_list.jsp UI setup method
	}

}
