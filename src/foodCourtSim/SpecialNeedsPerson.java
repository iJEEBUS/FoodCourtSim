package Project_4;

public class SpecialNeedsPerson extends Person{

	public SpecialNeedsPerson(int tickTime, double eateryTime, double checkOutTime) {
		super(tickTime*3, eateryTime*4, checkOutTime*2);
	}
}
