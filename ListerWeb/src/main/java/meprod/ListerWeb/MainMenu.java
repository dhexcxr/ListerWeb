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
		
		// TODO refactor this UI prep into its own method so we don't have to call this.doGet

		List<ToDoList> listerLists = HiberFunc.getLists();
		out.println(listerLists.get(0).toString());
		request.setAttribute("listerLists", listerLists);
		
		// new section
		// TODO as of right now, I don't think this is being used anywhere
		// TODO check if this has already been stored and place it in the same place so
			// we don't create 30,000 copies of this on the server
		String listerListsId = UUID.randomUUID().toString();
		request.getSession().setAttribute(listerListsId, listerLists);
		request.setAttribute("listerListsId", listerListsId);
		request.getRequestDispatcher("index.jsp").forward(request, response);		
		// end new section
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
			String newListName = request.getParameter("new_list_name");
			ToDoList newList = new ToDoList(newListName);
			if (HiberFunc.saveList(newList)) {
				out.println(newList.toString() + " save successful");
			} else {
				out.println("error saving: " + newList.toString());
			}
			this.doGet(request, response);		// TODO see if I can remove these doGets and returns, and let fallthrough to doGet at end
			return;
		}

		if (request.getParameter("open_list") != null) {
			out.println("open_list_function start");
			RequestDispatcher rd = request.getRequestDispatcher("/OpenList");
			rd.forward(request, response);
			return;
		}
		
		if (request.getParameter("delete_list") != null) {
			out.println("MainMenu delete_list\n");
				
			String listButton = request.getParameter("delete_list");
			String listIndex = listButton.substring(listButton.lastIndexOf(' ') + 1);

			ToDoList listToDelete = HiberFunc.getList(listIndex);

			if (listToDelete == null) {		// if something went wrong
				out.println("Database error...\n");
				return;
			}
			// open list, and save again when done
			out.println("we have OPENED the Lsit!");
			out.println(listToDelete.toString());

			if (listToDelete.isListBlank()) {
				// list is empty, ok to delete
				request.setAttribute("listToDelete", listToDelete);
				request.getSession().setAttribute("deleteListId", listToDelete.getId());
				
				// forward to page to confirm deletion with user
				RequestDispatcher rd = request.getRequestDispatcher("delete_list.jsp");
				rd.forward(request, response);
				return;
			} else {
				// TODO tell user they can't delete a list with items on it
			}	
		}
		
		if (request.getParameter("confirm_delete_list") != null) {
			out.println("MainMenu confirm_delete_list\n");
			
			int deleteListId = (Integer) request.getSession().getAttribute("deleteListId");	// TODO change this to the list itself
			ToDoList listToDelete = HiberFunc.getList(deleteListId);


			if (listToDelete == null) {		// if something went wrong
				out.println("Database error...\n");
				return;
			} else {		// open list, and save again when done
				out.println("we have OPENED the Lsit!");
				out.println(listToDelete.toString());
			}

			if (HiberFunc.deleteList(listToDelete)) {
				out.println(listToDelete.toString() + " delete list successful");
			} else {
				out.println("error deleting: " + listToDelete.toString());
			}
			this.doGet(request, response);		// TODO see if I can remove these doGets and returns, and let fallthrough to doGet at end
			return;
		}
		this.doGet(request, response);
	}

	@Override
    public String getServletInfo() {
        return "Short description";
    }
}

