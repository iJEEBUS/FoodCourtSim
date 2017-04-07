import java.awt.*;
/******************************************************
The BIRD class extends GVcritter.  This gives us access
to all public or protected members in GVcriter.

@author Scott Grissom
@version August 2016
******************************************************/
public class Cat extends GVcritter{

    // start direction for this BIRD: south or north
    private Direction dir;    

/*****************************************************
Create starting values for this BIRD.
@param loc given location for this critter
*****************************************************/  
    public Cat(Location loc){
        super(loc); 
        setColor(Color.PINK);
        setSpecies(Species.CAT);
        
        // pick a random start direction (north or south)
        
        if((Math.random()*14) <3)
            dir = Direction.NORTH;
        else if((Math.random()*14) <7)
            dir = Direction.EAST;
        else if((Math.random()*14) <10)
            dir = Direction.SOUTH;
        else
            dir = Direction.WEST;
    }

/*****************************************************
Birds always ROAR their opponents

@param opponent who is the critter fighting?
@return attack strategy
*****************************************************/     
    public Attack getAttack(GVcritter opponent) {
        return Attack.MEOW;
    }

/*****************************************************
Birds move in a southeast or norteast direction

@return desired direction of next step
*****************************************************/     
    public Direction getMoveDirection(){
        
        // increment the steps for this BIRD
        steps++;
        
        int rand = (int)(Math.random()*17);
        // alternate directions every other step
        if(rand < 4)
            return Direction.NORTH;
        else if(rand < 8)
            return Direction.EAST;
        else if(rand < 12)
            return Direction.SOUTH;
        else
            return Direction.WEST;
    }
}