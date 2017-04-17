package Project_4;

import java.awt.Color;

public class LimitedTimePerson extends Person{

	public LimitedTimePerson(int tickTime, double eateryTime, double checkOutTime, double leaveTime) {
		super(tickTime, ((int)eateryTime)*.5, ((int)checkOutTime)*.5, leaveTime, Color.blue);
	}
}
