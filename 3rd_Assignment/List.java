import java.util.Comparator;
public class List {
	private ListNode firstNode;
	private ListNode lastNode;
	private String name; 
	
	// constructor creates empty List with "listName" as the name
	public List() { this("list"); } 

	// constructor creates an empty List with a name
	public List( String listName) {
		name = listName;
		firstNode = lastNode = null;
	} 

    public void sort() {
        ListNode t, x, b = new ListNode(null, null);
        while (firstNode != null){
            t = firstNode;  firstNode = firstNode.nextNode;
            for (x = b; x.nextNode != null; x = x.nextNode)  //b is the first node of the new sorted list
                if (x.nextNode.data.getIsbn() >  t.data.getIsbn()) break;
            t.nextNode = x.nextNode; x.nextNode = t;
            if (t.nextNode==null) lastNode = t;
        }
        firstNode = b.nextNode;
    }
	// Examines if the book with this isbn exists already at list, 
	// and if this is true, set the new number of copies according to code:
	// if code==1 the copies are increased by 1.
	// if code!=1, the copies are reduced by 1, then the isbn is set negative, so that this element can be 
	// moved to the front of list with sort method, then deleted by removeFromFront method.
	public boolean itExists(int isbn,int code,int numOfCopies) {
		boolean exists = false;
		ListNode t = firstNode;
		while(t!=null) {
			if(t.data.getIsbn()==isbn) {
				exists = true;
				break;
			}
			t = t.nextNode;
		}
		if(exists) {
			if(code==1) {
				for(int i=0;i<numOfCopies;i++) t.data.addACopy();
			} else {
				for(int i=0;i<numOfCopies;i++) {
					t.data.removeACopy();
					if(t.data.getNumOfCopies()==0) {
						t.data.setIsbnToNeg();
						this.sort();
						this.removeFromFront();
						break; //We add the break because elsewhere the methods
						// reduces and the next book's numOfCopies, after the previous is removed.
					}
				}
			}
		}
		return exists;
	}
	// Seearches if the book with this isbn exist at list, and returns that book.
	public BookInfo itExists(int isbn) {
		ListNode t = firstNode;
		while(t!=null) {
			if(t.data.getIsbn()==isbn) break;
			t = t.nextNode;
		}
		return t.data;
	}
	
	public void insertAtFront( BookInfo insertItem ) {
		ListNode node = new ListNode( insertItem );
		if ( isEmpty() ) // firstNode and lastNode refer to same BookInfo
		firstNode = lastNode = node;
		else { // firstNode refers to new node
			node.nextNode = firstNode;
			firstNode = node;
		}
	} 
	// insert BookInfo at end of List
	public void insertAtBack( BookInfo insertItem ) {
		ListNode node = new ListNode( insertItem );
		if ( isEmpty() ) // firstNode and lastNode refer to same BookInfo
		firstNode = lastNode = node;
		else { // lastNode's nextNode refers to new node
			lastNode.nextNode = node;
			lastNode = node;
		}
	} 
	// remove first node from List
	public BookInfo removeFromFront() throws EmptyListException {
		if (isEmpty())	throw new EmptyListException( name );
		BookInfo removedItem = firstNode.data; // retrieve data being removed
		// update references firstNode and lastNode
		if ( firstNode == lastNode ) firstNode = lastNode = null;
		else firstNode = firstNode.nextNode;
		return removedItem;
	} 
	// remove last node from List
	public BookInfo removeFromBack() throws EmptyListException {
		if ( isEmpty() ) throw new EmptyListException( name );
		BookInfo removedItem = lastNode.data; // retrieve data being removed
		// update references firstNode and lastNode
		if ( firstNode == lastNode ) firstNode = lastNode = null;
		else { // locate new last node 
			ListNode current = firstNode;
			// loop while current node does not refer to lastNode
			while ( current.nextNode != lastNode )
			current = current.nextNode;
			lastNode = current; // current is new lastNode
			current.nextNode = null;
		}
		return removedItem; 
	} 
	// determine whether list is empty
	public boolean isEmpty() {return firstNode == null; } 

	// output List contents
	public void print() {
		if ( isEmpty() ) {
			System.out.printf( "Empty %s\n", name );
			return;
		} 
		ListNode current = firstNode;
		// while not at end of list, output current node's data
		while ( current != null ) {
			System.out.println(current.data.toString());
			current = current.nextNode;
		}
		System.out.println("");
	} 
}