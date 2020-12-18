import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

public class IntDoubleEndedQueueImplArray {
	private int[] theArray;
	private String name;
	private int firstElement;
	private int lastElement;
	private int capacity;
	
	public IntDoubleEndedQueueImplArray() {
		this("Double Ended Queue");
	}
	
	public IntDoubleEndedQueueImplArray(String name) {
		this.name = name;
		firstElement = lastElement = -1;
		capacity = 10;
		theArray = new int[10];
	}
	
	public boolean isEmpty() {
		return firstElement==lastElement && lastElement == -1;
	}
	
	public void addFirst(int item) {
		if(isFull()) theArray = doubleArray(theArray);
		// If first place in array is occupied
		if(firstElement==0) {
			for(int i=lastElement;i>=0;i--) {
				theArray[i+1] = theArray[i];
			}
			lastElement++;
		} else if(isEmpty()){
			firstElement = lastElement = 0;
		}
		theArray[firstElement] = item;
	}
	
	public void addLast(int item) {
		if(isFull()) theArray = doubleArray(theArray);
		if(lastElement==capacity-1) {
			// The queue is not full (due to the check above). Therefore, if the last element is at the last memory position 
			//of the queue, that means the firstElement is >=1 (so i-1>=0).
			for(int i = firstElement;i<=lastElement;i++) {
				theArray[i-1] = theArray[i];
			}
			firstElement--;
		} else if(isEmpty()) {
			firstElement = lastElement = 0;
		} else {
			lastElement++;
		}
		theArray[lastElement] = item;
	}
	public int removeFirst() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException("The queue is empty. You can't remove first element.");
		int temp = getFirst();
		firstElement++;
		return temp;
	}
	
	public int removeLast() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException("The queue is empty. You can't remove last element.");
		int temp = getLast();
		lastElement--;
		return temp;
	}
	public int getFirst() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException("The queue is empty.");
		return theArray[firstElement];
	}
	public int get(int i) throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException("The queue is empty.");
		if(i < size()) {
			return theArray[i];
		} else {
			throw new IndexOutOfBoundsException("The queue has less than "+i+1+" elements.");
		}
	}
	public int getLast() throws NoSuchElementException {
		if(isEmpty()) throw new NoSuchElementException("The queue is empty.");
		return theArray[lastElement];
	}
	public int size() {
		if(isEmpty()) {
			return 0;
		} else {
			return lastElement - firstElement + 1;
		}
	}

	public boolean isFull() {
		return size()==capacity;
	}
	
	private int[] doubleArray(int[] theAr) {
		if(isFull()) {
			capacity *= 2;
			int[] newArray =  new int[capacity];
			for(int i=0;i<theAr.length;i++) {
				newArray[i] = theAr[i];
			}
			return newArray;
		} else {
			return theAr;
		}
	}
	
	public void print(PrintStream stream) {
		if(!isEmpty()) {
			for(int i=firstElement;i<=lastElement;i++) {
				stream.print(theArray[i]+" ");
			}
		}
	}
}