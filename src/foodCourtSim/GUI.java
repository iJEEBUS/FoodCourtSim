package foodCourtSim;

//import foodCourtSim.Pane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The entire GUI class that is to be called in order to run the 
 * FoodCourtSim simulation.
 * 
 * @author Ronald Rounsifer
 * @version 3.21.2017
 */
public class GUI {

	int seconds_to_next_person;
	int avg_time_cashier;
	int total_time;
	int avg_time_eatery;
	int leave_time;
	int num_eateries;


	/** JFrame that is to be used with the GUI */
	JFrame frame;
	/** JPanel that is to be used with the GUI */
	JPanel panel;

	// all of the labels on the left side
	JLabel blank_lbl = new JLabel("");
	JLabel input_lbl = new JLabel("Input Information");
	JLabel next_person_lbl = new JLabel("Seconds to the Next Person");
	JLabel avg_sec_cashier_lbl = new JLabel("Average Seconds per cashier");
	JLabel total_time_lbl = new JLabel("Total time in seconds");
	JLabel avg_sec_eatery_lbl = new JLabel("Average Seconds per Eatery");
	JLabel person_leaves_lbl = new JLabel("Seconds Before Person leaves");
	JLabel num_eateries_lbl = new JLabel("Number of Eateries");
	JLabel output_info_lbl = new JLabel("Output information");
	JLabel throughput_lbl = new JLabel("Throughput");
	JLabel avg_time_lbl = new JLabel("Average time for a Person from start to finish:");
	JLabel num_ppl_left_lbl = new JLabel("Number of people left line");
	JLabel max_queue_lbl = new JLabel("Max Q length cashier line");
	JLabel left_in_line_lbl = new JLabel("Left in line");

	// labels on the right side
	JLabel line_lbl = new JLabel("-----------------------------------");
	JLabel second_line_lbl = new JLabel("-----------------------------------");
	JLabel throughput_result_lbl = new JLabel("");
	JLabel avg_time_result_lbl = new JLabel("");
	JLabel num_ppl_left_result_lbl = new JLabel("");
	JLabel max_queue_result_lbl = new JLabel("");
	JLabel left_in_line_result_lbl = new JLabel("");

	// text fields on the right side
	JTextField next_person_txt = new JTextField("20", 20);
	JTextField avg_sec_cashier_txt = new JTextField("10", 20);
	JTextField total_time_txt = new JTextField("10000", 20);
	JTextField avg_sec_eatery_txt = new JTextField("60", 20);
	JTextField person_leaves_txt = new JTextField("900", 20);
	JTextField num_eateries_txt = new JTextField("5", 20);

	// both buttons
	JButton start_btn = new JButton("Start Simulation");
	JButton quit_btn = new JButton("Quit Simulation");

	//	/** JFrame that is to be used for the GUI */
	//	JFrame frame;
	//
	//	Pane og_panel;

	/**
	 * Constructor for the GUI class
	 */
	public GUI() {
		frame = new JFrame();
		//		og_panel = new Pane(og_panel);

		//		this.pane = pane;

		// set the layout of the pane
		frame.setLayout(new GridBagLayout());

		// panel to hold components
		panel = new JPanel(new GridBagLayout());

		// constraints for the grid
		GridBagConstraints gbc = new GridBagConstraints();

		// add components to the JPanel
		// first column
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 0, 5, 5);
		panel.add(input_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(5, 0, 5, 5);
		panel.add(next_person_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(5, 0, 5, 5);
		panel.add(avg_sec_cashier_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(5, 0, 5, 5);
		panel.add(total_time_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(5, 0, 5, 5);
		panel.add(avg_sec_eatery_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.insets = new Insets(5, 0, 5, 5);
		panel.add(person_leaves_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.insets = new Insets(5, 0, 5, 5);
		panel.add(num_eateries_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = new Insets(5, 0, 5, 5);
		panel.add(start_btn, gbc);

		gbc.gridx = 0;
		gbc.gridy = 8;
		gbc.insets = new Insets(5, 5, 5, 5);
		panel.add(blank_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(0, 0, 0, 5);
		panel.add(output_info_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 10;
		gbc.insets = new Insets(0, 0, 0, 5);
		panel.add(throughput_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 11;
		gbc.insets = new Insets(0, 0, 0, 5);
		panel.add(avg_time_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.insets = new Insets(0, 0, 0, 5);
		panel.add(num_ppl_left_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 13;
		gbc.insets = new Insets(0, 0, 0, 5);
		panel.add(max_queue_lbl, gbc);

		gbc.gridx = 0;
		gbc.gridy = 14;
		gbc.insets = new Insets(0,0,30,5);
		panel.add(left_in_line_lbl, gbc);


		// second column
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 5, 0, 0);
		panel.add(line_lbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(next_person_txt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(0, 5, 0, 0);
		panel.add(avg_sec_cashier_txt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 5, 0, 0);
		panel.add(total_time_txt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 5, 0, 0);
		panel.add(avg_sec_eatery_txt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 5, 0, 0);
		panel.add(person_leaves_txt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.insets = new Insets(0, 5, 0, 0);
		panel.add(num_eateries_txt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.insets = new Insets(0, 5, 0, 0);
		gbc.fill = GridBagConstraints.LINE_START;
		panel.add(quit_btn, gbc);

		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.insets = new Insets(5, 5, 5, 5);
		panel.add(blank_lbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 9;
		gbc.insets = new Insets(0, 5, 0, 0);
		panel.add(second_line_lbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 10;
		gbc.insets = new Insets(0, 5, 0, 0);
		panel.add(throughput_result_lbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 11;
		gbc.insets = new Insets(0, 5, 0, 0);
		panel.add(avg_time_result_lbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 12;
		gbc.insets = new Insets(0, 5, 0, 0);
		panel.add(num_ppl_left_result_lbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 13;
		gbc.insets = new Insets(0, 5, 0, 0);
		panel.add(max_queue_result_lbl, gbc);

		gbc.gridx = 1;
		gbc.gridy = 14;
		gbc.insets = new Insets(0,5,30,0);
		panel.add(left_in_line_result_lbl, gbc);

		// add this panel to the JFrame that is calling it
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		frame.add(panel, gbc);	

		// add action listener to the buttons
		start_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// executes the simulation when pressed
				//						JOptionPane.showMessageDialog(null, "The program should begin running.");
				Sim sim = new Sim();
				seconds_to_next_person = Integer.parseInt(next_person_txt.getText());
				avg_time_cashier = Integer.parseInt(avg_sec_cashier_txt.getText());
				total_time = Integer.parseInt(total_time_txt.getText());
				avg_time_eatery = Integer.parseInt(avg_sec_eatery_txt.getText());
				leave_time = Integer.parseInt(person_leaves_txt.getText());
				num_eateries = Integer.parseInt(num_eateries_txt.getText());

				sim.run(panel, seconds_to_next_person, avg_time_cashier, total_time, 
						avg_time_eatery, leave_time, num_eateries);

				// need to add all of the throughputs, the people who have left the line, 
				// and the ones who are still in line

				throughput_result_lbl.setText(Integer.toString(sim.getThroughput()) + " people with Max = " + Integer.toString(sim.getNumPeople()));
				avg_time_result_lbl.setText(Integer.toString(sim.getAvgTime()) + " seconds");
				num_ppl_left_result_lbl.setText(Integer.toString(sim.getPeopleLeft()) + " people");
				max_queue_result_lbl.setText(Integer.toString(sim.getMaxQueueSize()));
				left_in_line_result_lbl.setText(Integer.toString(sim.getLeftInLine()));
				panel.revalidate();
				panel.repaint();
			}
		});

		quit_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(1);
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
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
