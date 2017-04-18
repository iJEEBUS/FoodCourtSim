/**
 * 
 */
package foodCourtSim;

import java.awt.Color;

public class Person {
	private int tickTime;  // master clock time
	protected double eateryTime;  // time it takes to order food at eatery
	protected double checkOutTime;
	protected double leaveTime;
	protected Color color;

	public Person(int tickTime, double eateryTime, double checkOutTime, double leaveTime, Color color) {
		this.tickTime = tickTime;
		this.eateryTime = eateryTime;
		this.checkOutTime = checkOutTime;
		this.leaveTime = leaveTime + tickTime;
		this.color = color;
	}
	
	public Color getColor() {
		return color;
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

	public void setColor(Color color) {
		this.color = color;
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
