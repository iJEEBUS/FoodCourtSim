package Project_4;

/*************************************************************************************
 * This class handles all of the logic that places/removes customers from 
 * the checkout line by making using of the event() method, which relies 
 * on the clock ticks.
 * 
 * @author Ron
 *
 ************************************************************************************/
public class CheckOut implements ClockListener {

	private int timeOfNextEvent = 0;
	private Person person;   // this is the person at the Checkout. 
	private int completed = 0;
	private int totalTime = 0;
	private int numPeople = 0;
	private CheckOutQ Q;
	private int leftLine = 0;

	/********************************************************************************
	 * Constructor for the CheckOut class
	 * @param q the queue for the checkout line
	 *******************************************************************************/
	public CheckOut(CheckOutQ q) {
		Q = q;
	}

	/********************************************************************************
	 * This is the main logic of the class. It will remove people from the line if 
	 * they have been in line for too long and also keep track of how many people
	 * have completed their trip through the food court.
	 * 
	 * @param tick the amount of iterations to run the method
	 *******************************************************************************/
	public void event (int tick){
		if (tick >= timeOfNextEvent) {
			
			// if the queue is populated AND the tick time is >= the persons leave
			// time then remove them from the line and increment leftLine by one
			if((Q.size() != 0) && (Q.get(0).getTickTime() >= Q.get(0).getLeaveTime())) {
				Q.remove(0);
				leftLine++;
			}
			
			// if the size of the queue is >= one
			if (Q.size() >= 1) {
				// variable for the current person
				person = Q.remove(0);		// do not send this person as of yet, make them wait. 
				
				// the time that the next event should occur
				timeOfNextEvent = tick + (int) (person.getCheckOutTime() + 1);
				
				// the total amount of time that has elapsed
				totalTime = totalTime + (tick - person.getTickTime());
				
				// the number of people who went through the line
				numPeople++;
				
				// the number of people who completed the line
				completed++;
			}	
		}
	}
	/********************************************************************************
	 * Gets the throughput from the checkout line
	 * @return
	 *******************************************************************************/
	public int getThroughPut() {
		return completed;
	}

	/********************************************************************************
	 * Gets how many people are still in line
	 * @return
	 *******************************************************************************/
	public int getLeft() {
		return Q.size();
	}

	/********************************************************************************
	 * Gets the average time that it took for customers to get all the way 
	 * through the checkout line.
	 * @return
	 *******************************************************************************/
	public int averageTotalTime() {
		return totalTime/numPeople;
	}

	/********************************************************************************
	 * Returns how many people left the line before being served.
	 * @return
	 *******************************************************************************/
	public int getLeftLine() {
		return leftLine;
	}
}