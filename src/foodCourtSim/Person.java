/**
 * 
 */
package Project_4;

public class Person {
	private int tickTime;  // master clock time
	protected double eateryTime;  // time it takes to order food at eatery
	protected double checkOutTime;
	
	public Person(int tickTime, double eateryTime, double checkOutTime) {
		this.tickTime = tickTime;
		this.eateryTime = eateryTime;
		this.checkOutTime = checkOutTime;
	}

	public double getCheckOutTime() {
		return checkOutTime;
	}

	public double getEateryTime() {
		return eateryTime;
	}
		
	public int getTickTime() {
		return tickTime;
	}
	
	public int getTotalTime() {
		return (int) (checkOutTime + eateryTime + tickTime);
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
}
