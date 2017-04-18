package foodCourtSim;

import java.awt.Color;
/*************************************************************************************
 * Helps to create the person that is limited on time.
 * 
 * @author Ron, Patryk, Mitch, Tyler
 *
 ************************************************************************************/

public class LimitedTimePerson extends Person{

	public LimitedTimePerson(int tickTime, double eateryTime, double checkOutTime, double leaveTime) {
		super(tickTime, ((int)eateryTime)*.5, ((int)checkOutTime)*.5, leaveTime, Color.blue);
	}
}
