import java.io.PrintStream;
import java.util.NoSuchElementException;

public class CharDoubleEndedQueueImpl<T> implements CharDoubleEndedQueue<T> {
	private String name;
	private int size;
	// First and Last nodes:
	private ListNode<T> firstElement;
	private ListNode<T> lastElement;
	// Default Constructor (no arguments)
	public CharDoubleEndedQueueImpl() {
		this("Double Ended Queue");
	}
	// Constructor with one argument
	public CharDoubleEndedQueueImpl(String name) {
		this.name = name;
		firstElement = lastElement = null;
		size = 0;
	}
	// Checks if the queue is empty
	public boolean isEmpty() {
		return firstElement == null;
	}
	// Puts an item (a char) at front of queue
	public void addFirst(T item) {
		ListNode<T> node = new ListNode<T>(item);
		if(isEmpty()) {
			firstElement=lastElement=node;
		} else {
			node.nextNode = firstElement;
			firstElement.previousNode = node;
			firstElement = node;
		}
		size++;
	}
	// Puts an item (a char) at rear of queue
	public void addLast(T item) {
		ListNode<T> node = new ListNode<T>(item);
		if(isEmpty()) {
			firstElement=lastElement=node;
		} else {
			node.previousNode = lastElement;
			lastElement.nextNode = node;
			lastElement = node;
		}
		size++;
	}
	// Removes an element from the front of the queue
	public T removeFirst() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException("The queue is empty. You can't remove first element.");
		T temp = firstElement.getData();
		firstElement.nextNode.previousNode = null;
		firstElement = firstElement.nextNode;
		size--;
		return temp;
	}
	// Removes an element from the rear of the queue
	public T removeLast() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException("The queue is empty. You can't remove last element.");
		T temp = lastElement.getData();
		lastElement.previousNode.nextNode = null;
		lastElement = lastElement.previousNode;
		size--;
		return temp;
	}
	// Just return the first element; do not remove it.
	public T getFirst() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException("The queue is empty.");
		return firstElement.getData();
	}
	// Just return the last element; do not remove it.
	public T getLast() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException("The queue is empty.");
		return lastElement.getData();
	}
	// Returns the size of queue
	public int size() {
		return size;
	}
	// Print the queue
	public void printQueue(PrintStream stream) {
		if(!isEmpty()) {
			ListNode<T> currentnode = firstElement;
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
