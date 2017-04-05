package foodCourtSim;

/*************************************************************************************
 * Interface that is used for the Person and Eatery classes to make sure 
 * that they have an event method.
 * 
 * @author Ron
 ************************************************************************************/
public interface ClockListener {
	public void event(int tick);  // the method that is called by the clock
}
