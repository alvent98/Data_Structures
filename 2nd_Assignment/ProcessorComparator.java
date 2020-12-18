import java.util.Comparator;

final class ProcessorComparator implements Comparator<Processor> {

	@Override
	public int compare(Processor o1,Processor o2) {
		if(o1.getActiveTime() < o2.getActiveTime()){
			return 1;
		}
		else if(o1.getActiveTime() == o2.getActiveTime()){
			return 0;
		}
		else{
			return -1;
		}
	}
	
}
