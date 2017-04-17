package Project_4;

import java.awt.*;
import javax.swing.*;

public class GUIsimulation extends JPanel{
	private Color[][] foodCourt;

	private int rows, columns, size = 10;

	private int numEateries, numCheckouts, numTicksNextPerson, averageEaterieTime,
	averageCheckOutTime, averageLeaveTime, runTime, ticks;
	
	private int Yc, Ye, Xe, Xc;

	private CheckOutQ Q;
	private Eatery[] eateries;
	private CheckOut[] checkouts;

	private PersonProducer produce;

	private boolean isRunning = false;
	private Clock clk;

	private JPanel infoPanel;

	private JTextField eatBox;
	private JTextField checkBox;
	private JTextField nextTimeBox;
	private JTextField eatTimeBox;
	private JTextField checkTimeBox;
	private JTextField leaveTimeBox;
	private JTextField totTimeBox;

	private JLabel eat;
	private JLabel check;
	private JLabel newPerson;
	private JLabel toGetFood;
	private JLabel toCheckOut;
	private JLabel toLeaveLine;
	
	private Color eaterieColor = Color.GRAY;
	private Color checkoutColor = Color.GRAY;

	public GUIsimulation(int width, int height) {
		this.rows = height/10;
		this.columns = width/10;
		foodCourt = new Color[rows][columns];
		setFoodCourt();
		clk = new Clock();
		defaultValues();
		fillCheckouts();
		fillEateries();
		Q = new CheckOutQ();
		clk.add(produce);
		setPreferredSize(new Dimension(columns*size, rows*size));

		getInfoCreater();
	}

	private void setFoodCourt() {
		for(int i = 0; i < columns; i++)
			for(int j = 0; j < rows; j++)
				foodCourt[j][i] = Color.white;
	}

	private void defaultValues() {
		rows = 50;
		columns = 70;
		numEateries = 3;
		numCheckouts = 2;
		numTicksNextPerson = 20;
		averageEaterieTime = 5;
		averageCheckOutTime = 20;
		averageLeaveTime = 900;
		runTime = 1000;

		eateries = new Eatery[numEateries];
		checkouts = new CheckOut[numCheckouts];

		for(int i = 0; i < numEateries; i++)
			eateries[i] = new Eatery(Q);

		for(int i = 0; i < numCheckouts; i++)
			checkouts[i] = new CheckOut(Q);

		produce = new PersonProducer(eateries, checkouts, numTicksNextPerson,
				averageEaterieTime, averageCheckOutTime, averageLeaveTime);
	}
	
	private void setInfo() {
		eateries = new Eatery[numEateries];
		checkouts = new CheckOut[numCheckouts];

		for(int i = 0; i < numEateries; i++)
			eateries[i] = new Eatery(Q);

		for(int i = 0; i < numCheckouts; i++)
			checkouts[i] = new CheckOut(Q);

		produce = new PersonProducer(eateries, checkouts, numTicksNextPerson,
				averageEaterieTime, averageCheckOutTime, averageLeaveTime);
	}

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

		eateries = new Eatery[numEateries];
		checkouts = new CheckOut[numCheckouts];

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
		clk.oneTick();
		
		clearBoard();
		
		for(int i = 1; i <= numEateries; i++)
			for(int j = 0; j < 24; j++) {
				foodCourt[(Ye*i)+2][Xe + j] = eateries[i-1].getColorIndex(j);
			}
		
		for(int i = 0; i < 25; i++)
			foodCourt[rows/2][(Xc+6) + i] = Q.getColorIndex(i);
		
		repaint();
	}

	public void paintComponent(Graphics g) {
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < columns; c++) {
				g.setColor(foodCourt[r][c]);
				g.fillRect(c*size, r*size, size, size);
			}
	}
	
	private void clearBoard() {
		for(int r = 0; r < rows; r++)
			for(int c = columns; c < columns; c++)
				if(foodCourt[r][c] != eaterieColor || foodCourt[r][c] != checkoutColor)
					foodCourt[r][c] = Color.WHITE;
	}
	
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

	public void addEatery() {
		numEateries++;
		setInfo();
		fillEateries();
	}

	public void addCheckout() {
		numCheckouts++;
		setInfo();
		fillCheckouts();
	}

	public void removeEatery() {
		if(numEateries != 0) {
			numEateries--;
			setInfo();
			fillEateries();
		}
	}

	public void removeCheckout() {
		if(numCheckouts != 0) {
			numCheckouts--;
			setInfo();
			fillCheckouts();
		}
	}
	
	public boolean checkTicks(int ticks) {
		if(ticks == runTime)
			return true;
		
		return false;
	}

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

		panel.add(eat);
		panel.add(check);
		panel.add(newPerson);
		panel.add(toGetFood);
		panel.add(toCheckOut);
		panel.add(toLeaveLine);
	}

	public void redisplay() {
		eat.setText("Eateries: " + numEateries);
		check.setText("Check Outs: " + numCheckouts);
		newPerson.setText("New Person: " + numTicksNextPerson + "s");
		toGetFood.setText("To Get Food: " + averageEaterieTime + "s");
		toCheckOut.setText("To Check Out: " + averageCheckOutTime + "s");
		toLeaveLine.setText("To Leave Line: " + averageLeaveTime + "s");
	}
}
