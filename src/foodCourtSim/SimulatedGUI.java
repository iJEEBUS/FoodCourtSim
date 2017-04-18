package foodCourtSim;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/*************************************************************************************
 *	This class creates the initial GUI that is then added onto with the 
 * GUIsimulation class
 * 
 * @author Ron, Patryk, Mitch, Tyler
 *
 ************************************************************************************/
public class SimulatedGUI extends JPanel implements Runnable, ActionListener{

	/** Instance of a GUIsimulation */
	private GUIsimulation sim;

	/** Constraints used for GUI */
	private GridBagConstraints gbc = new GridBagConstraints();

	/** Panel for the buttons */
	private JPanel buttonPanel;

	/** Panel for the data */
	private JPanel dataPanel;

	/** Panel for the displayed info */
	private JPanel displayPanel;

	/** Panel for the button display */
	private JPanel buttonDisplayHolder;

	/** JButtons to add to the infoPanel */
	private JButton addEatery, removeEatery, addCheckout, removeCheckout, start, stop, editInfo;

	/** Labels for the the dataPanel that are in the first column */
	private JLabel output_lbl, throughput_lbl, avg_time_lbl, left_line_lbl, max_q_lbl, 
	num_special_needs_lbl, num_limited_lbl, avg_time_special_lbl, avg_time_limited_lbl,
	avg_limited_time_lbl, avg_special_needs_lbl;

	/** Labels for the the dataPanel that are in the second column */
	private JLabel output_out_lbl, throughput_out_lbl, avg_time_out_lbl, left_line_out_lbl, 
	max_q_out_lbl, num_special_needs_out_lbl, num_limited_out_lbl, avg_time_special_out_lbl,
	avg_time_limited_out_lbl, avg_limited_time_out_lbl, avg_special_needs_out_lbl;

	/** Labels for the the dataPanel that are in the third column */
	private JLabel limited_time_key, special_needs_key, regular_key;

	/** Values for the layout of the GUI */
	private int simY = 500, simX = 700, bpX = 200, dpY = 100, bpY = 320;

	/** The size of the buttons */
	private Dimension button;

	/** The amount of current ticks in the simulation */
	private int ticks;

	/** Tells if the sim is running and/or complete */
	private boolean isRunning, compleate;

	/*************************************************************************************
	 * Constructor the the SimulatedGUI class. Adds the buttons, displayPanel, 
	 * and dataPanel.
	 ************************************************************************************/
	SimulatedGUI() {

		isRunning = false;
		compleate = false;

		button = new Dimension((bpX - 10), 40);
		sim = new GUIsimulation(simX, simY);
		add(sim);

		// buttonDisplayHolder
		buttonDisplayHolder = new JPanel();
		buttonDisplayHolder.setPreferredSize(new Dimension(bpX, simY));
		buttonDisplayHolder.setBackground(Color.GRAY);
		add(buttonDisplayHolder);

		// buttonPanel
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(bpX-2, bpY));
		buttonPanel.setBackground(Color.white);
		buttonDisplayHolder.add(buttonPanel);

		addButtons();

		// displayPanel
		displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(bpX-2, simY - (bpY)));
		displayPanel.setBackground(Color.white);
		buttonDisplayHolder.add(displayPanel);

		sim.display(displayPanel);
		
		// dataPanel
		dataPanel = new JPanel();
		dataPanel.setPreferredSize(new Dimension(simX + bpX + 5, dpY + 85));
		dataPanel.setLayout(new GridBagLayout());
		dataPanel.setBackground(Color.white);
		add(dataPanel);

		addData();

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(simX + bpX + 20, 
				simY + dpY + 100));

//		  This command makes the method run() run forever in a separate thread. If
//		  the variable isRunning is true then the run will execute something otherwise
//		  it just runs in the background.
		 
		new Thread(this).start();
	}

	/********************************************************************************
	 * Add the buttons to the GUI
	 *******************************************************************************/
	private void addButtons() {
		start = new JButton("Start");
		start.setPreferredSize(button);
		start.setFocusPainted(false);
		start.addActionListener(this);

		stop = new JButton("Stop and Reset");
		stop.setPreferredSize(button);
		stop.setFocusPainted(false);
		stop.addActionListener(this);

		addEatery = new JButton("Add Eatery");
		addEatery.setPreferredSize(button);
		addEatery.setFocusPainted(false);
		addEatery.addActionListener(this);

		removeEatery = new JButton("Remove Eatery");
		removeEatery.setPreferredSize(button);
		removeEatery.setFocusPainted(false);
		removeEatery.addActionListener(this);

		addCheckout = new JButton("Add Check Out");
		addCheckout.setPreferredSize(button);
		addCheckout.setFocusPainted(false);
		addCheckout.addActionListener(this);

		removeCheckout = new JButton("Remove Check Out");
		removeCheckout.setPreferredSize(button);
		removeCheckout.setFocusPainted(false);
		removeCheckout.addActionListener(this);

		editInfo = new JButton("Edit Info");
		editInfo.setPreferredSize(button);
		editInfo.setFocusPainted(false);
		editInfo.addActionListener(this);

		buttonPanel.add(start);
		buttonPanel.add(stop);
		buttonPanel.add(addEatery);
		buttonPanel.add(removeEatery);
		buttonPanel.add(addCheckout);
		buttonPanel.add(removeCheckout);
		buttonPanel.add(editInfo);
	}

	/********************************************************************************
	 * Add the data labels to the dataPanel
	 *******************************************************************************/
	private void addData() {

		// font for the data panel
		Font textFont = new Font("Helvetica", Font.PLAIN, 16);
		
		// first column
		output_lbl = new JLabel("Output Information");
		output_lbl.setFont(new Font("Helvetica", Font.BOLD, 17));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(1, 0, 1, 0);
		dataPanel.add(output_lbl, gbc);

		throughput_lbl = new JLabel("Throughput:");
		gbc.gridy++; // 1
		throughput_lbl.setFont(textFont);
		dataPanel.add(throughput_lbl, gbc);

		avg_time_lbl = new JLabel("Average time for a Person from start to finish: ");
		gbc.gridy++; // 2
		avg_time_lbl.setFont(textFont);
		dataPanel.add(avg_time_lbl, gbc);

		left_line_lbl = new JLabel("Number of people left line: ");
		gbc.gridy++; // 3
		left_line_lbl.setFont(textFont);
		dataPanel.add(left_line_lbl, gbc);

		max_q_lbl = new JLabel("Max Q length cashier line: ");
		gbc.gridy++; // 4
		max_q_lbl.setFont(textFont);
		dataPanel.add(max_q_lbl, gbc);	

		num_limited_lbl = new JLabel("Number of limited time customers: ");
		gbc.gridy++;
		num_limited_lbl.setFont(textFont);
		dataPanel.add(num_limited_lbl, gbc);

		num_special_needs_lbl = new JLabel("Number of special needs customers: ");
		gbc.gridy++;
		num_special_needs_lbl.setFont(textFont);
		dataPanel.add(num_special_needs_lbl, gbc);

		avg_limited_time_lbl = new JLabel("Limited time customers average time: ");
		gbc.gridy++;
		avg_limited_time_lbl.setFont(textFont);
		dataPanel.add(avg_limited_time_lbl, gbc);

		avg_special_needs_lbl = new JLabel("Special needs customers average time: ");
		gbc.gridy++;
		avg_special_needs_lbl.setFont(textFont);
		dataPanel.add(avg_special_needs_lbl, gbc);


		// second column
		output_out_lbl = new JLabel("--------------------------------");
		output_out_lbl.setFont(new Font("Helvetica", Font.BOLD, 17));
		gbc.gridx = 1;
		gbc.gridy = 0;
		dataPanel.add(output_out_lbl, gbc);

		throughput_out_lbl = new JLabel("");
		throughput_out_lbl.setFont(textFont);
		gbc.gridy++; // 1
		dataPanel.add(throughput_out_lbl, gbc);

		avg_time_out_lbl = new JLabel("");
		avg_time_out_lbl.setFont(textFont);
		gbc.gridy++; // 2
		dataPanel.add(avg_time_out_lbl, gbc);

		left_line_out_lbl = new JLabel("");
		left_line_out_lbl.setFont(textFont);
		gbc.gridy++; // 3
		dataPanel.add(left_line_out_lbl, gbc);

		max_q_out_lbl = new JLabel("");
		max_q_out_lbl.setFont(textFont);
		gbc.gridy++; // 4
		dataPanel.add(max_q_out_lbl, gbc);

		num_limited_out_lbl = new JLabel("");
		num_limited_out_lbl.setFont(textFont);
		gbc.gridy++;
		dataPanel.add(num_limited_out_lbl, gbc);

		num_special_needs_out_lbl = new JLabel("");
		num_special_needs_out_lbl.setFont(textFont);
		gbc.gridy++;
		dataPanel.add(num_special_needs_out_lbl, gbc);

		avg_limited_time_out_lbl = new JLabel("");
		avg_limited_time_out_lbl.setFont(textFont);
		gbc.gridy++;
		dataPanel.add(avg_limited_time_out_lbl, gbc);

		avg_special_needs_out_lbl = new JLabel("");
		avg_special_needs_out_lbl.setFont(textFont);
		gbc.gridy++;
		dataPanel.add(avg_special_needs_out_lbl, gbc);

		// third column
		limited_time_key = new JLabel(" Limited Time Customer ");
		limited_time_key.setBorder(new BevelBorder(1, Color.blue, Color.blue));
		limited_time_key.setFont(textFont);
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.insets = new Insets(1, 50, 1, 0);
		dataPanel.add(limited_time_key, gbc);

		special_needs_key = new JLabel(" Special Needs Customer ");
		special_needs_key.setBorder(new BevelBorder(1, Color.green, Color.green));
		special_needs_key.setFont(textFont);
		gbc.gridy += 2;
		dataPanel.add(special_needs_key, gbc);

		regular_key = new JLabel(" Regular Customer ");
		regular_key.setBorder(new BevelBorder(1, Color.RED, Color.RED));
		regular_key.setBackground(Color.RED);
		regular_key.setFont(textFont);
		gbc.gridy += 2;
		dataPanel.add(regular_key, gbc);
	}

	/********************************************************************************
	 * Updates the GUI labels that show the data in the bottom panel
	 *******************************************************************************/
	public void updateData() {
		throughput_out_lbl.setText(Integer.toString(sim.getThroughput()));
		left_line_out_lbl.setText(Integer.toString(sim.getLeftLine()));
		avg_time_out_lbl.setText(Integer.toString(sim.getAvgTotalTime()));
		max_q_out_lbl.setText(Integer.toString(sim.getMaxLength()));
		num_limited_out_lbl.setText(Integer.toString(sim.getNumLimited()));
		num_special_needs_out_lbl.setText(Integer.toString(sim.getNumSpecial()));
		avg_limited_time_out_lbl.setText(Integer.toString(sim.getAvgTimeLimited()));
		avg_special_needs_out_lbl.setText(Integer.toString(sim.getAvgTimeSpecial()));

	}

	/********************************************************************************
	 * Runs the entire simulation and creates a JOptionPane to inform the user
	 * when the simulation has ended
	 *******************************************************************************/
	public void run() {
		ticks = 0;
		try{
			while(true) {
				if(isRunning) {

					sim.oneTick();
					ticks++;
					compleate = sim.checkTicks(ticks);
					updateData();

					if(compleate) {
						isRunning = false;
						sim.reset();
						JOptionPane.showMessageDialog(null, "Simulation Complete");
					}
				}
				Thread.sleep(50);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/********************************************************************************
	 * Main method which runs the actual SimulatedGUI
	 * 
	 * @param args
	 *******************************************************************************/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SimulatedGUI panel = new SimulatedGUI();
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);
	}

	/********************************************************************************
	 * Handles the button clicks for the GUI
	 *******************************************************************************/
	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();
		
		if(source == start) 
			isRunning = true;

		if(source == stop) {
			isRunning = false;
			sim.reset();
		}

		if(source == addEatery)
			sim.addEatery();

		if(source == addCheckout)
			sim.addCheckout();

		if(source == removeEatery)
			sim.removeEatery();

		if(source == removeCheckout)
			sim.removeCheckout();

		if(source == editInfo)
			sim.getInfo();

		addEatery.setEnabled(!isRunning);
		removeEatery.setEnabled(!isRunning);
		addCheckout.setEnabled(!isRunning);
		removeCheckout.setEnabled(!isRunning);
		editInfo.setEnabled(!isRunning);

		sim.redisplay();
		sim.repaint();
	}
}