import java.util.Scanner;

public class Greedy{
	public static void main(String args[]) {
		TxtReader data = new TxtReader();
		// Import the data list from txt
		int[] array = data.readTxt("Proc.txt");
		GreedyAlgorithm newAlg = new GreedyAlgorithm();
		// Data procession:
		int makespan = newAlg.runAlgorithm1(array);		
		System.out.println("Makespan = "+makespan);
	}
}