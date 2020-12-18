/*
 * ListNode.java
 *
 */

// class to represent one node in a list
class ListNode
{
	// package access members; List can access these directly
	BookInfo data;
	ListNode nextNode;

	// constructor creates a ListNode that refers to bookInfo
	ListNode( BookInfo bookInfo )
	{
		this( bookInfo, null );
	} // end ListNode one-argument constructor

	// constructor creates ListNode that refers to
	// BookInfo and to next ListNode
	ListNode( BookInfo bookInfo, ListNode node )
	{
		data = bookInfo;
		nextNode = node;
	} // end ListNode two-argument constructor

	// return reference to data in node
	BookInfo getBookInfo()
	{
		return data; // return BookInfo in this node
	} // end method getBookInfo

	// return reference to next node in list
	ListNode getNext()
	{
		return nextNode; // get next node
	} // end method getNext
} // end class ListNode