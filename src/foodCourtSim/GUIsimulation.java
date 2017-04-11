package Project_4;

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

	private void getInfoCreater() {
		eatBox = new JTextField(5);
		checkBox = new JTextField(5);
		nextTimeBox = new JTextField(5);
		eatTimeBox = new JTextField(5);
		checkTimeBox = new JTextField(5);
		leaveTimeBox = new JTextField(5);
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
		
		infoPanel.setPreferredSize(new Dimension(250,300));
	}

	public void oneTick() {
		//controls the simulation
	}

	public void paintComponent(Graphics g) {
		for(int row=0; row<this.rows; row++){
			for(int col=0; col<this.columns; col++){
				Person p = foodCourt[row][col];

				// set color to white if no critter here
				if(p == null){
					g.setColor(Color.WHITE);
					// set color to critter color   
				}else{    
					g.setColor(p.getColor());
				}

				// paint the location
				g.fillRect(col*size, row*size, size, size);
			}
		}
	}

	public void start() {
		isRunning = true;
	}

	public void stop() {
		isRunning = false;
	}

	public void addEatery() {
		numEateries++;
	}

	public void addCheckout() {
		numCheckouts++;
	}

	public void removeEatery() {
		numEateries--;
	}

	public void removeCheckout() {
		numCheckouts--;
	}
}
