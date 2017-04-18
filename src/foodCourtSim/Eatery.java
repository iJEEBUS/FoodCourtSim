package foodCourtSim;

import java.awt.Color;

/**
 * Creates an Eatery where people in line are sent to. 
 * 
 * @author Ron
 */
public class Eatery implements ClockListener {

	/** LinkedList that will act as the line for the eatery */
	public LinkedList<Person> Q = new LinkedList<Person>();

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
	
	/** How many of those being served are special needs */
	private int special_needs = 0;
	
	/** How many people being served are limited on time */
	private int limited_time = 0;

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
				Q.removeIndex(0);

				// increment the amount who left by one
				leftLine++;
			}

			// if there is a person in line
			// then add that person to the check out line
			if (person != null) { 	
				
				if (person.getColor().equals(Color.blue)) {
					limited_time++;
				}
				
				if (person.getColor().equals(Color.green)) {
					special_needs++;
				}
				// add the person to the checkout line
				checkOut.add(person);    

				// send the person on
				person = null;				
			}

			// if the line has 1+ people in it then execute this
			if (Q.size() >= 1) {

				
				
				// remove the person from the line
				// but do not send them on yet
				person = Q.removeIndex(0);

				// set the time that the next event is supposed to occur
				timeOfNextEvent = tick + (int) (person.getEateryTime() + 1); 

				// increment the amount of completed customers by one
				completed++;
			}	
		}
	}

	/********************************************************************************
	 * Returns how many people left the line
	 * 
	 * @return int how many customers left the line
	 *******************************************************************************/
	public int getLeftLine() {
		return leftLine;
	}

	/********************************************************************************
	 * Returns the size of the line showing how many customers are left
	 * 
	 * @return int how many customers are still in line
	 *******************************************************************************/
	public int getLeft() {
		return Q.size();
	}

	/********************************************************************************
	 * Returns the maximum size of the line that was reached
	 * 
	 * @return int maximum length of line reached
	 *******************************************************************************/
	public int getMaxQlength() {
		return maxQlength;
	}

	/********************************************************************************
	 * Returns the total amount of people that completed their visit
	 * 
	 * @return int number of completed visits
	 *******************************************************************************/
	public int getThroughPut() {
		return completed;
	}

	/********************************************************************************
	 * Returns the line to the Eatery as an array from a Linked-List form
	 * 
	 * @return Person[] array version of the Eatery line
	 *******************************************************************************/
	public Person[] getLine() {
		Person[] temp = new Person[Q.size()];

		for(int i = 0; i < Q.size(); i++)
			temp[i] = Q.get(i);

		if(temp == null)
			throw new IllegalArgumentException("No line");

		return temp;
	}

	/********************************************************************************
	 * Gets the number of special needs customers who complete their entire visit
	 * 
	 * @return int number of special needs customers who compete trip
	 *******************************************************************************/
	public int getNumSpecial() {
		return special_needs;
	}
	
	/********************************************************************************
	 * Gets the number of limited time customers go through the entire visit
	 * 
	 * @return int limited time customers who completed their visit
	 ********************************************************************************/
	public int getNumLimited() {
		return limited_time;
	}
	
	/********************************************************************************
	 * Returns the color of the person at a specified index
	 * 
	 * @param index person who's color you wish to know
	 * @return	Color the color of the person
	 *******************************************************************************/
	public Color getColorIndex(int index) {
		if(index >= Q.size())
			return Color.white;

		return Q.get(index).getColor();
	}
}