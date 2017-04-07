import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
/**********************************************************
GUI for a critter simulation.  Impements Runnable to allow
a method to run in the background while the user continues
to click on buttons.

@author Scott Grissom
@version August 2016
 ***********************************************************/
public class CritterGUI extends JFrame implements ActionListener, Runnable{

    /** simulation speed */
    private final int DELAY = 50;

    /** is simulation currently runnning? */
    private boolean isRunning;  

    /** the simulation object that controls everything */
    private Simulation world; 

    /** displays updated statistics */
    JTextArea statsArea;

    JButton ants;
    JButton birds;
    JButton vultures;
    JButton hippos;
    JButton cats;
    JButton start;
    JButton stop;

    JMenuItem quitItem;
    JMenuItem clearItem;

    /************************************************************
    Main method displays the simulation GUI
     ************************************************************/
    public static void main(String arg[]){
        CritterGUI gui = new CritterGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Critter Simulation");
        gui.setSize(600,600);
        gui.pack();
        gui.setVisible(true);
    }

    /************************************************************
    Create the GUI
     ************************************************************/
    public CritterGUI(){

        // simulation is turned off 
        isRunning = false;

        // create the lay out
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();

        // Place the simulation on the screen
        position.gridx = 0;
        position.gridy = 1;
        position.gridwidth = 6;           
        world = new Simulation();
        add(world, position);

        // Place a label
        position.gridx = 6;
        position.gridy = 0;  
        add(new JLabel("Live Stats"),position);

        // Place stats area below the label
        statsArea = new JTextArea(7,12);
        statsArea.setBackground(Color.YELLOW);
        position.gridx = 6;
        position.gridy = 1;   
        position.anchor = GridBagConstraints.PAGE_START;
        add(statsArea, position);  
        statsArea.setText(world.getStats());
        
        
        position.gridwidth = 1;
        
        // FIX ME: place each button
        start = new JButton( "Start" );
        position.gridx = 1;
        position.gridy = 0; 
        position.insets = new Insets(5,5,5,0); 
        add(start, position);
        start.addActionListener(this);
        
        // FIX ME: place each button
        stop = new JButton( "Stop" );
        position.gridx = 2;
        position.gridy = 0;  
        position.insets = new Insets(5,5,5,0); 
        add(stop, position);
        stop.addActionListener(this);

        // FIX ME: place each button
        ants = new JButton( "Ants" );
        ants.setForeground(Color.RED);
        position.gridx = 0;
        position.gridy = 7;  
        position.insets = new Insets(5,5,5,0); 
        add(ants, position);
        ants.addActionListener(this);

        // FIX ME: place each button
        birds = new JButton( "Birds" );
        birds.setForeground(Color.BLUE);
        position.gridx = 1;
        position.gridy = 7; 
        position.insets = new Insets(5,5,5,0);  
        add(birds, position);
        birds.addActionListener(this);

        // FIX ME: place each button
        vultures = new JButton( "Vultures" );
        vultures.setForeground(Color.GRAY);
        position.gridx = 2;
        position.gridy = 7; 
        position.insets = new Insets(5,5,5,0);  
        add(vultures, position);
        vultures.addActionListener(this);

        // FIX ME: place each button
        hippos = new JButton( "Hippos" );
        hippos.setForeground(Color.BLACK);
        position.gridx = 3;
        position.gridy = 7;  
        position.insets = new Insets(5,5,5,0); 
        add(hippos, position);
        hippos.addActionListener(this);
        
        // FIX ME: place each button
        cats = new JButton( "Cats" );
        cats.setForeground(Color.PINK);
        position.gridx = 4;
        position.gridy = 7;  
        position.insets = new Insets(5,5,5,0); 
        add(cats, position);
        cats.addActionListener(this);
        
        setupMenus();

        // Advanced topic! this must be at the end of this method
        // start the simulation in separate thread
        new Thread(this).start();
    }

    /************************************************************
    Respond to button clicks
    @param e action even triggered by user
     ************************************************************/
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == quitItem){
            System.exit(1);
        }

        if(e.getSource() == clearItem){
            world.reset();
        }

        if(e.getSource() == ants){ 
            world.addAnts(10);
        }

        if(e.getSource() == birds){ 
            world.addBirds(10);
        }

        if(e.getSource() == vultures){ 
            world.addVultures(10);
        }

        if(e.getSource() == hippos){ 
            world.addHippos(10);
        }
        
        if(e.getSource() == cats){
            world.addCats(10);
        }
        
        if(e.getSource() == start){
            isRunning = true;
        }
        
        if(e.getSource() == stop){
            isRunning = false;
        }

        // Afterwards, update display and statistics
        world.repaint();
        statsArea.setText(world.getStats());
    }

    /************************************************************
    Once started, this method runs forever in a separate thread
    The simulation only advances and displays if the boolean
    variable is currently true
     ************************************************************/
    public void run(){
        try {

            // run forever
            while(true) {

                // only update simulation if it is running
                if (isRunning) {
                    world.oneStep();
                    statsArea.setText(world.getStats());
                }

                // pause between steps.  Otherwise, the simulation
                // would move too quickly to see
                Thread.sleep(DELAY);
            }
        }
        catch (InterruptedException ex) {
        }
    }
    
    private void setupMenus()
    {
        JMenuBar menus = new JMenuBar();
        setJMenuBar(menus);
        
        JMenu fileMenu = new JMenu("File");
        menus.add(fileMenu);
        
        quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(this);
        
        clearItem = new JMenuItem("Clear");
        clearItem.addActionListener(this);
        
        fileMenu.add(clearItem);
        fileMenu.add(quitItem);
    }
}