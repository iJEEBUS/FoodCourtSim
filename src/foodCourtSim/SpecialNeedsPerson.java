package foodCourtSim;
/*************************************************************************************
 * Represents a Person who takes more time to complete their time in the checkout 
 * line and at the Eatery.
 * 
 * @author Ron
 * @version 3.28.2017
 ************************************************************************************/
public class SpecialNeedsPerson extends Person{
	
	/********************************************************************************
	 * Constructor to create a SpecialNeedsPerson object
	 * 
	 * @param tickTime master clock time
	 * @param eateryTime how long the person takes at the Eatery
	 * @param checkOutTime how long it takes the person to checkout
	 * @param leaveTime how much time passes before the person leaves
	 *******************************************************************************/
	public SpecialNeedsPerson(int tickTime, double eateryTime, double checkOutTime, double leaveTime) {
		
		// calls the Person class and changes the necessary variables
		super(tickTime, eateryTime*4, checkOutTime*2, leaveTime*3);
	}
}
