import java.io.PrintStream;
import java.util.NoSuchElementException;

public class CharQueueImpl<T> implements CharQueue<T> {
	private String name;
	private int size;
	// First and Last nodes:
	private ListNode<T> tail;
	private ListNode<T> head;
	// Default Constructor (no arguments)
	public CharQueueImpl() {
		this("Queue");
	}
	// Constructor with one argument
	public CharQueueImpl(String name) {
		this.name = name;
		tail = head = null;
		size = 0;
	}
	// Checks if the queue is empty
	public boolean isEmpty() {
		return tail == null;
	}
	// Puts an item (a char) at rear of queue
	public void put(T item) {
		ListNode<T> temp = new ListNode<T>(item);
		if(isEmpty()) {
			head=tail=temp;
		} else {
			tail.nextNode = temp;
			tail = temp;
		}
		size++;
	}
	
	// Removes an element from the front of the queue
	public T get() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException("The queue is empty. You can't remove oldest element.");
		T tempData = head.getData();
		if(head==tail) {
			head=tail=null;
		} else {
			head = head.nextNode;
		}
		size--;
		return tempData;
	}
	
	
	// Just return the first element; do not remove it.
	public T peek() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException("The queue is empty.");
		return head.getData();
	}
	
	// Returns the size of queue
	public int size() {
		return size;
	}
	// Print the queue
	public void printQueue(PrintStream stream) {
		if(!isEmpty()) {
			ListNode<T> currentnode = head;
			stream.println("Print the queue:");
			stream.println(currentnode.getData());
			while(currentnode.nextNode!=null) {
				stream.println(currentnode.nextNode.getData());
				currentnode = currentnode.nextNode;
			}
		} else {
			stream.println("The queue is empty.");
		}
	}
}
