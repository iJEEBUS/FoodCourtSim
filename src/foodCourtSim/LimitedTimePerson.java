package Project_4;

public class LimitedTimePerson extends Person{

	public LimitedTimePerson(int tickTime, double eateryTime, double checkOutTime) {
		super(tickTime, ((int)eateryTime)*.5, ((int)checkOutTime)*.5);
	}
}
