/**
 * 
 */
package Project_4;

public class Person {
	private int tickTime;  // master clock time
	protected double eateryTime;  // time it takes to order food at eatery
	protected double checkOutTime;
	protected double leaveTime;
	
	public Person(int tickTime, double eateryTime, double checkOutTime, double leaveTime) {
		this.tickTime = tickTime;
		this.eateryTime = eateryTime;
		this.checkOutTime = checkOutTime;
		this.leaveTime = leaveTime + tickTime;
	}

	public double getCheckOutTime() {
		return checkOutTime;
	}
	
	public double getLeaveTime() {
		return leaveTime;
	}

	public double getEateryTime() {
		return eateryTime;
	}
		
	public int getTickTime() {
		return tickTime;
	}

	public void setCheckOutTime(double checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}
	
	public void setFinalTime(int tickTime) {
		this.tickTime = tickTime - this.tickTime;
	}

	public void setEateryTime(double time) {
		this.eateryTime = time;
	}
	
	public void setLeaveTime(double time) {
		this.leaveTime = time;
	}
}
