// class to represent one node in a list
class ListNode<T> {
	// package access members; List can access these directly
	T data;
	ListNode nextNode;
	ListNode previousNode;

	// constructor creates a ListNode that refers to object
	ListNode(T item) {
		this(item, null, null);
	}

	// Object and to next ListNode
	ListNode(T item, ListNode previousNode, ListNode nextNode) {
		data = item;
		this.previousNode = previousNode;
		this.nextNode = nextNode;
	} 

	// return reference to data in node
	T getData() { return data; } 

	// return reference to next node in list
	ListNode getNext() { return nextNode; }
	
	// return reference to previous node in list
	ListNode getPrevious() { return previousNode; }
}