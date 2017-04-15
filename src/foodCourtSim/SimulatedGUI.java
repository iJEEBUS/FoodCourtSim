package Project_4;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SimulatedGUI extends JPanel{

	private GUIsimulation sim;

	private JPanel buttonPanel;
	private JPanel dataPanel;
	private JPanel displayPanel;
	private JPanel buttonDisplayHolder;

	private action listener;

	private JButton addEatery, removeEatery, addCheckout, removeCheckout, start, stop, editInfo;

	private int simY = 500, simX = 700, bpX = 200, dpY = 100, bpY = 320;

	private Dimension button;

	public SimulatedGUI() {
		listener = new action();

		button = new Dimension((bpX - 10), 40);
		sim = new GUIsimulation(simX, simY);
		add(sim);
		
		buttonDisplayHolder = new JPanel();
		buttonDisplayHolder.setPreferredSize(new Dimension(bpX, simY));
		buttonDisplayHolder.setBackground(Color.GRAY);
		add(buttonDisplayHolder);

		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(bpX-2, bpY));
		buttonPanel.setBackground(Color.white);
		buttonDisplayHolder.add(buttonPanel);

		addButtons();
		
		displayPanel = new JPanel();
		displayPanel.setPreferredSize(new Dimension(bpX-2, simY - (bpY)));
		displayPanel.setBackground(Color.white);
		buttonDisplayHolder.add(displayPanel);
		
		sim.display(displayPanel);

		dataPanel = new JPanel();
		dataPanel.setPreferredSize(new Dimension(simX + bpX + 5, dpY));
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

			if(source == start) 
				sim.start();

			else if(source == stop)
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
			
			sim.redisplay();
			sim.repaint();
		}

	}
}
