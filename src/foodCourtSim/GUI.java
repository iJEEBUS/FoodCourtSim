package foodCourtSim;

import foodCourtSim.Pane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The entire GUI class that is to be called in order to run the 
 * FoodCourtSim simulation.
 * 
 * @author Ronald Rounsifer
 * @version 3.21.2017
 */
public class GUI {
	
	/** JFrame that is to be used for the GUI */
	JFrame frame;
	
	/**
	 * Constructor for the GUI class
	 */
	public GUI() {
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Pane());
		frame.pack();
		frame.setLocation(800, 100);
		frame.setVisible(true);
	}
	
	/**
	 * Creates and runs a new instance of the GUI class
	 */
	public static void main(String[] args) {
		GUI gui = new GUI();
	}

}
