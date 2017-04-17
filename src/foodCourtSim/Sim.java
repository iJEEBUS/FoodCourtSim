package foodCourtSim;

import java.awt.Dialog;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * The main class that is used to run the Food Court Simulation. This program
 * will allow managers and business owners to optimize how they use their
 * employees
 * 
 * @author Ron
 */
public class Sim {

	/** Used to set the size of the GUI */
	private int simY = 500, simX = 700;
	
	/** Instance of GUI to scrape inputted info */
	GUIsimulation sim = new GUIsimulation(simX, simY);
	
	/** How many people successfully completed their visits */
	private int throughput;
	
	/** Average time it took for every person to complete their visit */
	private int avg_time;
	
	/** The amount of people who left the line */
	private int people_who_left;
	
	/** The max size reached by the queue */
	private int max_queue_length;
	
	/** The total possible number of people that could complete their trip */
	int numPeople;
	
	/** The size of the Queue at the end of the simulation */
	int left_in_line;
	
	
	public void run(int seconds_to_next_person, int avg_time_cashier, 
			int total_time, int avg_eatery_time, 
			int leave_time, int num_eateries){
		
		// the number of eateries to be used
		int numEateries = num_eateries;
		
		// the number of checkout lines to be used
		int numCheckOuts = 2;

		// create a new line for the check out
		CheckOutQ q = new CheckOutQ();
		
		// create a new instance of a clock
		Clock clk = new Clock();
		
		// array that has specified amount of eateries-
		Eatery[] eateryList = new Eatery[numEateries];	//array of eateries so multiple can be made
		CheckOut[] checkoutList = new CheckOut[numCheckOuts];
		
		// adds the specified number of eateries to the array
		for(int k = 0; k < numEateries; k++) {
			eateryList[k] = new Eatery(q);		//adds an eatery to the array
			clk.add(eateryList[k]);
		}

		// adds the specified number of checkouts to the array
		for(int c = 0; c < numCheckOuts; c++) {
			checkoutList[c] = new CheckOut(q);
			clk.add(checkoutList[c]);
		}
		
		// create a new instance of a Person to be used in the lines
		PersonProducer produce = new PersonProducer(eateryList, checkoutList, 20, 60, 10, 900);	
		
		// add the person to the clock so it is notified of ticks
		clk.add(produce);

		// start the simulation
		clk.run(10000);

		// the number of people that are currently in line
		int peopleInLine = q.size();
		
		// the amount of people that have completed their visit
		int throughPut = 0;
		
		// the total number of people that were added to the line
		this.numPeople = produce.getNumPeople();
		
		// the average time for a complete visit
		int avgTime = 0;
		
		// how many people left the line
		int leftLine = 0;

		// Loops through the eateries that are available to the customer and 
		// updates the number of people in the line, the max length of the 
		// queue and how many people have left the line
		for(int i = 0; i < numEateries; i++) {
			
			// updates the amount of people that are in the lines
			peopleInLine = peopleInLine + eateryList[i].getLeft();
			
			// if the number of people in line is greater than  the current max
			// length achieved by the queue then update the max length
			if (peopleInLine > max_queue_length)
				this.max_queue_length = peopleInLine;
			
			// get the number of people that have left the line and 
			// update the variable
			leftLine += eateryList[i].getLeftLine();
		}
		
		// Almost the same logic as above except the checkout lines are looped.
		// Updates the total amount of successful visits, the average time to 
		// complete a visit, and how many people have left the line.
		for(int j = 0; j < numCheckOuts; j++) {
			
			// updates the throughput of successful visits
			throughPut = throughPut + checkoutList[j].getThroughPut();
			
			// updates the average time to complete a visit
			avgTime = avgTime + checkoutList[j].averageTotalTime();
			
			// updates how many people have left the line
			leftLine += checkoutList[j].getLeftLine();
		}
		
		// update variables with the current information
		this.avg_time = avgTime = avgTime/numCheckOuts;
		this.throughput = throughPut;
		this.people_who_left = leftLine;
		this.left_in_line = q.size();
	}
	
	/********************************************************************************
	 * Returns the throughput for the current simulation
	 * @return
	 *******************************************************************************/
	public int getThroughput() {
		return throughput;
	}
	
	/********************************************************************************
	 * Returns the average time it took for all customers to complete their visits
	 * for the current simulation
	 * @return
	 *******************************************************************************/
	public int getAvgTime() {
		return avg_time;
	}
	
	/********************************************************************************
	 * Returns the amount of people who left the line for the current simulation
	 * @return
	 *******************************************************************************/
	public int getPeopleLeft() {
		return people_who_left;
	}
	
	/********************************************************************************
	 * Returns the maximum size reached by the queue for the current simulation
	 * @return
	 *******************************************************************************/
	public int getMaxQueueSize() {
		return max_queue_length;
	}
	
	/********************************************************************************
	 * Returns the amount of people that could have possibly completed their visits
	 * @return
	 *******************************************************************************/
	public int getNumPeople() {
		return numPeople;
	}
	
	/********************************************************************************
	 * Returns the number of people that are left in the line at the end of the sim.
	 * @return
	 *******************************************************************************/
	public int getLeftInLine() {
		return left_in_line;
	}
}