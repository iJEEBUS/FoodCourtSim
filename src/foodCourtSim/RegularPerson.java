package foodCourtSim;

import java.awt.Color;

public class RegularPerson extends Person{
	public RegularPerson(int tickTime, double eateryTime, double checkOutTime, double leaveTime) {
		super(tickTime, eateryTime, checkOutTime, leaveTime, Color.RED);
	}
}
