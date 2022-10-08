package meprod.ListerWeb;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "list_item")
public class ListItem implements Serializable {

	private static final long serialVersionUID = 3103112339987530571L;

	@Id
	@Column(name = "list_item_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name = "name")
	private String listItemName;

	@Column(name = "created_date")
	private Date creationDateTime;

	@Column(name = "done")
	private boolean done;

	@Column(name = "finished_date")
	private Date finishedDateTime;

	@Column(name = "TODO_LIST_ITEM_NUM")
	private int toDoListItemNum;

	public ListItem() {}

	public ListItem(String toDoItem, int toDoListItemNum) {
		setListItemName(toDoItem);
		setCreationDateTime(new Date(System.currentTimeMillis()));
		setDone(false);
		setFinishedDateTime(null);
		setToDoListItemNum(toDoListItemNum);
	}

	public int getId() {
		return id;
	}

	public void setId(int newId) {
		this.id = newId;
	}

	public String getListItemName() {
		return listItemName;
	}

	public void setListItemName(String toDoItem) {
		this.listItemName = toDoItem;
	}

	public Date getCreationDateTime() {
		return new Date(creationDateTime.getTime());
	}

	public void setCreationDateTime(Date creationDateTime) {
		this.creationDateTime = new Date(creationDateTime.getTime());
	}

	public boolean isDone() {
		return getDone();
	}

	public boolean getDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public Date getFinishedDateTime() {
		return finishedDateTime;
	}

	public void setFinishedDateTime(Date finishedDateTime) {
		this.finishedDateTime = finishedDateTime;
	}

	public int getToDoListItemNum() {
		return toDoListItemNum;
	}

	public void setToDoListItemNum(int toDoListItemNum) {
		this.toDoListItemNum = toDoListItemNum;
	}

	public void checkOffListItem() {
		setDone(true);
		setFinishedDateTime(new Date(System.currentTimeMillis()));
	}

	@Override
	public String toString() {
		StringBuilder listDetails = new StringBuilder(
				listItemName + ", Created: " + creationDateTime + ", Complete: " + (done ? "Yes": "No"));
		if (isDone()) {
			listDetails.append(", Completed: " + getFinishedDateTime());
		}
		return listDetails.toString();
	}

}
