package Project_4;

import java.awt.*;
import javax.swing.*;

public class GUIsimulation extends JPanel {
	private Person[][] foodCourt;
	
	private int ROWS = 50, COLUMNS = 70, SIZE = 10;
	
	private int numEateries, numCheckouts, numTicksNextPerson, averageEaterieTime,
			averageCheckOutTime, averageLeaveTime;
	
	private CheckOutQ Q;
	
	private PersonProducer produce;
	
	private boolean isRunning = false;
	
	public GUIsimulation(int width, int height) {
		this.ROWS = height/10;
		this.COLUMNS = width/10;
		foodCourt = new Person[ROWS][COLUMNS];
		Clock clk = new Clock();
		Q = new CheckOutQ();
		getInfo();
		clk.add(produce);
		setPreferredSize(new Dimension(COLUMNS*SIZE, ROWS*SIZE));
	}
	
	public void getInfo() {
		//JOptionPane to be used
		numEateries = 3;
		numCheckouts = 2;
		numTicksNextPerson = 20;
		averageEaterieTime = 60;
		averageCheckOutTime = 40;
		averageLeaveTime = 900;
		
		Eatery[] eateries = new Eatery[numEateries];
		CheckOut[] checkouts = new CheckOut[numCheckouts];
		
		for(int i = 0; i < numEateries; i++)
			eateries[i] = new Eatery(Q);
		
		for(int i = 0; i < numCheckouts; i++)
			checkouts[i] = new CheckOut(Q);
		
		produce = new PersonProducer(eateries, checkouts, numTicksNextPerson,
				averageEaterieTime, averageCheckOutTime, averageLeaveTime);
	}
	
	public void oneTick() {
		
	}
	
	public void paintComponent(Graphics g) {
		for(int row=0; row<ROWS; row++){
            for(int col=0; col<COLUMNS; col++){
                Person p = foodCourt[row][col];

                // set color to white if no critter here
                if(p == null){
                    g.setColor(Color.WHITE);
                    // set color to critter color   
                }else{    
                    g.setColor(p.getColor());
                }

                // paint the location
                g.fillRect(col*SIZE, row*SIZE, SIZE, SIZE);
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
