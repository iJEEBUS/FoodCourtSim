package Project_4;

/*************************************************************************************
 * Create an ArrayList<Person> that is to be used to hold the people in order to simulate 
 * a checkout line.
 * 
 * @author Ron
 ************************************************************************************/

public class CheckOutQ {
	/** The ArrayList that is used to represent the check out line */
	private LinkedList<Person> q;
	
	/** The max amount of people that were in the queue */
	private int maxQ;
	
	/********************************************************************************
	 * Constructor for the CheckOutQ class. Initializes an empty ArrayList<Person> 
	 * and sets the maxQ variable to 0.
	 *******************************************************************************/
	public CheckOutQ() {
		q = new LinkedList<Person>();
		maxQ = 0;
	}
	
	/********************************************************************************
	 * Allows us to add a new person to the already established ArrayList that is 
	 * acting as the checkout line
	 * 
	 * @param p the Person object to add to the checkout line
	 *******************************************************************************/
	public void add(Person p) {
		q.add(p);
		if(q.size() > maxQ)
			maxQ = q.size();
	}
	
	/********************************************************************************
	 * Allows us to remove a person from the ArrayList
	 * @return
	 *******************************************************************************/
	public Person remove(int i) {
		return q.removeIndex(i);
	}
	
	/********************************************************************************
	 * Returns the current size of the queue
	 * @return
	 *******************************************************************************/
	public int size() {
		return q.size();
	}
	
	/********************************************************************************
	 * Returns the max size reached by the queue
	 * @return
	 *******************************************************************************/
	public int maxQ() {
		return maxQ;
	}
	
	/********************************************************************************
	 * Allows us to access a person in the queue with a specified index
	 * @return
	 *******************************************************************************/
	public Person get(int index) {
		// if the queue size is 0 then return null
		if(q.size() == 0)
			return null;
		
		return q.get(index);
	}
}