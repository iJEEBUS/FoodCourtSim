package foodCourtSim;

import java.awt.*;
import javax.swing.*;

/*************************************************************************************
 *	This class creates an instance of the GUIsimulation and helps to 
 * handle the logic of the simulation. Also used to update the visual aid.
 * 
 * @author Ron, Patryk, Mitch, Tyler
 *
 ************************************************************************************/
public class GUIsimulation extends JPanel{
	/** 2D array to handle the colors to be outputte on the visual aid */
	private Color[][] foodCourt;

	/** Instance of a Sim class */
	Sim s = new Sim();

	/** The size of the visual aid */
	private int rows, columns, size = 10;

	/** The data that is used to run the simulation*/
	private int numEateries, numCheckouts, numTicksNextPerson, 
	averageEaterieTime, averageCheckOutTime, averageLeaveTime, runTime;

	/** Values for the size of the panels */
	private int Yc, Ye, Xe, Xc;

	/** CheckOutQ to keep track of all the customers */
	private CheckOutQ Q;

	/** Array for the customers that are in the eatery */
	private Eatery[] eateries;

	/** Array for the customers that are in the checkout line */
	private CheckOut[] checkouts;

	/** Instance of a person to create more */
	private PersonProducer produce;

	/** Main clock to handle all of the events with each tick */
	private Clock clk;

	/** Panel to be used to hold information */
	private JPanel infoPanel;

	/** Used in the JOption pane to get user data */
	private JTextField eatBox;
	private JTextField checkBox;
	private JTextField nextTimeBox;
	private JTextField eatTimeBox;
	private JTextField checkTimeBox;
	private JTextField leaveTimeBox;
	private JTextField totTimeBox;

	/** Used in the JOption pane to get user data */
	private JLabel eat;
	private JLabel check;
	private JLabel newPerson;
	private JLabel toGetFood;
	private JLabel toCheckOut;
	private JLabel toLeaveLine;
	private JLabel totalTime;

	/** If this is the first run of the sim then this will be true */
	private boolean firstRun;

	/** Color of the eateries are to be gray */
	private Color eaterieColor = Color.GRAY;

	/** Color of the checkouts are to be gray */
	private Color checkoutColor = Color.GRAY;

	/*************************************************************************************
	 * Constructor for the GUIsimulation. Sets the simulation to a cleared mode.
	 * 
	 * @param width the width of the GUI
	 * @param height the height of the GUI
	 ************************************************************************************/
	public GUIsimulation(int width, int height) {
		this.rows = height/10;
		this.columns = width/10;
		setPreferredSize(new Dimension(columns*size, rows*size));

		foodCourt = new Color[rows][columns];

		setFoodCourt();
		defaultValues();
		fillCheckouts();
		fillEateries();

		getInfoCreater();

		firstRun = true;
	}

	/*************************************************************************************
	 * Sets the visual aid food court to blank
	 ************************************************************************************/
	private void setFoodCourt() {
		for(int i = 0; i < columns; i++)
			for(int j = 0; j < rows; j++)
				foodCourt[j][i] = Color.white;
	}

	/*************************************************************************************
	 * Adds all of the needed customers to the clock so an action
	 * can be performed.
	 ************************************************************************************/
	private void setClock() {
		clk = new Clock();

		clk.add(produce);
		for(int i = 0; i < numEateries; i++)
			clk.add(eateries[i]);
		for(int j = 0; j < numCheckouts; j++)
			clk.add(checkouts[j]);
	}

	/*************************************************************************************
	 * Sets the default values that will be used in the simulation unless they
	 * are changed.
	 ************************************************************************************/
	private void defaultValues() {
		rows = 50;
		columns = 70;
		numEateries = 3;
		numCheckouts = 2;
		numTicksNextPerson = 10;
		averageEaterieTime = 60;
		averageCheckOutTime = 40;
		averageLeaveTime = 90;
		runTime = 1000;
	}

	/*************************************************************************************
	 * Sets all of the eateries and checkouts while producing a single person
	 * to be used in the simulation
	 ************************************************************************************/
	private void setInfo() {
		Q = new CheckOutQ();

		eateries = new Eatery[numEateries];
		checkouts = new CheckOut[numCheckouts];

		for(int i = 0; i < numEateries; i++)
			eateries[i] = new Eatery(Q);

		for(int i = 0; i < numCheckouts; i++)
			checkouts[i] = new CheckOut(Q);

		produce = new PersonProducer(eateries, checkouts, numTicksNextPerson,
				averageEaterieTime, averageCheckOutTime, averageLeaveTime);
	}

	/*************************************************************************************
	 * Opens the JOptionPane that allows the user to enter specific data to be used
	 ************************************************************************************/
	public void getInfo() {
		try {
			int result = JOptionPane.showConfirmDialog(null, infoPanel, 
					"Please enter newRows, columns and winning value.", JOptionPane.OK_CANCEL_OPTION);

			if (result == JOptionPane.OK_OPTION) {
				numEateries = Integer.parseInt(eatBox.getText());
				numCheckouts = Integer.parseInt(checkBox.getText());
				numTicksNextPerson = Integer.parseInt(nextTimeBox.getText());
				averageEaterieTime = Integer.parseInt(eatTimeBox.getText());
				averageCheckOutTime = Integer.parseInt(checkTimeBox.getText());
				averageLeaveTime = Integer.parseInt(leaveTimeBox.getText());
				runTime = Integer.parseInt(totTimeBox.getText());
			}

			if(result == JOptionPane.CANCEL_OPTION)
				throw new IllegalArgumentException();
		}

		catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Info unchanged due error");
		}

		fillEateries();
		fillCheckouts();
	}

	/*************************************************************************************
	 * Handles the layout of the JOptionPane that is created in the getInfo() method
	 ************************************************************************************/
	private void getInfoCreater() {
		eatBox = new JTextField( 5);
		checkBox = new JTextField( 5);
		nextTimeBox = new JTextField( 5);
		eatTimeBox = new JTextField( 5);
		checkTimeBox = new JTextField( 5);
		leaveTimeBox = new JTextField( 5);
		totTimeBox = new JTextField(5);

		infoPanel = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 0, 5, 5);
		infoPanel.add(new JLabel("Seconds to the Next Person"), gbc);

		gbc.gridy = 1;
		infoPanel.add(new JLabel("Average Seconds per cashier"), gbc);

		gbc.gridy = 2;
		infoPanel.add(new JLabel("Total time in seconds"), gbc);

		gbc.gridy = 3;
		infoPanel.add(new JLabel("Average Seconds per Eatery"), gbc);

		gbc.gridy = 4;
		infoPanel.add(new JLabel("Seconds Before Person leaves"), gbc);

		gbc.gridy = 5;
		infoPanel.add(new JLabel("Number of Eateries"), gbc);

		gbc.gridy = 6;
		infoPanel.add(new JLabel("Number of Check Outs"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		infoPanel.add(nextTimeBox, gbc);

		gbc.gridy = 1;
		infoPanel.add(checkTimeBox, gbc);

		gbc.gridy = 2;
		infoPanel.add(totTimeBox, gbc);

		gbc.gridy = 3;
		infoPanel.add(eatTimeBox, gbc);

		gbc.gridy = 4;
		infoPanel.add(leaveTimeBox, gbc);

		gbc.gridy = 5;
		infoPanel.add(eatBox, gbc);

		gbc.gridy = 6;
		infoPanel.add(checkBox, gbc);

		infoPanel.setPreferredSize(new Dimension(300,350));
	}

	/*************************************************************************************
	 * When a tick occurs this method is run which updates the visual aid.
	 * One tick in the simulation represents one second in real life.
	 ************************************************************************************/

	public void oneTick() {
		if(firstRun == true) {
			setInfo();
			setClock();
		}

		firstRun = false;

		clk.oneTick();

		clearBoard();

		for(int i = 1; i <= numEateries; i++)
			for(int j = 0; j < columns - Xe; j++) {
				foodCourt[(Ye*i)+2][Xe + j] = eateries[i-1].getColorIndex(j);
			}

		for(int i = 0; i < (Xe-2) - (Xc+6); i++)
			foodCourt[rows/2][(Xc+6) + i] = Q.getColorIndex(i);

		for(int i = 0; i < numCheckouts; i++)
			foodCourt[(Yc*(i+1))+2][Xc] = checkouts[i].getColoratCheckout();

		repaint();
	}


	/*************************************************************************************
	 * Paints the squares onto the visual aid.
	 ************************************************************************************/
	public void paintComponent(Graphics g) {
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < columns; c++) {
				g.setColor(foodCourt[r][c]);
				g.fillRect(c*size, r*size, size, size);
			}
	}

	/*************************************************************************************
	 * Clears the visual aid board to reset the simulation.
	 ************************************************************************************/
	private void clearBoard() {
		for(int r = 0; r < rows; r++)
			for(int c = columns; c < columns; c++)
				if(foodCourt[r][c] != eaterieColor || foodCourt[r][c] != checkoutColor)
					foodCourt[r][c] = Color.WHITE;
	}

	/*************************************************************************************
	 * Fills the eateries in the visual aid with the correct type of customers
	 ************************************************************************************/
	private void fillEateries() {
		Ye = rows / (numEateries + 1);
		Xe = 2*columns/3;

		for(int i = 0; i < rows; i++)
			if(foodCourt[i][2*columns/3] == eaterieColor)
				addEaterySim(i+1, Xe,Color.WHITE);

		for(int i = 1; i <= numEateries; i++) {
			addEaterySim(Ye*i, Xe,eaterieColor);
		}	
	}

	/*************************************************************************************
	 * Adds an eatery to the simulations visual aid
	 ************************************************************************************/
	private void addEaterySim(int Y, int X, Color color) {
		foodCourt[Y-1][X] = color;
		foodCourt[Y-1][X+1] = color;
		foodCourt[Y-1][X+2] = color;
		foodCourt[Y-1][X+3] = color;
		foodCourt[Y][X] = color;
		foodCourt[Y][X+1] = color;
		foodCourt[Y][X+2] = color;
		foodCourt[Y][X+3] = color;
		foodCourt[Y+1][X] = color;
		foodCourt[Y+1][X+1] = color;
		foodCourt[Y+1][X+2] = color;
		foodCourt[Y+1][X+3] = color;	
	}

	/*************************************************************************************
	 * Fills the checkouts in the visual aid with the correct type of customer
	 ************************************************************************************/
	private void fillCheckouts() {
		Yc = rows / (numCheckouts + 1);
		Xc = columns/8;

		for(int i = 0; i < rows; i++)
			if(foodCourt[i][columns/8] == eaterieColor)
				addCheckoutSim(i+1, Xc,Color.WHITE);

		for(int i = 1; i <= numCheckouts; i++) {
			addCheckoutSim(Yc*i, Xc,checkoutColor);
		}
	}

	/*************************************************************************************
	 * Adds a checkout line to the simulations visual aid
	 ************************************************************************************/
	private void addCheckoutSim(int Y, int X, Color color) {
		foodCourt[Y-1][X] = color;
		foodCourt[Y-1][X+1] = color;
		foodCourt[Y-1][X+2] = color;
		foodCourt[Y-1][X+3] = color;
		foodCourt[Y][X] = color;
		foodCourt[Y][X+1] = color;
		foodCourt[Y][X+2] = color;
		foodCourt[Y][X+3] = color;
		foodCourt[Y+1][X] = color;
		foodCourt[Y+1][X+1] = color;
		foodCourt[Y+1][X+2] = color;
		foodCourt[Y+1][X+3] = color;	
	}

	/*************************************************************************************
	 * Updates and increments the number of eateries by one.
	 ************************************************************************************/
	public void addEatery() {
		numEateries++;
		setInfo();
		fillEateries();
	}

	/*************************************************************************************
	 * Updates and increments the number of checkouts by one.
	 ************************************************************************************/
	public void addCheckout() {
		numCheckouts++;
		setInfo();
		fillCheckouts();
	}

	/*************************************************************************************
	 * Updates and decrements the number of eateries by one.
	 ************************************************************************************/
	public void removeEatery() {
		if(numEateries != 0) {
			numEateries--;
			setInfo();
			fillEateries();
		}
	}

	/*************************************************************************************
	 * Updates and decrements the number of checkouts by one.
	 ************************************************************************************/
	public void removeCheckout() {
		if(numCheckouts != 0) {
			numCheckouts--;
			setInfo();
			fillCheckouts();
		}
	}

	/*************************************************************************************
	 * Resets the simulation information and gets it ready for another simulation
	 ************************************************************************************/
	public void reset() {
		setFoodCourt();
		fillEateries();
		fillCheckouts();
		setInfo();
		firstRun = true;
	}

	/*************************************************************************************
	 * Returns true of the number of ticks is equal to the run time and returns
	 * false if they are not equal so the simulation will continue running
	 * @param ticks the number of current ticks in the simulation
	 * @return boolean true if the sim should end, false if it should continue
	 ************************************************************************************/
	public boolean checkTicks(int ticks) {
		if(ticks == runTime)
			return true;

		return false;
	}

	/*************************************************************************************
	 * Displays the inputed panel on the GUI
	 * 
	 * @param panel the JPanel to be displayed
	 ************************************************************************************/
	public void display(JPanel panel) {
		Font textFont = new Font("Helvetica", Font.BOLD, 16);

		eat = new JLabel("Eateries: " + numEateries);
		eat.setFont(textFont);

		check = new JLabel("Check Outs: " + numCheckouts);
		check.setFont(textFont);

		newPerson = new JLabel("New Person: " + numTicksNextPerson + "s");
		newPerson.setFont(textFont);

		toGetFood = new JLabel("To Get Food: " + averageEaterieTime + "s");
		toGetFood.setFont(textFont);

		toCheckOut = new JLabel("To Check Out: " + averageCheckOutTime + "s");
		toCheckOut.setFont(textFont);

		toLeaveLine = new JLabel("To Leave Line: " + averageLeaveTime + "s");
		toLeaveLine.setFont(textFont);

		totalTime = new JLabel("Total Time: " + runTime + "s");
		totalTime.setFont(textFont);

		panel.add(eat);
		panel.add(check);
		panel.add(newPerson);
		panel.add(toGetFood);
		panel.add(toCheckOut);
		panel.add(toLeaveLine);
		panel.add(totalTime);
	}

	/*************************************************************************************
	 * Updates the information panel
	 ************************************************************************************/
	public void redisplay() {
		eat.setText("Eateries: " + numEateries);
		check.setText("Check Outs: " + numCheckouts);
		newPerson.setText("New Person: " + numTicksNextPerson + "s");
		toGetFood.setText("To Get Food: " + averageEaterieTime + "s");
		toCheckOut.setText("To Check Out: " + averageCheckOutTime + "s");
		toLeaveLine.setText("To Leave Line: " + averageLeaveTime + "s");
	}

	/*************************************************************************************
	 * Returns the number of ticks until a new customer is added
	 * 
	 * @return int how many seconds before another person enters
	 ************************************************************************************/
	public int getNextTime() {
		return numTicksNextPerson;
	}

	/*************************************************************************************
	 * Returns the average amount of time that is spent in the checkout line
	 * 
	 * @return int average time spent at the checkout
	 ************************************************************************************/
	public int getAvgTimeCashier() {
		return averageCheckOutTime;
	}

	/*************************************************************************************
	 * Returns the total amount of time that it took for a person to complete their
	 * trip
	 * 
	 * @return int total time it took to complete a visit
	 ************************************************************************************/
	public int getTotalTime() {
		return averageCheckOutTime + averageEaterieTime;
	}

	/*************************************************************************************
	 * Returns the average amount of time that is spent in an eatery
	 * 
	 * @return int average time spent at an eatery
	 ************************************************************************************/
	public int getAvgEateryTime() {
		return averageEaterieTime;
	}

	/*************************************************************************************
	 * Returns the average amount of time that it took for a customers to leave
	 * 
	 * @return int average time until customer leaves
	 ************************************************************************************/
	public int getLeaveTime() {
		return averageLeaveTime;
	}

	/*************************************************************************************
	 * Returns the number of eateries that are in the simulation
	 * 
	 * @return int number of eateries in the simulation
	 ************************************************************************************/
	public int getNumEateries() {
		return numEateries;
	}

	/*************************************************************************************
	 * Returns the number of checkouts that are in the simulation
	 * 
	 * @return int number of checkouts in the simulation
	 ************************************************************************************/
	public int getNumCashiers() {
		return numCheckouts;
	}

	/*************************************************************************************
	 * Returns the entire run time of the simulation
	 * 
	 * @return int the entire run time of the simulation
	 ************************************************************************************/
	public int getRunTime() {
		return runTime;
	}

	/********************************************************************************
	 * Returns the amount of customers that were put through the
	 * checkout lines
	 * @return int the amount of customers that made it through
	 *******************************************************************************/
	public int getThroughput() {
		int throughput = 0;
		if (checkouts.length > 0) {
			for (int i = 0; i < checkouts.length; i++) {
				throughput = checkouts[i].getThroughPut();
			}
		}
		return throughput;
	}

	/********************************************************************************
	 * Returns the amount of customers that left either the eatery or checkout lines.
	 * 
	 * @return int the amount of customers that left
	 *******************************************************************************/
	public int getLeftLine() {
		int leftLine = 0;
		if (eateries.length > 0) {
			for (int i  = 0; i < eateries.length; i++) {
				leftLine += eateries[i].getLeftLine();
			}
		}
		if (checkouts.length > 0) {
			for (int j = 0; j < checkouts.length; j++) {
				leftLine += checkouts[j].getLeftLine();
			}
		}
		return leftLine;
	}

	/********************************************************************************
	 * Returns the amount of customers that are still in a line.
	 * 
	 * @return int the amount of customers still in line
	 *******************************************************************************/
	public int getInLine() {
		int in_line = 0;
		if (eateries.length > 0) {
			for (int i  = 0; i < eateries.length; i++) {
				in_line += eateries[i].getLeft();
			}
		}
		if (checkouts.length > 0) {
			for (int j = 0; j < checkouts.length; j++) {
				in_line += checkouts[j].getLeft();
			}
		}
		return in_line;
	}


	/********************************************************************************
	 * Returns the average time for a person to complete their visit and checkout
	 * 
	 * @return int the average amount of time to complete visit
	 *******************************************************************************/
	public int getAvgTotalTime() {
		int total_time = 0;

		for(int i = 0; i < checkouts.length; i++) {
			total_time += checkouts[i].averageTotalTime();
		}

		return total_time / checkouts.length;
	}

	/********************************************************************************
	 * Returns the max length that the cashier lines reach throughout the simulation
	 * 
	 * @return int the max length of the cashier line reached
	 *******************************************************************************/
	public int getMaxLength() {
		int max_length = 0;

		if (checkouts.length > 0) {
			for (int i = 0; i < checkouts.length - 1; i++) {
				if (checkouts[i].getMaxLength() >= checkouts[i++].getMaxLength()) {
					max_length = checkouts[i].getMaxLength();
				}
			}
		}
		return max_length;
	}

	/********************************************************************************
	 * Returns the number of special needs customers who completely 
	 * finished their trip
	 * 
	 * @return int number of special needs who completed trips
	 *******************************************************************************/
	public int getNumSpecial() {
		int special = 0;
		if (eateries.length > 0) {
			for (int i = 0; i < eateries.length; i ++) {
				special += eateries[i].getNumSpecial();
			}
		}
		return special;
	}

	/********************************************************************************
	 * Returns the number of limited time customers who completely 
	 * finished their trip
	 * 
	 * @return int number of limited timers who completed trips
	 *******************************************************************************/
	public int getNumLimited() {
		int limited = 0;
		if (eateries.length > 0) {
			for (int j = 0; j < eateries.length; j++) {
				limited += eateries[j].getNumLimited();
			}
		}
		return limited;
	}

	/********************************************************************************
	 * Returns the average time that it took for a special needs customer to 
	 * complete their trip.
	 * 
	 * @return int special needs average trip time
	 *******************************************************************************/
	int special_people = 0;
	int special_needs_time = 0;
	int avg_timeS = 0;


	public int getAvgTimeSpecial() {

		if (checkouts.length > 0) {
			for (int i = 0; i < checkouts.length; i++) {
				if(checkouts[i].getColoratCheckout().equals(Color.green)) {
					avg_timeS += checkouts[i].getTotalTime();
					special_people++;
				}
			}
		}
		if (special_people > 0) 
			special_needs_time = (avg_timeS / special_people);
		return special_needs_time;
	}

	/********************************************************************************
	 * Returns the average time that it took for a limited time customer to 
	 * complete their trip.
	 * 
	 * @return int limited timers average trip time
	 *******************************************************************************/
	int limited_people = 0;
	int limited_time = 0;
	int avg_timeL = 0;

	public int getAvgTimeLimited() {

		if (checkouts.length > 0) {
			for (int i = 0; i < checkouts.length; i++) {
				if(checkouts[i].getColoratCheckout().equals(Color.blue)) {
					avg_timeL += checkouts[i].getTotalTime();
					limited_people++;
				}
			}
		}
		if (limited_people > 0)
			limited_time = (avg_timeL / limited_people);
		return limited_time;
	}
}