package foodCourtSim;

/*************************************************************************************
 *	Creates a Node that is used in the navigation of the Linked List
 * 
 * @author Ron, Patryk, Mitch, Tyler
 ************************************************************************************/
public class Node<E> {
	/** The data to be stored in the node */
	private E data;

	/** The node above the specified node */
	private Node<E> above;

	/** The node below the specified node */
	private Node<E> below;

	/*************************************************************************************
	 * The constructor for the Node class 
	 ************************************************************************************/
	public Node(E data, Node<E> above, Node<E> below) {
		this.data = data;
		this.above = above;
		this.below = below;
	}

	/*************************************************************************************
	 * Empty constructor to create a new Node
	 ************************************************************************************/
	public Node() {

	}

	/*************************************************************************************
	 * Get the data from the node
	 * @return data data from the node
	 ************************************************************************************/
	public E getData() {
		return data;
	}

	/*************************************************************************************
	 * Set the data for the node
	 * @param data the data for the node
	 ************************************************************************************/
	public void setData(E data) {
		this.data = data;
	}

	/*************************************************************************************
	 * Returns the node that is above the current node
	 * @return above the node above
	 ************************************************************************************/
	public Node<E> getAbove() {
		return above;
	}

	/*************************************************************************************
	 * Sets the node above the current node
	 * @param above to be above the current node
	 ************************************************************************************/
	public void setAbove(Node<E> above) {
		this.above = above;
	}

	/*************************************************************************************
	 * Gets the node that is below the current node
	 * @return below the node that is below the current node
	 ************************************************************************************/
	public Node<E> getBelow() {
		return below;
	}

	/*************************************************************************************
	 * Sets the node that is below the current node
	 * @param below the node below the current node
	 ************************************************************************************/
	public void setBelow(Node<E> below) {
		this.below = below;
	}
}