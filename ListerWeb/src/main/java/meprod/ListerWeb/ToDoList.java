package meprod.ListerWeb;

import static java.lang.System.out;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		out.println("we got to the ToDoList constructor");
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
	
	protected void addListItem(String newListItemName) {
		listItems.add(new ListItem(newListItemName, listItems.size() + 1));		
	}

	protected void deleteListItem(String listItemIndex) {
		int toDoItemIndex = Integer.parseInt(listItemIndex) - 1;
		listItems.remove(toDoItemIndex);
	}

	protected void checkOffListItem(String listItemIndex) {
		int toDoItemIndex = Integer.parseInt(listItemIndex) - 1;
		ListItem listItemToCheckOff = listItems.get(toDoItemIndex);
		listItemToCheckOff.checkOffListItem();
		out.println(listItemToCheckOff.getListItemName() + " checked off...\n");
		return;
	}
	
	protected boolean isListItemComplete(String listItemIndex) {
		int toDoItemIndex = Integer.parseInt(listItemIndex) - 1;
		return listItems.get(toDoItemIndex).isDone();
	}
	
	protected ListItem getListItem(String listItemIndex) {
		int toDoItemIndex = Integer.parseInt(listItemIndex) - 1;
		return listItems.get(toDoItemIndex);
	}
	
	protected String getListItemName(String listItemIndex) {
		int toDoItemIndex = Integer.parseInt(listItemIndex) - 1;
		return listItems.get(toDoItemIndex).getListItemName();
	}
	
	public boolean isListBlank() {
		return this.listItems.isEmpty();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ToDoList) {
			ToDoList listToCompare = (ToDoList) obj;
			return (this.getId() == listToCompare.getId()) &&
					(this.getListName().equals(listToCompare.getListName())) &&
					(this.getCreationDateTime().equals(listToCompare.getCreationDateTime())) &&
					(this.getListItems().equals(listToCompare.getListItems()));
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public String toString() {
		return listName + ", Created: " + getCreationDateTime();
	}
}
