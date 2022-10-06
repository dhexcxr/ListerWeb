package meprod.ListerWeb;

import static java.lang.System.out;

import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Lister 
{
	private static Scanner keyboard = new Scanner(System.in);
	protected static SessionFactory factory;

	public static void main(String[] args) {
		openDbConnection();		
		out.println("Welcome to ListerHiber");
		run();
	}
	
	protected static void openDbConnection() {
		// attempt to set up session factory
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Exception ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}
	
	private static void run() {
		// display main menu and let user interact
		while (true) {
			out.println("Main Menu");
			out.println("1. List Lister lists");
			out.println("2. Create Lister list");
			out.println("3. Delete Lister list");
			out.println("4. Open Lister list");
			out.println("5. Quit Lister");
			out.print("\nMake a selection: ");

			String mainMenuSelection = keyboard.nextLine();
			out.println();

			switch (mainMenuSelection) {
				case "1": {
					listLists();
					break;
				}
				case "2": {
					createList();
					break;
				}
				case "3": {
					deleteList();
					break;
				}
				case "4": {
					openList();
					break;
				}
				case "5": {
					out.println("Good bye...");
					return;
				}
				default:
					out.println("Invalid selection: " + mainMenuSelection + "\n\n");
			}
		}
	}

	private static boolean listLists() {
		// get all lists
		List<ToDoList> listerLists = HiberFunc.getLists();
		
		// if there was a failure
		if (listerLists == null) {
			out.println("Databse error...\n");
			return false;
		} else if (listerLists.isEmpty()) {
			out.println("You have no lists...\n");
			return false;
		}

		out.println("Current To Do lists:");
		ListIterator<ToDoList> listerIterator = listerLists.listIterator();
		while (listerIterator.hasNext()) {
			ToDoList todoList = listerIterator.next();
			out.println(todoList.getId() + ". " + todoList.toString());
		}

		out.println();
		// if everything went well
		return true;
	}

	private static void createList() {
		// make a new list
		out.print("What would you like to name the new Lister list? ");
		String newListName = keyboard.nextLine();
		ToDoList toDoList = new ToDoList(newListName);
		
		// save the new list in the DB
		boolean result = HiberFunc.saveList(toDoList);
		
		if (result) {		// if everything went well 
			out.println("New list " + newListName + " created...\n");
		} else {
			out.println("Databse error...\n");
		}
	}

	private static void deleteList() {
		// delete a list from our To Do Lists
		if (listLists()) {		// only display if listing the lists went well
			out.print("Which list would you like to remove? ");
			String listIndexInput = keyboard.nextLine();
			int listIndex = Integer.parseInt(listIndexInput);

			// get the list we want to delete
			ToDoList listToDelete = HiberFunc.getList(listIndex);


			if (!listToDelete.getListItems().isEmpty()) {		// if list is not empty
				out.println("Cannot delete a To Do list with items on it...\n");
				return;
			}

			while (true) {
				// confirm deletion
				out.print("Delete list: " + listToDelete.toString() + "? [Y/N] ");
				String deleteConfirmation = keyboard.nextLine();

				switch (deleteConfirmation.toUpperCase()) {
					case "Y": {
						// yes, please delete
						boolean deleteResult = HiberFunc.deleteList(listToDelete);
						
						if (deleteResult) {		// check result of DB operation
							out.println(listToDelete.getListName() + " deleted...\n");
						} else {
							out.println("Database error...\n");
						}
						return;
					}
					case "N": {
						out.println(listToDelete.getListName() + " not deleted...\n");
						return;
					}
					default:
						out.println("Invalid selection: " + deleteConfirmation + "\n\n");
				}
			}
		}
	}

	private static void openList() {
		// open a list for editing, adding/removing/checking off To Do items, etc
		if (listLists()) {		// only display if listing the lists went well
			out.print("Which list would you like to open? ");
			String listIndexInput = keyboard.nextLine();
			int listIndex = Integer.parseInt(listIndexInput);
			out.println();

			// get the list we want to open
			ToDoList listToOpen = HiberFunc.getList(listIndex);
			if (listToOpen == null) {		// if something went wrong
				out.println("Database error...\n");
			} else {		// open list, and save again when done
				listToOpen.openList();
				HiberFunc.saveList(listToOpen);
			}
		}
	}
}
