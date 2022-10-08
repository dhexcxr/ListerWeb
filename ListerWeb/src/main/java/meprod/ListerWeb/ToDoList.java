package meprod.ListerWeb;

import static java.lang.System.out;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "todo_list")
public class ToDoList implements Serializable{

	private static final long serialVersionUID = -6494044540380915496L;

	private static Scanner keyboard = new Scanner(System.in);

	@Id
	@Column(name = "todo_list_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String listName;

	@Column(name = "created_date")
	private Date creationDateTime;

	@ElementCollection(targetClass=ListItem.class)
	@OrderColumn(name = "list_item_id")
	@OneToMany(cascade = CascadeType.ALL,
				orphanRemoval = true,
				fetch = FetchType.EAGER)
	private List<ListItem> listItems;

	public ToDoList() {
		System.out.println("we got to the ToDoList constructor");
	}

	public ToDoList(String listName) {
		setListName(listName);
		setCreationDateTime(new Date(System.currentTimeMillis()));
		setListItems(new ArrayList<>());
	}

	public int getId() {
		return id;
	}

	public void setId(int newId) {
		this.id = newId;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public Date getCreationDateTime() {
		return new Date(creationDateTime.getTime());
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = new Date(creationDateTime.getTime());
	}

	public List<ListItem> getListItems() {
		return listItems;
	}

	public void setListItems(List<ListItem> listItems) {
		this.listItems = listItems;
	}
	
	public String getDescription() {
		return this.toString();
	}

	public void openList() {
		while (true) {
//			String listMenuSelection = showListMenu();
//
//			out.println();
//
//			switch (listMenuSelection) {
//				case "1": {
//					showListItems();
//					break;
//				}
//				case "2": {
//					createListItem();
//					break;
//				}
//				case "3": {
//					deleteListItem();
//					break;
//				}
//				case "4": {
//					checkOffListItem();
//					break;
//				}
//				case "5": {
//					return;
//				}
//				default:
//					out.println("Invalid selection: " + listMenuSelection + "\n\n");
//			}
			// TODO move to web FE
			break;
		}
	}

	// TODO move to web FE
	private String showListMenu() {
//		out.println("List Menu for " + this.getListName());
//		out.println("1. List To Do items");
//		out.println("2. Create To Do item");
//		out.println("3. Delete To Do item");
//		out.println("4. Check off To Do item");
//		out.println("5. Close list");
//		out.print("\nMake a selection: ");
//
//		return keyboard.nextLine();
		return null;
	}

	// TODO move to web FE
	private boolean showListItems() {
//		if (listItems.isEmpty()) {
//			out.println("You have no To Do items on this list...\n");
//			return false;
//		}
//
//		out.println("Current Items on list:");
//		ListIterator<ListItem> listerIterator = listItems.listIterator();
//		while (listerIterator.hasNext()) {
//			ListItem listItem = listerIterator.next();
//			out.println(listItem.getToDoListItemNum() + ". " + listItem.toString());
//		}
//
//		out.println();
//		return true;
		return false;
	}
	
	protected void addListItem(String newListItemName) {
//		boolean result = false;
		listItems.add(new ListItem(newListItemName, listItems.size() + 1));		
//		return result;
	}

	private void createListItem() {
		out.print("What would you like to name the new To Do item? ");
		String newListItemName = keyboard.nextLine();
		listItems.add(new ListItem(newListItemName, listItems.size() + 1));
		// TODO test for successful ToDo Item creation
		out.println("New To Do item created...\n"); // TODO include To Do item name
	}

	private void deleteListItem() {
//		if (showListItems()) {
//			out.print("Which list would you like to remove? ");
//			String toDoItemIndexInput = keyboard.nextLine();
//
//			int toDoItemIndex = Integer.parseInt(toDoItemIndexInput);
//
//			ListIterator<ListItem> deleteIterator = listItems.listIterator(toDoItemIndex);
//			ListItem listItemToDelete = deleteIterator.previous();
//
//			if (listItemToDelete.isDone()) {
//				out.println("Cannot delete a completed To Do List item...\n");
//				return;
//			}
//
//			while (true) {
//				out.print("Delete To Do item: " + listItemToDelete.toString() + "? [Y/N] ");
//				String deleteConfirmation = keyboard.nextLine();
//
//				switch (deleteConfirmation.toUpperCase()) {
//					case "Y": {
//						deleteIterator.remove();
//						out.println(listItemToDelete.getListItemName() + " deleted...\n");
//						return;
//					}
//					case "N": {
//						out.println(listItemToDelete.getListItemName() + " not deleted...\n");
//						return;
//					}
//					default:
//						out.println("Invalid selection: " + deleteConfirmation + "\n\n");
//				}
//			}
//		}
	}

	protected void checkOffListItem(String listItemIndex) {
		int toDoItemIndex = Integer.parseInt(listItemIndex);
		ListItem listItemToCheckOff = listItems.get(toDoItemIndex - 1);
		listItemToCheckOff.checkOffListItem();
		out.println(listItemToCheckOff.getListItemName() + " checked off...\n");
		return;
	}
	
	protected boolean isListItemComplete(String listItemIndex) {
		int toDoItemIndex = Integer.parseInt(listItemIndex);
		return listItems.get(toDoItemIndex - 1).isDone();
	}

	@Override
	public String toString() {
		return listName + ", Created: " + getCreationDateTime();
	}
}
