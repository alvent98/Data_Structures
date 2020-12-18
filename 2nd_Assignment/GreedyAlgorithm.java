public class GreedyAlgorithm {
	// Empty constructor
	public GreedyAlgorithm() {}	
	// returns the makespan of the given data
	public int runAlgorithm1(int[] array) {
		ProcessorComparator cmp = new ProcessorComparator();
		int numOfProcessors = array[0];
		int numOfProcedures = array[1];
		int remainProcesses = numOfProcedures;
		// Instantiation of the array with the procedures.
		int[] procedures_array = new int[numOfProcedures];
		// Initialization of the array with the procedures.
		for(int i=0;i<numOfProcedures;i++) procedures_array[i] = array[i+2];
		// The array with the processors.
		Processor[] processors_array = new Processor[numOfProcessors];
		// Instantiation of the array with the processors.
		for(int i=0;i<numOfProcessors;i++) processors_array[i] = new Processor();
		// A priority queue in order to choose the least loaded processor each time at O(1).
		MaxPQ<Processor> pq = new MaxPQ(numOfProcessors,cmp);
		// Initialize the processors with their first process. 
		for(int i=0;i<numOfProcessors && i<numOfProcedures;i++) {
			processors_array[i].addEndedProcedure(procedures_array[i]);
			remainProcesses--;
		}
		for(int i=0;i<numOfProcessors;i++) pq.insert(processors_array[i]);
		while(remainProcesses>0) {
			// Get the processor with the least weight:
			Processor selct = pq.getMax();
			// Add the next procedure to this processor.
			selct.addEndedProcedure(procedures_array[numOfProcedures - remainProcesses]);
			remainProcesses--;
			// Put the processor back at the priority queue.
			pq.insert(selct);
		}
		for(int i=0;i<numOfProcessors;i++) processors_array[i] = pq.getMax();
		// The most loaded processor is at the last element of array. (Actually getMax returns the min).
		int makespan = processors_array[numOfProcessors-1].getActiveTime();
		// if numOfProcedures is less than 100, print the prcessed jobs of each processors.
		if(numOfProcedures<100) {
			for(int i=0;i<numOfProcessors;i++) {
				Processor selct = processors_array[i];
				System.out.print("id: "+selct.getId()+", load="+selct.getActiveTime()+": ");
				selct.printProcessedJobs();
				System.out.println();
			}
		}
		return makespan;
	}
}