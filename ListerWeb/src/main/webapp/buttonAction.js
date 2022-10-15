/**
 * 
 */

function button(action, selectedListId, selectedListItem = -1) {
	document.getElementById("action").value = action;
	document.getElementById("selectedListId").value = selectedListId;
	document.getElementById("selectedListItem").value = selectedListItem;
    callServlet.submit();
}