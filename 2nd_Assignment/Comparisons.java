import java.util.Random;

public class Comparisons{
	public static void main(String args[]) {
		TxtReader data = new TxtReader();
		TxtWriter txt = new TxtWriter();
		// the makespans: Averages and totals, for each algorithm
		int greedyMakespan = 0;
		int greedyDecreasingMakespan = 0;
		int avGreedyMakespan, avGreedyDecreasingMakespan;
		// The percentage of speed of the faster algorithm.
		double percentage = 0;
		double tempPercentage;
		int n; // Number of procedures on each file
		// 3 Flags for the evaluation of speed of each algorithm, for each one of the three N (100/500/1000)
		boolean flag100 = true;
		boolean flag500 = true;
		boolean flag1000 = true;
		// Create 30 files with random procedures' times
		for(int i=0;i<10;i++) {
			txt.createFile("output0"+i+".txt",100);
			txt.createFile("output1"+i+".txt",500);
			txt.createFile("output2"+i+".txt",1000);
		}
		// Proceed the created files
		for(int i=0;i<30;i++) {
			// Read search correctly the name of the file, and open it
			String zero = "";
			if(i>=0 && i<=9) zero = "0";
			int[] array = data.readTxt("output"+zero+i+".txt");
			// Data procession:
			// a) First is algorithm No 1: (GreedyAlgorithm)
			GreedyAlgorithm newAlg = new GreedyAlgorithm();
			// Add the makespan
			greedyMakespan += newAlg.runAlgorithm1(array);
			// a) Second is algorithm No 2: (GreedyDecreasingAlgorithm)
			array = data.readTxt("output"+zero+i+".txt");
			Sort processesSorter = new Sort();
				// Sort the procedures from least to most fast.
			processesSorter.greedyDecreasing(array);
			array = processesSorter.getProcessedArray();
				// Then call again algorithm No 1, and add the makespan:
			greedyDecreasingMakespan += newAlg.runAlgorithm1(array);
			// If all files of same N are processed, calculate average makespan for this N.
			if(i==9 || i==19 || i==29) {
				if(i==9) n = 100;
				else if(i==19) n = 500;
				else n = 1000;
				// Calculate average makespan for each one of the algorithms.
				avGreedyMakespan = greedyMakespan/10;
				avGreedyDecreasingMakespan = greedyDecreasingMakespan/10;
				greedyMakespan = 0;
				greedyDecreasingMakespan = 0;
				// Is greedy algorithm faster than greedy decreasing algorithm for N = 100?
				if(i==9) if(avGreedyDecreasingMakespan>=avGreedyMakespan) flag100 = false;
				// Is greedy algorithm faster than greedy decreasing algorithm for N = 500?
				else if(i==19) if(avGreedyDecreasingMakespan>=avGreedyMakespan) flag500 = false;
				// Is greedy algorithm faster than greedy decreasing algorithm for N = 1000?
				else if(avGreedyDecreasingMakespan>=avGreedyMakespan) flag1000 = false;
				// Print average makespan for the current N, for each algorithm:
				System.out.println("The average makespan for "+n+" procedures using greedy algorithm is: "+
				avGreedyMakespan);
				System.out.println("The average makespan for "+n+" procedures using greedy decreasing algorithm is: "+
				avGreedyDecreasingMakespan);
				tempPercentage = ((double)(avGreedyMakespan - avGreedyDecreasingMakespan))/avGreedyMakespan*100.0;
				String conclusion = (avGreedyDecreasingMakespan<avGreedyMakespan)?"decreasing":"";
				System.out.println("Greedy "+conclusion+" algorithm is faster by "+tempPercentage+" %, for "+n+" procedures \n");
				percentage += tempPercentage;
			}
		}	
		// Reach final conclusion:
		if (flag100 && flag500 && flag1000) System.out.println("CONCLUSION: greedy decreasing algorithm is faster by "+percentage/3+" %.");
		else if(!flag100 && !flag500 && !flag1000) System.out.println("CONCLUSION: greedy algorithm is faster.by "+(-1)*percentage/3+" %.");
		else  System.out.println("No conclusion can be extracted.");
	}
}