import java.util.*;
import javax.swing.*;
import java.awt.*;

/****************************************************
 * Simulates a 2D world of critters that move around
 * and fight if they inhabit the same location.
 * 
 * @author Scott Grissom 
 * @version August 2016
 ***************************************************/
public class Simulation extends JPanel{

    /** a 2D world of critters */
    private GVcritter[][] theWorld;

    /** a collection of all live critters */
    private ArrayList <GVcritter> allCritters;

    /** control size of the world */
    private final int ROWS=50, COLUMNS=70, SIZE=10;

    /** number of Ants alive in the simulation */
    private int steps, numAnts, numBirds, numHippos, numVultures, numCats;

    /****************************************************
    Constructor instantiates and initializes all 
    instance members.
     ****************************************************/
    public Simulation(){
        theWorld = new GVcritter[ROWS][COLUMNS];
        allCritters = new ArrayList<GVcritter>();   
        numAnts=0;
        numBirds=0;
        numHippos=0;
        numVultures=0;
        numCats=0;
        setPreferredSize(new Dimension(COLUMNS*SIZE, ROWS*SIZE));
    }

    /****************************************************
    Add the requested number of Ants into the simulation.
    Repeatedly ask for a random location that is free.
    Increment the number of Ants in the simulation.

    @param num number of ants
     ****************************************************/ 
    public void addAnts(int num){
        numAnts += num;
        for(int i=1;i<=num;i++){
            // create a new Ant at an open location
            Location loc = getOpenLocation();
            Ant c = new Ant(loc);
            placeCritter(c);
        }
    }

    public void addVultures(int num){
        numVultures += num;
        for(int i = 0; i <=num; i++)
        {
            Location loc = getOpenLocation();
            Vulture c = new Vulture(loc);
            placeCritter(c);
        }
    }

    public void addHippos(int num)
    {
        numHippos += num;
        for(int i = 0; i<=num; i++)
        {
            Location loc = getOpenLocation();
            Hippo c = new Hippo(loc);
            placeCritter(c);
        }
    }

    /****************************************************
    Add the requested number of Birds into the simulation.
    Repeatedly ask for a random location that is free.
    Increment the number of Birds in the simulation.

    @param num number of birds
     ****************************************************/ 
    public void addBirds(int num){
        numBirds += num;
        for(int i=1;i<=num;i++){
            // create a new Ant at an open location
            Location loc = getOpenLocation();
            Bird c = new Bird(loc);
            placeCritter(c);
        }
    }
    
    public void addCats(int num){
        numCats += num;
        for(int i = 0; i <=num; i++)
        {
            Location loc = getOpenLocation();
            Cat c = new Cat(loc);
            placeCritter(c);
        }
    }

    /******************************************************
    Move forward on step of the simulation
     *****************************************************/  
    public void oneStep(){

        // shuffle the arraylist of critters for better performance
        Collections.shuffle(allCritters);
        steps++;

        // step throgh all critters using traditional for loop
        for(int i=0; i<allCritters.size(); i++){
            GVcritter attacker = allCritters.get(i);

            // what location does critter want to move to?
            GVcritter.Direction dir = attacker.getMoveDirection();
            Location previousLoc = attacker.getLocation();
            Location nextLoc = getRelativeLocation(previousLoc, dir);  

            // who is at the next location?
            GVcritter defender = theWorld[nextLoc.getRow()][nextLoc.getCol()];

            // no critters here so OK for critter 1 to move
            if(defender == null){
                theWorld[nextLoc.getRow()][nextLoc.getCol()] = attacker;
                attacker.setLocation(nextLoc);
                theWorld[previousLoc.getRow()][previousLoc.getCol()] = null;

                // both critters the same species so peacefully bypass 
            }else if(attacker.getSpecies() == defender.getSpecies()){

                // update critter locations
                attacker.setLocation(nextLoc);
                defender.setLocation(previousLoc);

                // update positions in the world
                theWorld[nextLoc.getRow()][nextLoc.getCol()] = attacker;
                theWorld[previousLoc.getRow()][previousLoc.getCol()] = defender;

                //different species so they fight at location of critter 2
            }else if(attacker.getSpecies() != defender.getSpecies()){
                fight(attacker, defender);
            }
        }

        // update drawing of the world
        repaint();
    }

    /******************************************************
    Step through the 2D world and paint each location white
    (for no critter) or the critter's color.  The SIZE of 
    each location is constant.

    @param g graphics element used for display
     *****************************************************/      
    public void paintComponent(Graphics g){
        for(int row=0; row<ROWS; row++){
            for(int col=0; col<COLUMNS; col++){
                GVcritter c = theWorld[row][col];

                // set color to white if no critter here
                if(c == null){
                    g.setColor(Color.WHITE);
                    // set color to critter color   
                }else{    
                    g.setColor(c.getColor());
                }

                // paint the location
                g.fillRect(col*SIZE, row*SIZE, SIZE, SIZE);
            }
        }
    }

    private Location getOpenLocation()
    {
        int rows, columns;
        while(true)
        {
            rows = (int)(Math.random()*ROWS);
            columns = (int)(Math.random()*COLUMNS);
            Location loc = new Location(rows,columns);
            for(int i = 0; i < allCritters.size(); i++)
            {
                GVcritter critter = allCritters.get(i);
                if(critter.getLocation() != loc)
                    return loc;
            }
            if(allCritters.size() == 0)
                return loc;
        }
    }

    private void placeCritter(GVcritter c)
    {
        allCritters.add(c);
        theWorld[c.getLocation().getRow()][c.getLocation().getCol()] = c;
    }

    private Location getRelativeLocation(Location loc, GVcritter.Direction d)
    {
        Location newLoc = new Location(loc.getRow(),loc.getCol());
        if(d == GVcritter.Direction.NORTH)
        {
            if(loc.getRow() <= 1)
                newLoc = new Location(ROWS-1,loc.getCol());
            else
                newLoc = new Location(loc.getRow()-1,loc.getCol());
        }else if(d == GVcritter.Direction.EAST)
        {
            if(loc.getCol() >= COLUMNS-1)
                newLoc = new Location(loc.getRow(),1);
            else
                newLoc = new Location(loc.getRow(),loc.getCol()+1);
        }else if(d == GVcritter.Direction.SOUTH)
        {
            if(loc.getRow() >= ROWS-1)
                newLoc = new Location(1,loc.getCol());
            else
                newLoc = new Location(loc.getRow()+1,loc.getCol());
        }else if(d == GVcritter.Direction.WEST)
        {
            if(loc.getCol() <= 1)
                newLoc = new Location(loc.getRow(),COLUMNS-1);
            else
                newLoc = new Location(loc.getRow(),loc.getCol()-1);
        }

        return newLoc;
    }

    public void reset()
    {
        numAnts=0;
        numBirds=0;
        numHippos=0;
        numVultures=0;
        numCats=0;

        for(int i = 0; i < allCritters.size(); i++)
        {
            GVcritter c = allCritters.get(i);
            Location loc = c.getLocation();
            theWorld[loc.getRow()][loc.getCol()] = null;
        }
        allCritters.clear();
    }

    public String getStats()
    {
        String stats = "Steps:      " + steps + "\nAnts:         " + numAnts + "\nBirds:       " + numBirds + "\nHippos:    " + numHippos + "\nVultures:   " + numVultures + "\nCats:         " + numCats;
        return stats;
    }

    private void fight(GVcritter attacker, GVcritter defender)
    {
        int a = 0, d = 0;
        boolean attackerAlive = true, defenderAlive = true;

        /**
         * 1 = POUNCE
         * 2 = SCRATCH
         * 3 = ROAR
         * 4 = FORFEIT
         */

        if(attacker.getAttack(defender) == GVcritter.Attack.POUNCE)
            a = 1;
        else if(attacker.getAttack(defender) == GVcritter.Attack.SCRATCH)
            a = 2;
        else if(attacker.getAttack(defender) == GVcritter.Attack.ROAR)
            a = 3;
        else if(attacker.getAttack(defender) == GVcritter.Attack.FORFEIT)
            a = 4;
        else
            a = 5;

        if(defender.getAttack(attacker) == GVcritter.Attack.POUNCE)
            d = 1;
        else if(defender.getAttack(attacker) == GVcritter.Attack.SCRATCH)
            d = 2;
        else if(defender.getAttack(attacker) == GVcritter.Attack.ROAR)
            d = 3;
        else if(defender.getAttack(attacker) == GVcritter.Attack.FORFEIT)
            d = 4;
        else
            d = 5;

        theWorld[attacker.getLocation().getRow()][attacker.getLocation().getCol()] = null;
        theWorld[defender.getLocation().getRow()][defender.getLocation().getCol()] = null;

        while(attackerAlive && defenderAlive)
        {
            if(a == 4)
                attackerAlive = false;
            if(d == 4)
                defenderAlive = false;

            if((a == 1 && d == 3) || (a == 3 && d == 2) || (a == 2 && d == 1) || (a == 5 && d == 2) || (a == 5 && d == 3))
            {
                defenderAlive = false;
            }else if(a == d){
                if(Math.random() >= .5)
                    attackerAlive = false;
                else
                    defenderAlive = false;
            }else{
                attackerAlive = false;
            }
        }

        if(attackerAlive == false && defenderAlive == true)
        {
            theWorld[defender.getLocation().getRow()][defender.getLocation().getCol()] = defender;
            critterDies(attacker);
        }else{
            theWorld[attacker.getLocation().getRow()][attacker.getLocation().getCol()] = attacker;
            critterDies(defender);
        }

    }

    private void critterDies(GVcritter c)
    {
        if(c.getSpecies() == GVcritter.Species.ANT)
            numAnts--;
        else if(c.getSpecies() == GVcritter.Species.BIRD)
            numBirds--;
        else if(c.getSpecies() == GVcritter.Species.VULTURE)
            numVultures--;
        else
            numHippos--;

        allCritters.remove(c);
    }
}
