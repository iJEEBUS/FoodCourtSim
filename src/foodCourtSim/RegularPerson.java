package foodCourtSim;
/*************************************************************************************
 * Represents a Person who has the normal amount of time to spend in line and at
 * the Eatery
 * 
 * @author Ron
 * @version 3.28.2017
 ************************************************************************************/
public class RegularPerson extends Person{
	
	/********************************************************************************
	 * Constructor to create a RegularPerson object
	 * 
	 * @param tickTime master clock time
	 * @param eateryTime how long the person takes at the Eatery
	 * @param checkOutTime how long it takes the person to checkout
	 * @param leaveTime how much time passes before the person leaves
	 *******************************************************************************/
	public RegularPerson(int tickTime, double eateryTime, double checkOutTime, double leaveTime) {
		
		// calls the Person class and changes the necessary variables
		super(tickTime, eateryTime, checkOutTime, leaveTime);
	}
}
