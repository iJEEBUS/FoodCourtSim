package foodCourtSim;

import java.awt.*;
import javax.swing.*;

public class GUIsimulation extends JPanel {
	private Person[][] foodCourt;

	private int rows, columns, size = 10;

	private int numEateries, numCheckouts, numTicksNextPerson, averageEaterieTime,
	averageCheckOutTime, averageLeaveTime, runTime;

	private CheckOutQ Q;

	private PersonProducer produce;

	private boolean isRunning = false;

	private JPanel infoPanel;

	private JTextField eatBox;
	private JTextField checkBox;
	private JTextField nextTimeBox;
	private JTextField eatTimeBox;
	private JTextField checkTimeBox;
	private JTextField leaveTimeBox;
	private JTextField totTimeBox;

	/********************************************************************************
	 * Constructor that creates the GUIsimulation
	 * @param width
	 * @param height
	 *******************************************************************************/
	public GUIsimulation(int width, int height) {
		this.rows = height/10;
		this.columns = width/10;
		foodCourt = new Person[rows][columns];
		Clock clk = new Clock();
		defaultValues();
		Q = new CheckOutQ();
		clk.add(produce);
		setPreferredSize(new Dimension(columns*size, rows*size));
		
		getInfoCreater();
	}
	
	/********************************************************************************
	 * Default values to be used in the simulation
	 *******************************************************************************/
	private void defaultValues() {
		rows = 50;
		columns = 70;
		numEateries = 3;
		numCheckouts = 2;
		numTicksNextPerson = 20;
		averageEaterieTime = 20;
		averageCheckOutTime = 20;
		averageLeaveTime = 900;
		runTime = 10000;
	}

	/********************************************************************************
	 * Parses the information that is entered by the user in the JOptionPane
	 *******************************************************************************/
	public void getInfo() {
		//JOptionPane to be used

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

		Eatery[] eateries = new Eatery[numEateries];
		CheckOut[] checkouts = new CheckOut[numCheckouts];

		for(int i = 0; i < numEateries; i++)
			eateries[i] = new Eatery(Q);

		for(int i = 0; i < numCheckouts; i++)
			checkouts[i] = new CheckOut(Q);

		produce = new PersonProducer(eateries, checkouts, numTicksNextPerson,
				averageEaterieTime, averageCheckOutTime, averageLeaveTime);
	}

	
	
	
	
	// Modify the size of the JTextFields in so that they are all the same size
	///////////////////////////////////////////////////////////////////
	private void getInfoCreater() {
		eatBox = new JTextField("" + numCheckouts);
		checkBox = new JTextField("" + numEateries);
		nextTimeBox = new JTextField("" + numTicksNextPerson);
		eatTimeBox = new JTextField("" + averageEaterieTime);
		checkTimeBox = new JTextField("" + averageCheckOutTime);
		leaveTimeBox = new JTextField("" + averageLeaveTime);
		totTimeBox = new JTextField("" + runTime);
		
		// attempt to set the size of the textfields to make them uniform
		Dimension d = new Dimension(20, 5);
		
		eatBox.setMinimumSize(d);
		checkBox.setMinimumSize(d);
		nextTimeBox.setMinimumSize(d);
		eatTimeBox.setMinimumSize(d);
		checkTimeBox.setMinimumSize(d);
		leaveTimeBox.setMinimumSize(d);
		totTimeBox.setMinimumSize(d);
		
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
		
		infoPanel.setPreferredSize(new Dimension(250,300));
	}

	/********************************************************************************
	 * Used to control the simulation
	 *******************************************************************************/
	public void oneTick() {
		//controls the simulation
	}

	/********************************************************************************
	 * Used for the creation of a visual aid that will show the actual movements
	 * of the lines
	 *******************************************************************************/
	public void paintComponent(Graphics g) {
		for(int row=0; row<this.rows; row++){
			for(int col=0; col<this.columns; col++){
				Person p = foodCourt[row][col];

				// set color to white if no critter here
				if(p == null){
					g.setColor(Color.WHITE);
					// set color to critter color   
//				}else{    
//					g.setColor(p.getColor());
				}

				// paint the location
				g.fillRect(col*size, row*size, size, size);
			}
		}
	}

	/********************************************************************************
	 * Sets the isRunning variable to true
	 *******************************************************************************/
	public void start() {
		isRunning = true;
	}

	/********************************************************************************
	 * Sets the isRunning variable to false
	 *******************************************************************************/
	public void stop() {
		isRunning = false;
	}

	/********************************************************************************
	 * Increment the eateries by one
	 *******************************************************************************/
	public void addEatery() {
		numEateries++;
	}

	/********************************************************************************
	 * Increment the checkouts by one
	 *******************************************************************************/
	public void addCheckout() {
		numCheckouts++;
	}

	/********************************************************************************
	 * Decrement eateries by one
	 *******************************************************************************/
	public void removeEatery() {
		numEateries--;
	}

	/********************************************************************************
	 * Decrement the checkouts by one
	 *******************************************************************************/
	public void removeCheckout() {
		numCheckouts--;
	}
	
	// these throw a Null Pointer Exception if they are empty

	/********************************************************************************
	 * Gets the time until the next person enters the line as entered by the user
	 * @return int
	 *******************************************************************************/
	public int getNextTime() {
		return Integer.parseInt(nextTimeBox.getText());
	}
	
	/********************************************************************************
	 * Gets the average time that is spent at each cashier as entered by the user
	 * @return int
	 *******************************************************************************/
	public int getAvgTimeCashier() {
		return Integer.parseInt(checkTimeBox.getText());
	}
	
	/********************************************************************************
	 * Gets the total time that the program will run as entered by the user
	 * @return int
	 *******************************************************************************/
	public int getTotalTime() {
		return Integer.parseInt(totTimeBox.getText());
	}
	
	/********************************************************************************
	 * Gets the average time spent at the eatery as entered by the user
	 * @return int
	 *******************************************************************************/
	public int getAvgEateryTime() {
		return Integer.parseInt(eatTimeBox.getText());
	}
	
	/********************************************************************************
	 * Gets the average time that it takes people to leave the line as entered
	 * by the user
	 * @return int
	 *******************************************************************************/
	public int getLeaveTime() {
		return Integer.parseInt(leaveTimeBox.getText());
	}
	
	/********************************************************************************
	 * Gets the number of eateries available as entered by the user
	 * @return int
	 *******************************************************************************/
	public int getNumEateries() {
		return Integer.parseInt(eatBox.getText());
	}
	
	/********************************************************************************
	 * Gets the number of checkouts that are available as entered by the user
	 * @return int
	 *******************************************************************************/
	public int getNumCheckouts() {
		return Integer.parseInt(checkBox.getText());
	}
	
}
