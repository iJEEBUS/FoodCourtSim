/**
 * 
 */
package foodCourtSim;

public class Person {
	private int tickTime;  // master clock time
	protected double eateryTime;  // time it takes to order food at eatery
	protected double checkOutTime;
	protected double leaveTime;
	
	/********************************************************************************
	 * Constructor to create a Person object
	 * 
	 * @param tickTime master clock time
	 * @param eateryTime how long the person takes at the Eatery
	 * @param checkOutTime how long it takes the person to checkout
	 * @param leaveTime how much time passes before the person leaves
	 *******************************************************************************/
	public Person(int tickTime, double eateryTime, double checkOutTime, double leaveTime) {
		this.tickTime = tickTime;
		this.eateryTime = eateryTime;
		this.checkOutTime = checkOutTime;
		this.leaveTime = leaveTime + tickTime;
	}

	/********************************************************************************
	 * Returns the amount of time it takes for this person to checkout
	 *******************************************************************************/
	public double getCheckOutTime() {
		return checkOutTime;
	}
	
	/********************************************************************************
	 * Returns the time at which the person will leave if still in line
	 *******************************************************************************/
	public double getLeaveTime() {
		return leaveTime;
	}

	/********************************************************************************
	 *  Returns the amount of time the person spends at the Eatery
	 *******************************************************************************/
	public double getEateryTime() {
		return eateryTime;
	}
	
	/********************************************************************************
	 * Returns the current clock tick time
	 *******************************************************************************/
	public int getTickTime() {
		return tickTime;
	}

	/********************************************************************************
	 * Set the checkout time through an argument
	 * 
	 * @param checkOutTime how long this person will take at the check out
	 *******************************************************************************/
	public void setCheckOutTime(double checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	/********************************************************************************
	 * Set the current tick time
	 * 
	 * @param tickTime the current tick time
	 *******************************************************************************/
	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}
	
	/********************************************************************************
	 * 
	 *******************************************************************************/
	public void setFinalTime(int tickTime) {
		this.tickTime = tickTime - this.tickTime;
	}

	/********************************************************************************
	 * Set the time for how long the person will take in the Eatery
	 * 
	 * @param time how much time the person will spend in the Eatery
	 *******************************************************************************/
	public void setEateryTime(double time) {
		this.eateryTime = time;
	}
	
	/********************************************************************************
	 * Set the time that will need to elapse before the person leaves the line
	 * 
	 * @param time the amount of time that needs to pass before the person leaves 
	 * the line
	 *******************************************************************************/
	public void setLeaveTime(double time) {
		this.leaveTime = time;
	}
}
