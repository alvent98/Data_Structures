import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Comparator;

public class CharQueueWithMin<Character> extends CharQueueImpl<Character> {
	private String name;
	private int size;
	// First and Last nodes:
	private ListNode<Character> tail;
	private ListNode<Character> head;
	// Create the double queue to keep the min
	private CharDoubleEndedQueueImpl<Character> doubleQueue = new CharDoubleEndedQueueImpl();
	protected Comparator cmp;
	// Default Constructor (no arguments)
	public CharQueueWithMin() {
		this("Queue");
	}
	// Constructor with one argument
	public CharQueueWithMin(String name) {
		super(name);
	}
	// Checks if the queue is empty
	public boolean isEmpty() {
		return super.isEmpty();
	}
	// Puts an item (a char) at front of queue
	public void put(Character item) {
		// Put the item as in CharQueueImpl
		super.put(item);
		// If doubleQueue is empty just add the item at front.
		if(doubleQueue.isEmpty()) {
			doubleQueue.addFirst(item);
		} else {
			// If item is less than the first element of double queue, add the item at front.
			if(((Comparable)doubleQueue.getFirst()).compareTo(item)>= 0) {
				doubleQueue.addFirst(item);
			}
		}		
	}
	
	// Removes an element from the rear of the queue
	public Character get() throws NoSuchElementException {
		// We also remove last element of the double queue
		Character temp = doubleQueue.removeLast();
		return super.get();
	}	
	
	// Just return the first element; do not remove it.
	public Character peek() throws NoSuchElementException {
		return super.peek();
	}
	
	// Returns the size of queue
	public int size() {
		return super.size();
	}
	
	// Return the first element of doublequeue, whitch is the min of queue.
	public Character min() {
		return doubleQueue.getFirst();
	}
	// Print the queue
	public void printQueue(PrintStream stream) {
		super.printQueue(stream);
	}
}