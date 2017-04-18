package foodCourtSim;

import java.awt.Color;

public class SpecialNeedsPerson extends Person{

	public SpecialNeedsPerson(int tickTime, double eateryTime, double checkOutTime, double leaveTime) {
		super(tickTime, eateryTime*4, checkOutTime*2, leaveTime*3, Color.green);
	}
}
