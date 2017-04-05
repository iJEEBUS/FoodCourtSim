package foodCourtSim;
/*************************************************************************************
 * Represents a Person who has a limited amount of time to spend at the Eatery/in line
 * 
 * @author Ron
 * @version 3.28.2017
 ************************************************************************************/
public class LimitedTimePerson extends Person{

	/********************************************************************************
	 * Constructor to create a SpecialNeedsPerson object
	 * 
	 * @param tickTime master clock time
	 * @param eateryTime how long the person takes at the Eatery
	 * @param checkOutTime how long it takes the person to checkout
	 * @param leaveTime how much time passes before the person leaves
	 *******************************************************************************/
	public LimitedTimePerson(int tickTime, double eateryTime, double checkOutTime, double leaveTime) {
		
		// calls the Person class and changes the necessary variables
		super(tickTime, ((int)eateryTime)*.5, ((int)checkOutTime)*.5, leaveTime);
	}
}
