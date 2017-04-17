package Project_4;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SimulatedGUI extends JPanel implements Runnable, ActionListener{

	private GUIsimulation sim;

	private JPanel buttonPanel;
	private JPanel dataPanel;
	private JPanel displayPanel;
	private JPanel buttonDisplayHolder;

	private JButton addEatery, removeEatery, addCheckout, removeCheckout, start, stop, editInfo;

	private int simY = 500, simX = 700, bpX = 200, dpY = 100, bpY = 320;

	private Dimension button;

	private int ticks;
	private boolean isRunning, compleate;

	public SimulatedGUI() {

		isRunning = false;
		compleate = false;

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

		new Thread(this).start();
	}

	private void addButtons() {
		start = new JButton("Start");
		start.setPreferredSize(button);
		start.setFocusPainted(false);
		start.addActionListener(this);

		stop = new JButton("Stop");
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

	private void addData() {

	}

	public void run() {
		ticks = 0;
		try{
			while(true) {
				if(isRunning && !compleate) {
					sim.oneTick();
					ticks++;
					compleate = sim.checkTicks(ticks);
					if(compleate)
						System.out.println("Compleate");
				}
				Thread.sleep(10);
			}
		}
		catch (Exception e) {}
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

	@Override
	public void actionPerformed(ActionEvent event) {
		Object source = event.getSource();

		if(source == start) {
			isRunning = true;
		}

		if(source == stop)
			isRunning = false;

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

		sim.redisplay();
		sim.repaint();
	}
}
