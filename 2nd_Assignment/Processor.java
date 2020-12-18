public class Processor implements Comparable<Processor> {
	private static int numOfProssesors=0;
	private int id;
	private IntDoubleEndedQueueImplArray processed_jobs;
	// Constructor
	public Processor() { 
		id = ++numOfProssesors;
		processed_jobs = new IntDoubleEndedQueueImplArray("Id: "+id);
	}
	// Getters
	public int getId() { return id; }
	public int getActiveTime() {
		int time = 0;
		if(processed_jobs.isEmpty()) return time;
		for(int i = 0;i<processed_jobs.size();i++) {
			time += processed_jobs.get(i);
		}
		return time;
	}
	
	public void addEndedProcedure(int time) {
		processed_jobs.addLast(time);
	}
	public void printProcessedJobs() {
		processed_jobs.print(System.out);
	}
	public int compareTo(Processor a) {
		if(this.getActiveTime()>a.getActiveTime()) {
			return 1;
		} else if(this.getActiveTime()<a.getActiveTime()) {
			return -1;
		} else {
			return 0;
		}
	}	
}