package meprod.ListerWeb;

import static java.lang.System.out;

import java.io.IOException;
import java.util.List;

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
		sendToOpenList(request, response);
	}
	
	private void sendToOpenList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String listIndex = request.getParameter("selectedListId");
		
		ToDoList listToOpen = HiberFunc.getList(listIndex);
			
		if (listToOpen == null) {		// if something went wrong
			out.println("Database error...\n");
			out.println("listToOpen is null in OpenList.sendToOpenList function start\n");
			goBack(request, response);
			return;
		}
		// open list for display
		out.println("we have OPENED the listToOpen in OpenList.sendToOpenList function start!");
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
		
		// TODO check this cast
		List<ToDoList> listerLists = (List<ToDoList>) request.getSession().getAttribute("listerLists");
		
		if (listerLists == null) {
			out.println("OpenList.doPost: something went wrong on the server retreiving listerLists");
			return;
		}
		
		String action = request.getParameter("action");
		String selectedListId = request.getParameter("selectedListId");
		String selectedListItem = request.getParameter("selectedListItem");

		if (action.equals("create_new_list_item")) {
			request.setAttribute("currentListId", selectedListId);
			RequestDispatcher rd = request.getRequestDispatcher("new_list_item.jsp");
			rd.forward(request, response);
			return;
		}
		
		if (action.equals("save_new_list_item")) {
			String newListName = request.getParameter("new_list_item_name");
			ToDoList openList = HiberFunc.getList(selectedListId);
			int listIndex = listerLists.indexOf(openList);
			
			openList.addListItem(newListName);
			
			ToDoList newlySavedList = HiberFunc.saveList(openList);
			if (newlySavedList == null) {
				out.println("error saving: " + openList.toString());
			} else {
				out.println(openList.toString() + " save successful");
				listerLists.set(listIndex, newlySavedList);
				request.getSession().setAttribute("listerLists", listerLists);
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
			int openListId = Integer.parseInt(request.getParameter("selectedListId"));	// TODO change this to the list itself
			ToDoList currentOpenList = HiberFunc.getList(openListId);
			
			String listItemIndex2 = request.getParameter("selectedListItem");
			
			if (currentOpenList == null) {		// if something went wrong
				out.println("Database error...\n");
				out.println("currentOpenList is null in confirm_check_off_list_item\n");
				goBack(request, response);		// TODO do we want to return to the main menu from here, maybe make an error page that tells the user
				return;								// what happened and show a button to return to main menu, and refresh the ToDo List there
			}
			// open list, and save again when done
			out.println("we have OPENED the currentOpenList in confirm_check_off_list_item!");
			out.println(currentOpenList.toString());
			
			currentOpenList.checkOffListItem(listItemIndex2);
			
			ToDoList newlySavedList = HiberFunc.saveList(currentOpenList);
			if (newlySavedList == null) {
				out.println("error saving: " + currentOpenList.toString());
			} else {
				out.println(currentOpenList.toString() + " save successful");
//				listerLists.add(newlySavedList);
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
			
			int listIndex = listerLists.indexOf(currentOpenList);
			
			if (currentOpenList == null) {		// if something went wrong
				out.println("Database error..\n");
				out.println("currentOpenList is null in confirm_delete_list_item\n");
				goBack(request, response);		// TODO do we want to return to the main menu from here, maybe make an error page that tells the user
				return;								// what happened and show a button to return to main menu, and refresh the ToDo List there
			}
			// open list, and save again when done
			out.println("we have OPENED the currentOpenList in confirm_delete_list_item!");
			out.println(currentOpenList.toString());
			
			currentOpenList.deleteListItem(selectedListItem);
			
			ToDoList newlySavedList = HiberFunc.saveList(currentOpenList);
			if (newlySavedList == null) {
				out.println("error saving: " + currentOpenList.toString());
			} else {
				out.println(currentOpenList.toString() + " save successful");
//				listerLists.add(newlySavedList);
				listerLists.set(listIndex, newlySavedList);
				request.getSession().setAttribute("listerLists", listerLists);
			}
			this.doGet(request, response);		// TODO see if I can remove these doGets and returns, and let fallthrough to doGet at end
			return;
		}

		if (request.getParameter("back_to_main_menu") != null) {			// TODO (dont know if we're actually using this)
//			request.getSession().removeAttribute("openListId");		// remove current open list ID when we leave the List Menu
////			request.getSession().removeAttribute("listerLists");	// remove the attribute so we don't leave it sitting around on the server
//			RequestDispatcher rd = request.getRequestDispatcher("/MainMenu");
//			rd.forward(request, response);
			goBack(request, response);
			return;
		}
		
		if (action.equals("back_to_list_menu")) {
			sendToOpenList(request, response);
			return;
		}
		
		this.doGet(request, response);		// TODO get request parameter that is sent when MainMenu servlet calls this servlet from
												// open_list section, then after refactoring open_list.jsp UI setup from this.doGet() into
												// its own method, have an if (request.getParamete("[that Parameter]" != null) section in here
												// that will call that open_list.jsp UI setup method
	}
	
	private void goBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("openListId");		// remove current open list ID when we leave the List Menu
//		request.getSession().removeAttribute("listerLists");	// remove the attribute so we don't leave it sitting around on the server
		RequestDispatcher rd = request.getRequestDispatcher("/MainMenu");
		rd.forward(request, response);
	}

}
