package foodCourtSim;

import foodCourtSim.Pane;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {
	
	/** JFrame that is to be used for the GUI */
	JFrame frame;
	
	/** JPanel that is to be used for the GUI */
	Pane pane;
	
	
	/** Labels that are to be used for the GUI */
	JLabel line_lbl;
	JLabel input_lbl;
	JLabel next_person_lbl;
	JLabel avg_sec_cashier_lbl;
	JLabel total_time_lbl;
	JLabel avg_sec_eatery_lbl;
	JLabel person_leaves_lbl;
	JLabel num_eateries_lbl;
	JLabel output_info_lbl;
	JLabel throughput_lbl;
	JLabel avg_time_lbl;
	JLabel num_ppl_left_lbl;
	JLabel max_queue_lbl;
	
	/** Buttons that are to be used for the GUI */
	JButton start_sim_btn;
	JButton quit_sim_btn;
	
	public GUI() {
		
		frame = new JFrame();
		pane = new Pane();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pane);
		frame.pack();
		frame.setLocation(800, 100);
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}

}
