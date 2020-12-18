import java.io.*;
import java.util.Comparator;

public class Sort{
	private int[] array;
	public Sort() {}
	public void greedyDecreasing(int[] newArray) {
		// Import the data from txt
		this.array = newArray;
		IntegerComparator cmp1 = new IntegerComparator();
		int numOfProcesses = array[1];
		int remainProcesses = numOfProcesses;
		
		// Instantiation of the array with the procedures.
		int[] procedures_array = new int[numOfProcesses];
		
		// Initialization of the array with the procedures.
		for(int i=0;i<numOfProcesses;i++) procedures_array[i] = array[i+2];
		
		// Create a priority queue to put the procedures in order to sort them:
 		MaxPQ<Integer> pqProcess = new MaxPQ(numOfProcesses,cmp1);
		for(int i=0;i<numOfProcesses;i++) pqProcess.insert(procedures_array[i]);
		pqProcess.heapSort();
		int selected;
		// Put the procedures at the procedures_array, from the most to least heavy (Actually we do so by start putting 
		// the procedures from the end of array).
		for(int i=numOfProcesses+1;i>1;i--) {
			selected = pqProcess.getMax();
			array[i] = selected;
		}
		//for(int i = 0; i<numOfProcesses+2;i++) System.out.println(array[i]);
	}
	// A getter to return the sorted array with the procedures.
	public int[] getProcessedArray() {
		return array;
	}
}