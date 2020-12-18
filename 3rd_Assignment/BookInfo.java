public class BookInfo {
	private int isbn;
	private int numOfCopies;
	// Constructor
	public BookInfo(int isbn, int numOfCopies) {
		this.isbn = isbn;
		this.numOfCopies = numOfCopies;
	}
	// Getters
	public int getIsbn() { return isbn; }
	public int getNumOfCopies() { return numOfCopies; }
	
	// Used to move this book at front of list, then delete first element.
	// Actually a special setter.
	public void setIsbnToNeg() { this.isbn = -1; } 
	
	public void addACopy() { this.numOfCopies++; }
	public void removeACopy() {
		if(getNumOfCopies()==0) System.out.println("No copies of book with isbn "+getIsbn()+" to remove.");
		else this.numOfCopies--;
	}
	
	public String toString() {
		return "Book "+getIsbn()+", copies:"+getNumOfCopies();
	}
} 