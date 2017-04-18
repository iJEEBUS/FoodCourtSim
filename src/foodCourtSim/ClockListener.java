package foodCourtSim;

/*************************************************************************************
 * Interface for all of the objects that implement the
 * ClockListener
 * 
 * @author Ron, Patryk, Mitch, Tyler
 ************************************************************************************/
public interface ClockListener {
	public void event(int tick);  // the method that is called by the clock
}
