/**
 * 
 */
package foodCourtSim;

import java.awt.Color;

/*************************************************************************************
 *	Defines what a person is that is going to be used in the simulation
 * 
 * @author Ron, Patryk, Mitch, Tyler
 ************************************************************************************/
public class Person {
	/** Master tick time for the simulation */
	private int tickTime;  
	/** Time it takes to order food at an eatery */
	protected double eateryTime;  

	/** The average time to checkout */
	protected double checkOutTime;

	/** The average time for a customer to leave */
	protected double leaveTime;

	/** The color of the customer */
	protected Color color;

	/*************************************************************************************
	 * Constructor to create a new person
	 * 
	 * @param tickTime the time until another person is added to the sim
	 * @param eateryTime average time at the eatery
	 * @param checkOutTime average time at the checkout
	 * @param leaveTime average time until customer leaves
	 * @param color the color of the person
	 ************************************************************************************/
	public Person(int tickTime, double eateryTime, double checkOutTime, double leaveTime, Color color) {
		this.tickTime = tickTime;
		this.eateryTime = eateryTime;
		this.checkOutTime = checkOutTime;
		this.leaveTime = leaveTime + tickTime;
		this.color = color;
	}

	/*************************************************************************************
	 * Gets the color of the customer
	 * @return Color the color of the person
	 ************************************************************************************/
	public Color getColor() {
		return color;
	}

	/*************************************************************************************
	 * Gets the checkout time of a person
	 * 
	 * @return double time for person to checkout
	 ************************************************************************************/
	public double getCheckOutTime() {
		return checkOutTime;
	}

	/*************************************************************************************
	 * Gets the leave time for the customer
	 * 
	 * @return double time until person leaves the line
	 ************************************************************************************/
	public double getLeaveTime() {
		return leaveTime;
	}

	/*************************************************************************************
	 * Gets the average time for the customer at the eatery
	 * 
	 * @return double average time at eatery
	 ************************************************************************************/
	public double getEateryTime() {
		return eateryTime;
	}

	/*************************************************************************************
	 * Gets the current tick time
	 * 
	 * @return int current tick time
	 ************************************************************************************/
	public int getTickTime() {
		return tickTime;
	}

	/*************************************************************************************
	 * Sets the color of the person
	 * 
	 * @param color the color of the person desired
	 ************************************************************************************/
	public void setColor(Color color) {
		this.color = color;
	}

	/*************************************************************************************
	 * Sets the checkout time of the customer
	 * 
	 * @param checkOutTime average time to checkout
	 ************************************************************************************/
	public void setCheckOutTime(double checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	/*************************************************************************************
	 * Sets the current tick time
	 * 
	 * @param tickTime set tick time to this
	 ************************************************************************************/
	public void setTickTime(int tickTime) {
		this.tickTime = tickTime;
	}

	/*************************************************************************************
	 * Set the final time for the simulation
	 * @param tickTime current tick time
	 ************************************************************************************/
	public void setFinalTime(int tickTime) {
		this.tickTime = tickTime - this.tickTime;
	}

	/*************************************************************************************
	 * Sets the average time spent at the eatery
	 * 
	 * @param time time spent at the eatery
	 ************************************************************************************/
	public void setEateryTime(double time) {
		this.eateryTime = time;
	}

	/*************************************************************************************
	 * Set the amount of time that must pass for a person to leave the line
	 * 
	 * @param time time until person leaves
	 ************************************************************************************/
	public void setLeaveTime(double time) {
		this.leaveTime = time;
	}
}
