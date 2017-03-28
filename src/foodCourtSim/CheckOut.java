package Project_4;

public class CheckOut implements ClockListener {

	private int timeOfNextEvent = 0;
	private Person person;   // this is the person at the Checkout. 
	private int completed = 0;
	private int totalTime = 0;
	private int numPeople = 0;
	private CheckOutQ Q;
	private int leftLine = 0;
	
	public CheckOut(CheckOutQ q) {
		Q = q;
	}

	public void event (int tick){
		if (tick >= timeOfNextEvent) {
			
			if((Q.size() != 0) && (Q.get(0).getTickTime() >= Q.get(0).getLeaveTime())) {
				Q.remove(0);
				leftLine++;
			}
			
			if (Q.size() >= 1) {
				person = Q.remove(0);		// do not send this person as of yet, make them wait. 
				timeOfNextEvent = tick + (int) (person.getCheckOutTime() + 1);
				totalTime = totalTime + (tick - person.getTickTime());
				numPeople++;
				completed++;
			}	
		}
	}

	public int getThroughPut() {
		return completed;
	}
	
	public int getLeft() {
		return Q.size();
	}
	
	public int averageTotalTime() {
		return totalTime/numPeople;
	}
	
	public int getLeftLine() {
		return leftLine;
	}
}
