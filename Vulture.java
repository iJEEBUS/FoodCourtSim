import java.awt.*;
/******************************************************
The Ant class extends GVcritter.  This gives us access
to all public or protected members in GVcriter.

@author Scott Grissom
@version August 2016
******************************************************/
public class Vulture extends GVcritter{

    // start direction for this Ant: south or north
    private Direction dir;    

/*****************************************************
Create starting values for this Ant.
@param loc given location for this critter
*****************************************************/  
    public Vulture(Location loc){
        super(loc); 
        setColor(Color.GRAY);
        setSpecies(Species.VULTURE);
        
        int rand = (int)(Math.random()*14+1);
        if(rand <= 4)
            dir = Direction.NORTH;
        if(rand <= 7)
            dir = Direction.WEST;
        if(rand <= 11)
            dir = Direction.SOUTH;
        else
            dir = Direction.EAST;
    }

/*****************************************************
Ants always SCRATCH their opponents

@param opponent who is the critter fighting?
@return attack strategy
*****************************************************/     
    public Attack getAttack(GVcritter opponent) {
        if(opponent.getSpecies() == Species.HIPPO)
            return Attack.SCRATCH;
        else
            return Attack.ROAR;
    }

/*****************************************************
Ants move in a southeast or norteast direction

@return desired direction of next step
*****************************************************/     
    public Direction getMoveDirection(){
        
        // increment the steps for this Ant
        steps++;
        
        // alternate directions every other step
        if(steps%14 < 4)
            return Direction.NORTH;
        else if(steps %14 <7)
            return Direction.WEST;
        else if(steps %14 <11)
            return Direction.SOUTH;
        else
            return Direction.EAST;
    }
}