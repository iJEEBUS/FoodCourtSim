package foodCourtSim;

import java.awt.Color;

/*************************************************************************************
 * This class handles all of the logic that places/removes customers from 
 * the checkout line by making using of the event() method, which relies 
 * on the clock ticks.
 * 
 * @author Ron, Patryk, Mitch, Tyler
 *
 ************************************************************************************/
public class CheckOut implements ClockListener {

	/** Time until next person arrives */
	private int timeOfNextEvent = 0;
	
	/** The person at the checkout */
	private Person person;   
	
	/** The amount of full trips completed by customers */
	private int completed = 0;
	
	/** The total time of the simulation */
	private int totalTime = 0;
	
	/** The number of people in the simulation */
	private int numPeople = 0;
	
	/** CheckOutQ to hold customers at the checkout */
	private CheckOutQ Q;
	
	/** How many customers left the line */
	private int leftLine = 0;
	
	/** Color of the person */
	private Color C;
	
	/** Max length achieved by a line */
	private int max_length = 0;
	
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
			C = Color.white;
			
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
				
				C = person.getColor();
				
				if (C.equals(Color.green)) {
				}
				
				if (C.equals(Color.blue)) {
				}
				
				// the time that the next event should occur
				timeOfNextEvent = tick + (int) (person.getCheckOutTime() + 1);

				// the total amount of time that has elapsed
				totalTime = totalTime + (tick - person.getTickTime());

				// the number of people who went through the line
				numPeople++;

				// the number of people who completed the line
				completed++;
				
				// the max length achieved by the line
				max_length = Q.maxQ();
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
		if (numPeople == 0)
			return 0;
		return totalTime/numPeople;
	}

	/********************************************************************************
	 * Returns how many people left the line before being served.
	 * 
	 * @return int how many people left the line
	 *******************************************************************************/
	public int getLeftLine() {
		return leftLine;
	}
	
	/********************************************************************************
	 * Returns the max length of the checkout queues
	 * 
	 * @return int max length of the checkout queues
	 *******************************************************************************/
	public int getMaxLength() {
		return max_length;
	}
	
	/********************************************************************************
	 * Get the total amount of time it took a person to complete their visit
	 * 
	 * @return int total time to complete visit
	 *******************************************************************************/
	public int getTotalTime() {
		return totalTime;
	}
	
	/********************************************************************************
	 * Returns the color of the person at checkout
	 * @return Color color of person checking out
	 *******************************************************************************/
	public Color getColoratCheckout() {
		return C;
	}
}