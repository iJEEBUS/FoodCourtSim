package foodCourtSim;

/*************************************************************************************
 *	Creates a Node that is used in the navigation of the Linked List
 * 
 * @author Ron, Patryk, Mitch, Tyler
 ************************************************************************************/
public class Node<E> {
	private E data;
	private Node<E> above;
	private Node<E> below;
	
	public Node(E data, Node<E> above, Node<E> below) {
		this.data = data;
		this.above = above;
		this.below = below;
	}
	
	public Node() {
		
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getAbove() {
		return above;
	}

	public void setAbove(Node<E> above) {
		this.above = above;
	}

	public Node<E> getBelow() {
		return below;
	}

	public void setBelow(Node<E> below) {
		this.below = below;
	}
	
}
