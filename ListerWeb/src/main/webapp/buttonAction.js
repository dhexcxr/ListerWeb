/**
 * 
 */

function button(action, selectedList, selectedListItem = -1) {
	document.getElementById("action").value = action;
	document.getElementById("selectedList").value = selectedList;
	document.getElementById("selectedListItem").value = selectedListItem;
    callServlet.submit();
}