package foodCourtSim;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SimulatedGUI extends JPanel{

	private GUIsimulation sim;
	
	private Sim s = new Sim();

	private GridBagConstraints gbc = new GridBagConstraints();

	private JPanel buttonPanel;
	private JPanel dataPanel;

	private action listener;

	private JButton addEatery, removeEatery, addCheckout, removeCheckout, start, stop, editInfo;

	// make these align to the left of the data panel
	private JLabel output_lbl, throughput_lbl, avg_time_lbl, left_line_lbl, max_q_lbl;
	private JLabel output_out_lbl, throughput_out_lbl, avg_time_out_lbl, left_line_out_lbl, max_q_out_lbl;

	private int simY = 500, simX = 700, bpX = 200, dpY = 100;

	private Dimension button;

	public SimulatedGUI() {
		listener = new action();

		button = new Dimension((bpX - 10), 40);
		sim = new GUIsimulation(simX, simY);
		add(sim);

		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(bpX, simY));
		buttonPanel.setBackground(Color.white);
		add(buttonPanel);

		addButtons();

		dataPanel = new JPanel();
		dataPanel.setPreferredSize(new Dimension(simX + bpX + 5, dpY));
		dataPanel.setLayout(new GridBagLayout());
		dataPanel.setBackground(Color.white);
		add(dataPanel);

		addData();

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(simX + bpX + 20, 
				simY + dpY + 20));
	}

	private void addButtons() {
		start = new JButton("Start");
		start.setPreferredSize(button);
		start.setFocusPainted(false);
		start.addActionListener(listener);

		stop = new JButton("Stop");
		stop.setPreferredSize(button);
		stop.setFocusPainted(false);
		stop.addActionListener(listener);

		addEatery = new JButton("Add Eatery");
		addEatery.setPreferredSize(button);
		addEatery.setFocusPainted(false);
		addEatery.addActionListener(listener);

		removeEatery = new JButton("Remove Eatery");
		removeEatery.setPreferredSize(button);
		removeEatery.setFocusPainted(false);
		removeEatery.addActionListener(listener);

		addCheckout = new JButton("Add Check Out");
		addCheckout.setPreferredSize(button);
		addCheckout.setFocusPainted(false);
		addCheckout.addActionListener(listener);

		removeCheckout = new JButton("Remove Check Out");
		removeCheckout.setPreferredSize(button);
		removeCheckout.setFocusPainted(false);
		removeCheckout.addActionListener(listener);

		editInfo = new JButton("Edit Info");
		editInfo.setPreferredSize(button);
		editInfo.setFocusPainted(false);
		editInfo.addActionListener(listener);

		buttonPanel.add(start);
		buttonPanel.add(stop);
		buttonPanel.add(addEatery);
		buttonPanel.add(removeEatery);
		buttonPanel.add(addCheckout);
		buttonPanel.add(removeCheckout);
		buttonPanel.add(editInfo);
	}

	private void addData() {

		// left side of data panel
		output_lbl = new JLabel("Output Information");
		gbc.gridx = 0;
		gbc.gridy = 0;
		dataPanel.add(output_lbl, gbc);

		throughput_lbl = new JLabel("Throughput:");
		gbc.gridy++; // 1
		dataPanel.add(throughput_lbl, gbc);

		avg_time_lbl = new JLabel("Average time for a Person from start to finish: ");
		gbc.gridy++; // 2
		dataPanel.add(avg_time_lbl, gbc);

		left_line_lbl = new JLabel("Number of people left line: ");
		gbc.gridy++; // 3
		dataPanel.add(left_line_lbl, gbc);

		max_q_lbl = new JLabel("Max Q length cashier line: ");
		gbc.gridy++; // 4
		dataPanel.add(max_q_lbl, gbc);	
		
		// right side of data panel
		output_out_lbl = new JLabel("--------------------------------");
		gbc.gridx = 1;
		gbc.gridy = 0;
		dataPanel.add(output_out_lbl, gbc);
		
		throughput_out_lbl = new JLabel("");
		gbc.gridy++; // 1
		dataPanel.add(throughput_out_lbl, gbc);
		
		avg_time_out_lbl = new JLabel("");
		gbc.gridy++; // 2
		dataPanel.add(avg_time_out_lbl, gbc);
		
		left_line_out_lbl = new JLabel("");
		gbc.gridy++; // 3
		dataPanel.add(left_line_out_lbl, gbc);
		
		max_q_out_lbl = new JLabel("");
		gbc.gridy++; // 4
		dataPanel.add(max_q_out_lbl, gbc);
	}
	
	private void updateGUI() {
		throughput_out_lbl.setText("" + s.getThroughput());
		avg_time_out_lbl.setText("" + s.getAvgTime());
		left_line_out_lbl.setText("" + s.getPeopleLeft());
		max_q_out_lbl.setText("" + s.getMaxQueueSize());
		dataPanel.repaint();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SimulatedGUI panel = new SimulatedGUI();
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);
	}

	private class action implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			Object source = event.getSource();

			if(source == start) { 
				// shows that the sim is active
				sim.start();
				
				// arguments are from the GUIsimulation class that the user inputs
				s.run(sim.getNextTime(), sim.getAvgTimeCashier(), sim.getTotalTime(), 
						sim.getAvgEateryTime(), sim.getLeaveTime(), sim.getNumEateries());
				updateGUI();
			}

			else if(source == stop)
				// shows that the sim is inactive
				sim.stop();

			else if(source == addEatery)
				sim.addEatery();

			else if(source == addCheckout)
				sim.addCheckout();

			else if(source == removeEatery)
				sim.removeEatery();

			else if(source == removeCheckout)
				sim.removeCheckout();

			else
				sim.getInfo();
		}
	}
}
