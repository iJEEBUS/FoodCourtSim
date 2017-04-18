package foodCourtSim;

import java.awt.Color;

/*************************************************************************************
 * Helps to create the person that is a regular customer
 * 
 * @author Ron, Patryk, Mitch, Tyler
 *
 ************************************************************************************/
public class RegularPerson extends Person{
	public RegularPerson(int tickTime, double eateryTime, double checkOutTime, double leaveTime) {
		super(tickTime, eateryTime, checkOutTime, leaveTime, Color.RED);
	}
}
