package foodCourtSim;
import java.util.ArrayList;
/**
 * Creates an Eatery where people in line are sent to. 
 * 
 * @author Ron
 */
public class Eatery implements ClockListener {
	
	/** ArrayList that will act as the line for the eatery */
	private ArrayList<Person> Q = new ArrayList<Person>();
	
	/** The time until the next should occur */
	private int timeOfNextEvent = 0;
	
	/** The max length of the line */
	private int maxQlength = 0;
	
	/** The person that is at the eatery */
	private Person person;   
	
	/** How many people completely went through the line */
	private int completed = 0;
	
	/** An ArrayList<Person> that will simulate a line for the checkout */
	private CheckOutQ checkOut;
	
	/** How many people left the line */
	private int leftLine = 0;
	
	/********************************************************************************
	 * Constructor for the Eatery class. Initializes checkOut to the argument.
	 * 
	 * @param q the checkout ArrayList to be used as the line
	 *******************************************************************************/
	public Eatery(CheckOutQ q) {
		checkOut = q;
	}
	
	/********************************************************************************
	 * Add a person to the line at the eatery
	 * 
	 * @param person the Person object to add to the list
	 *******************************************************************************/
	public void add (Person person)
	{
		Q.add(person);
		if (Q.size() > maxQlength)
			maxQlength = Q.size();
	}
	
	/********************************************************************************
	 * Runs the main logic of this class when the clock ticks
	 * 
	 * @param tick the amount of times to iterate this method
	 *******************************************************************************/
	public void event (int tick){
		
		// if the tick is >= the next event time
		if (tick >= timeOfNextEvent) {
			
			// if the line is not empty and the tick time is >= the leave time
			// then remove the person from the line
			if((Q.size() != 0) && (Q.get(0).getTickTime() >= Q.get(0).getLeaveTime())) {
				
				// remove the person from the line
				Q.remove(0);
				
				// increment the amount who left by one
				leftLine++;
			}
			
			// if there is a person in line
			// then add that person to the check out line
			if (person != null) { 	
				
				// add the person to the checkout line
				checkOut.add(person);    
				
			// send the person on
			person = null;				
			}
			
			// if the line has 1+ people in it then execute this
			if (Q.size() >= 1) {
				
				// remove the person from the line
				// but do not send them on yet
				person = Q.remove(0);
				
				// set the time that the next event is supposed to occur
				timeOfNextEvent = tick + (int) (person.getEateryTime() + 1); 
				
				// increment the amount of completed customers by one
				completed++;
			}	
		}
	}
	
	/********************************************************************************
	 * Returns how many people left the line
	 *******************************************************************************/
	public int getLeftLine() {
		return leftLine;
	}
	
	/********************************************************************************
	 * Returns the size of the line showing how many customers are left
	 *******************************************************************************/
	public int getLeft() {
		return Q.size();
	}
	
	/********************************************************************************
	 * Returns the maximum size of the line that was reached
	 *******************************************************************************/
	public int getMaxQlength() {
		return maxQlength;
	}

	/********************************************************************************
	 * Returns the total amount of people that completed their visit
	 *******************************************************************************/
	public int getThroughPut() {
		return completed;
	}
}
