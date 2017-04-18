package foodCourtSim;

/*************************************************************************************
 * Creates a clock that is used by the Eatery and Person classes and informs each
 * class when the clock has changed.
 * 
 * @author   Roger Ferguson
 ************************************************************************************/
public class Clock {
	
	/** Array of items that need to know the clock has changed */
	private ClockListener[] myListeners;  

	/** The number of listeners that make up the ClockListener[] */
	private int numListeners;

	/** The maximum number of objects that can be placed in the ClockListener[] */
	private int MAX = 100;

	/** The current time in the simulation */
	private int currentTime;

	/********************************************************************************
	 * Constructor the the Clock class. 
	 * Initializes numListeners to zero and creates a new ClockListener of size MAX
	 *******************************************************************************/
	public Clock() {
		numListeners = 0;
		currentTime = 0;
		myListeners = new ClockListener[MAX];
	}

	/********************************************************************************
	 * Runs through the created array and calls the .event() method for each object.
	 * 
	 * @param endingTime how long the method will run for
	 *******************************************************************************/
	public void run(int endingTime) {

		// while the currentTime is less than the endingTime
		for (int currentTime = 0; currentTime <= endingTime; currentTime++) {

			// while j is less than the amount of objects in the array
			for (int j = 0; j < numListeners; j++)

				// call the event method for the object
				myListeners[j].event(currentTime);

//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {}
		}
	}

	/********************************************************************************
	 * Calls the .event() method on all of the object that implement ClockListener
	 *******************************************************************************/
	public void oneTick() {
		// while j is less than the amount of objects in the array
		for (int j = 0; j < numListeners; j++)
			
			// call the event method for the object
			myListeners[j].event(currentTime);

		currentTime++;
	}

	/********************************************************************************
	 * Adds a new object to myListeners[]
	 * 
	 * @param cl the object that is to be added to the myListeners[]
	 *******************************************************************************/
	public void add(ClockListener cl) {
		// place the object at the end of the array
		myListeners[numListeners] = cl;

		// increment the number of listeners in the array
		numListeners++;
	}

	/********************************************************************************
	 * Returns the array holding the objects
	 *******************************************************************************/
	public ClockListener[] getMyListeners() {
		return myListeners;
	}

	/********************************************************************************
	 * Set the array to a specific array that is passed as an argument
	 *******************************************************************************/
	public void setMyListeners(ClockListener[] myListeners) {
		this.myListeners = myListeners;
	}

	/********************************************************************************
	 * Returns the number of listeners in the array
	 *******************************************************************************/
	public int getNumListeners() {
		return numListeners;
	}

	/********************************************************************************
	 * Set the number of listeners 
	 *******************************************************************************/
	public void setNumListeners(int numListeners) {
		this.numListeners = numListeners;
	}

	/********************************************************************************
	 * Returns the MAX size of the array
	 *******************************************************************************/
	public int getMAX() {
		return MAX;
	}
}