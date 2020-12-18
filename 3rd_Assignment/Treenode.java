public class Treenode {
	private int id;
	private String city;
	public Treenode l; // pointer to left subtree
	public Treenode r; // pointer to right subtree
	public int N; //number of nodes in the subtree starting at this TreeNode
	private List booklist;
	
	// Constructor
	public Treenode(int id,String city) {
		this.id = id;
		this.city = city;
		this.l=this.r=null;
		N=1; //Just this node of the tree
		booklist = new List();
	}
	
	// Getters
	public int key() { return this.id; }
	public String getCity() { return this.city; }
	public int getN() { return N; }
	//public Treenode getLTreenode() { return this.l; }
	//public Treenode getRTreenode() { return this.r; }
	
	// Path Setters
	public void setLTreenode( Treenode l ) { 
		this.l = l;
		N += l.getN(); 
	}
	public void setRTreenode( Treenode r ) { 
		this.r = r; 
		N += r.getN(); 
	}
	
	public BookInfo existsInList(int isbn) {
		return booklist.itExists(isbn);
	}
	
	// Increases number of copies by 1. (itExists do not only returns a boolean; See List class)
	public void addBook(int isbn,int nOfcps) {
		if(!booklist.itExists(isbn,1,nOfcps)) {
			BookInfo bi = new BookInfo(isbn,nOfcps);
			booklist.insertAtFront(bi);
			booklist.sort();
		}
	}
	// Reduces number of copies by 1. if number of copies == 0, does some extra things (See lines 49-50).
	// (itExists do not only returns a boolean; See List class)
	// Due to the function of itExist method, if the code is !=1, the copies are reduced by 1,
	// and the isbn is set to -1. If after this reduction, number of copies ==0,the list is sorted,
	// so this book has to be at the front of the list. Then the first node of List is removed. (See List, lines 40-70)
	public void removeBook(int isbn,int nOfcps) {
		if(!booklist.itExists(isbn,0,nOfcps)) {
			System.out.println("There is not a book with ISBN = "+isbn+".");
		}
	} 
	
	public void printBookList() {
		System.out.println("Warehouse "+key()+" located in "+getCity()+":");
		booklist.print();
	}
}