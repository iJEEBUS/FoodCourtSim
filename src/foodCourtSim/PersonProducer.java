/**
 * 
 */
package foodCourtSim;

import java.util.Random;


public class PersonProducer implements ClockListener {

	/** How many ticks need to pass in order for the next person to arrive */
	private int nextPerson = 0;
	
	/** An array that will act like a line for an Eatery */
	private Eatery[] eatery;
	
	/** An array that will act like a line for the checkout*/
	private CheckOut[] checkout;
	
	/** How much time will pass until a new Person is added to the line */
	private int numOfTicksNextPerson;
	
	/** Average time for the people to spend at the Eatery */
	private int averageEateryTime;
	
	/** Average amount of time that it will take to checkout */
	private int averageCheckOutTime;
	
	/** Average time for person to complete visit */
	private int totalAverageTime = 0;
	
	/** The total amount of people that have been placed in an Eatery*/
	private int numPeople = 0;
	
	/** Average time until the Person leaves */
	private int averageLeaveTime;

	/** Random number generator */
	private Random r = new Random();

	/**
	 * Constructor for the PersonProducer class that will initialize the 
	 * local variables to the arguments.
	 * 
	 * @param eatery the list of possible eateries 
	 * @param checkout the list of checkout lines
	 * @param numOfTicksNextPerson number of seconds until another person is added
	 * @param averageEateryTime average amount of time a Person takes at an Eatery
	 * @param averageCheckOutTime average amount of time it takes to checkout
	 * @param leaveTime the time it takes for a person to leave the line uncompleted
	 */
	public PersonProducer(Eatery[] eatery, CheckOut[] checkout, int numOfTicksNextPerson, 
			int averageEateryTime, int averageCheckOutTime, int leaveTime) {
		
		// set the local variables equal to the arguments
		this.eatery = new Eatery[eatery.length];
		this.checkout = new CheckOut[checkout.length];

		// transfer all of the eatery objects
		for(int i = 0; i < eatery.length; i++)
			this.eatery[i] = eatery[i];
		
		// transfer all of the checkout line objects
		for(int k = 0; k < checkout.length; k++)
			this.checkout[k] = checkout[k];
		
		// set the local variables equal to the arguments
		this.numOfTicksNextPerson = numOfTicksNextPerson;
		this.averageEateryTime = averageEateryTime;
		this.averageCheckOutTime = averageCheckOutTime;
		this.averageLeaveTime = leaveTime;
		//r.setSeed(13);    // This will cause the same random number
	}
	
	/********************************************************************************
	 * Returns the average amount of time for all of the people 
	 *******************************************************************************/
	public int getAvgTime() {
		return (totalAverageTime/numPeople);
	}
	
	/********************************************************************************
	 * Returns how many people have been placed into an Eatery
	 *******************************************************************************/
	public int getNumPeople () {
		return numPeople;
	}


	/********************************************************************************
	 * Handles the main logic of this class and is called by the clock with 
	 * every tick.
	 * This generates a random eat, checkout, and leave time before passing them
	 * through to create either a new Person (RegularPerson, LimitedTimePerson, 
	 * or SpecialNeedsPerson) before placing that person at a random Eatery.
	 * 
	 * @param tick the amount of times to iterate this method
	 *******************************************************************************/
	public void event(int tick) {
		if (nextPerson <= tick) {
			
			// set the time for the next person to arrive near the 
			// specified amount from the PersonProducer() constructor
			nextPerson = (int) (tick + Math.max(0,numOfTicksNextPerson*0.5*r.nextGaussian() + numOfTicksNextPerson +.5));

			// make the times a number near the averages inputed from the PersonProducer() constructor
			int eatTime = (int) Math.max(0,averageEateryTime*0.5*r.nextGaussian() + averageEateryTime +.5);
			int checkTime = (int) Math.max(0,averageCheckOutTime*0.5*r.nextGaussian() + averageCheckOutTime +.5);
			int leaveTime = (int) Math.max(0,averageLeaveTime*0.5*r.nextGaussian() + averageLeaveTime +.5);
			
			// create a new person to add to an Eatery
			Person person;
			
			// generate random number 0-100
			int rNumber = (int)(Math.random() * 100);

			// if the number is < 10
			if(rNumber < 10)
				
				// create a new special needs person
				person = new SpecialNeedsPerson(tick, eatTime, checkTime, leaveTime);
			
			// if the number is < 30
			else if(rNumber < 30)
				
				// create a new limited time person
				person = new LimitedTimePerson(tick, eatTime, checkTime, leaveTime);
			
			else
				// create a new regular person
				person = new RegularPerson(tick, eatTime, checkTime, leaveTime);
			
			// place this person into a random Eatery
			eatery[(int)(Math.random()*eatery.length)].add(person);	//assigns person to a random eatery
			
			// increment the number of people that have 
			numPeople++;
		}
	}
}
