import java.io.PrintStream;

public class ST {
	private Treenode head;
	// Constructor
	public ST() { head = null; }
	// Searches a specific warehouse in the tree. If it exists, it prints its data.
	public void searchByWarehouse(int nodeid) {
		if(search(nodeid)!=null) {
			Treenode t = search(nodeid);
			t.printBookList();
		} else {
			System.out.println("There is not a warehouse with this id.");
		}
	}
	// Searches if the book with the isbn exists in city with id: nodeid.
	public void searchBookInWarehouse(int nodeid,int isbn) {
		Treenode t = search(nodeid);
		if(t!=null) {
			BookInfo bi = t.existsInList(isbn);
			if(bi!=null) {
				System.out.println(bi.toString());
			} else {
				System.out.println("Warehouse "+t.key()+" does not have this book.");
			}
		} else {
			System.out.println("There is not a warehouse with this id.");
		}
	}
	// Searches in whitch warehouses exist the book with this isbn.
	public void searchBook(int isbn) {
		System.out.println("The book is availiable at");
		boolean flag = false;
		flag = searchBook(isbn,head,flag);
		if(!flag) System.out.println(" no warehouse");
	}
	// Searches in whitch treenodes exist the book with this isbn. (This does the real job).
	public boolean searchBook(int isbn,Treenode h,boolean flag) {
		if(h!=null) {
			searchBook(isbn,h.l,flag);
			BookInfo bi = h.existsInList(isbn);
			if(bi!=null) {
				// flag states if the book is found
				flag = true;
				System.out.println("/nWarehouse "+h.key()+" located in "+h.getCity()+", copies: "+bi.getNumOfCopies());
			}
			searchBook(isbn,h.r,flag);
		}
		return flag;
	}
	// Searches a specific treenode in the tree.
	public Treenode search(int nodeid) { return searchR(head,nodeid); }
	// Searches a specific treenode in the tree.(This does the real job).
	private Treenode searchR(Treenode h,int nodeid) {
		if(h==null) return null;
		if(nodeid==h.key()) return h;
		if(nodeid<h.key()) {
			return searchR(h.r,nodeid);
		} else {
			return searchR(h.l,nodeid);
		}
	}
	// Inserts a book at the warehouse with this id. If the book already exists,
	// it adds the number of its new copies.
	public void insertBookAtWarehouse(int nodeid, int isbn, int copies) {
		if(search(nodeid)!=null) {
			Treenode t = search(nodeid);
			t.addBook(isbn,copies);
		} else {
			System.out.println("There is not a warehouse with this id.");
		}
	}
	// Insert a warehouse at the tree.
	public void insertWarehouse(int nodeid, String name) { 
		if(search(nodeid)==null) {
			Treenode newStore = new Treenode(nodeid, name);
			head = insertR(head,newStore); 
		} else {
			System.out.println("There is already a warehouse with this id.");
		}
	}
	// Insert a treenode at the tree, using randomized trees. (This does the real job).
	private Treenode insertR(Treenode h,Treenode newStore) {
		if(h==null) return newStore;
		if(Math.random()*h.N <1.0) return insertT(h,newStore);
		if(h.key()<newStore.key()) {
			h.l = insertR(h.l,newStore);
		} else {
			h.r = insertR(h.r,newStore);
		}
		h.N++;
		return h;
	}
	// Insert a treenode at the tree, using classic insert. (This does the real job).
	private Treenode insertT(Treenode h,Treenode newStore) {
		if(h==null) return newStore;
		if(newStore.key()<h.key()) {
			h.l = insertT(h.l,newStore);
			h = rotR(h);
		} else {
			h.r = insertT(h.r,newStore);
			h = rotL(h);
		} 
		return h;
	}
	// Removes a book at the warehouse with this id. If the book does not exist,
	// it prints a relative message.
	public void removeBook(int nodeid,int isbn) {
		if(search(nodeid)!=null) {
			Treenode t = search(nodeid);
			t.removeBook(isbn,1);
		} else {
			System.out.println("There is not a warehouse with this id.");
		}
	}
	// Remove a warehouse at the tree.
	public void removeWarehouse(int nodeid) {
		removeR(head,nodeid);
	}
	// Removes the treenode with nodeid==h.key(), if it exists.
	private Treenode removeR(Treenode h,int nodeid) {
		if(h==null) return null;
		int key = h.key();
		if(nodeid<key) removeR(h.l,nodeid);
		if(key<nodeid) removeR(h.r,nodeid);
		if(key==nodeid) h = joinLR(h.l,h.r);
		return h;
	} 
	// Replacement of a treenode with its parent or child, using randomization.
	private Treenode joinLR(Treenode a,Treenode b) {
		int N = a.N + b.N;
		if(a==null) return b;
		if(b==null) return a;
		if(Math.random()*N < 1.0*a.N) {
			a.r = joinLR(a.r,b);
			return a;
		} else {
			b.l = joinLR(a,b.l);
			return b;
		}	
	}
	// Does a right rotation of the treenode h.
	private Treenode rotR(Treenode h) {
		Treenode newStore = h.l;
		h.l = newStore.r;
		newStore.r = h;
		return newStore;
	}
	// Does a left rotation of the treenode h.
	private Treenode rotL(Treenode h) {
		Treenode newStore = h.r;
		h.r = newStore.l;
		newStore.l = h;
		return newStore;
	}
	// Prints the tree.
	public void printTree(PrintStream stream) {
		printTree(stream,head);
	}
	// Prints the tree, using inorder traversal.
	public void printTree(PrintStream stream, Treenode h) {
		if(h!=null) {
			printTree(stream,h.l);
			h.printBookList();
			printTree(stream,h.r);
		}
	}
}