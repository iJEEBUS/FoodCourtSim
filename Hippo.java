import java.awt.*;
/******************************************************
The Ant class extends GVcritter.  This gives us access
to all public or protected members in GVcriter.

@author Scott Grissom
@version August 2016
******************************************************/
public class Hippo extends GVcritter{

    // start direction for this Ant: south or north
    private Direction dir; 
    private int age;

/*****************************************************
Create starting values for this Ant.
@param loc given location for this critter
*****************************************************/  
    public Hippo(Location loc){
        super(loc); 
        setColor(Color.BLACK);
        setSpecies(Species.HIPPO);
        
        age = (int)(Math.random()*200)+300;
        
        int rand = (int)(Math.random()*4+1);
        if(rand == 1)
            dir = Direction.NORTH;
        else if(rand == 2)
            dir = Direction.EAST;
        else if(rand == 3)
            dir = Direction.SOUTH;
        else
            dir = Direction.WEST;
    }

/*****************************************************
Ants always SCRATCH their opponents

@param opponent who is the critter fighting?
@return attack strategy
*****************************************************/     
    public Attack getAttack(GVcritter opponent) {
        if(steps >= age)
            return Attack.FORFEIT;
        else
            return Attack.POUNCE;
    }

/*****************************************************
Ants move in a southeast or norteast direction

@return desired direction of next step
*****************************************************/     
    public Direction getMoveDirection(){
        
        // increment the steps for this Ant
        steps++;
        
        // choose random direction
        if(steps % 5 == 0)
        {
            int a = (int)(Math.random()*4+1);
            if(a == 1)
                dir = Direction.NORTH;
            else if(a == 2)
                dir = Direction.EAST;
            else if(a == 3)
                dir = Direction.SOUTH;
            else
                dir = Direction.WEST;
        }
        return dir;
    }
}