package foodCourtSim;

/*************************************************************************************
 *	Creates a Linked List and contains methods which allow us to navigate said
 * Linked List
 * 
 * @author Ron, Patryk, Mitch, Tyler
 ************************************************************************************/
public class LinkedList<E> {

	/** The top pointer for a Linked List */
	private Node<E> top;

	/** The bottom pointer for a Linked List */
	private Node<E> tail;

	/** Total number of nodes in the Linked List */
	private int tot;

	/*************************************************************************************
	 * Constructor for the Linked list. Sets top and tail to null while making tot = 0
	 ************************************************************************************/
	public LinkedList() {
		top = null;
		tail = null;
		tot = 0;
	}

	/*************************************************************************************
	 * Adds a new node to the Linked List at the top
	 * 
	 * @param data the information stored in the Node
	 ************************************************************************************/
	public void addFirst(E data) {
		Node<E> temp = new Node<E>(data, null, top);

		if(top != null)
			top.setAbove(temp);
		else
			tail = temp;

		top = temp;
		tot++;
	}

	/*************************************************************************************
	 * Adds a new node to the Linked List at the bottom
	 * 
	 * @param data the information stored in the Node
	 ************************************************************************************/
	public void addLast(E data) {
		if(top != null) {
			tail.setBelow(new Node<E>(data, tail, null));
			tail = tail.getBelow();
		}else
			tail = top = new Node<E>(data, null, null);

		tot++;
	}

	/*************************************************************************************
	 * Adds a new node to the Linked List at the bottom
	 * 
	 * @param data the information stored in the Node
	 ************************************************************************************/
	public void add(E data) {
		addLast(data);
	}

	/*************************************************************************************
	 * Adds new nodes to the Linked List that are given in an Array format
	 * 
	 * @param data the information to be stored in the Linked List
	 ************************************************************************************/
	public void addAll(E[] data) {
		for(int i = 0; i < data.length; i++)
			addLast(data[i]);
	}

	/*************************************************************************************
	 * Removes a node to the Linked List at the top
	 ************************************************************************************/
	public E removeFirst() {
		Node<E> temp = top;
		temp.getBelow().setAbove(null);
		top = temp.getBelow();

		tot--;

		return temp.getData();
	}

	/*************************************************************************************
	 * Removes a new node to the Linked List at the top
	 ************************************************************************************/
	public E removeLast() {
		Node<E> temp = tail;
		if(tail == null)
			return null;
		if(tail.getAbove() == null) {
			tot--;
			tail = top = null;
			return temp.getData();
		}
		temp.getAbove().setBelow(null);
		tail = temp.getAbove();

		tot--;

		return temp.getData();
	}

	/*************************************************************************************
	 * Returns the node at a certain index
	 * @param i the index to return
	 * @return E the data from a node
	 ************************************************************************************/
	public E get(int i) {
		if(top == null)
			return null;

		Node<E> temp;

		if(i < tot/2) {
			temp = top;
			for(int q = 0; q < i; q++)
				temp = temp.getBelow();
		} else {
			temp = tail;
			for(int q = 0; q < (tot-i-1); q++)
				temp = temp.getAbove();
		}

		return temp.getData();
	}

	/*************************************************************************************
	 * Remove a node at a certain index
	 * @param i the index to remove
	 * @return the data from the specific node
	 ************************************************************************************/
	public E removeIndex(int i) {
		if(top == null)
			return null;

		Node<E> temp;

		if(i < tot/2) {
			temp = top;
			for(int q = 0; q < i; q++)
				temp = temp.getBelow();
		} else {
			temp = tail;
			for(int q = 0; q < (tot-i-1); q++)
				temp = temp.getAbove();
		}

		if(temp.getBelow() == null)
			return removeLast();
		if(temp.getAbove() == null)
			return removeFirst();

		temp.getBelow().setAbove(temp.getAbove());
		temp.getAbove().setBelow(temp.getBelow());

		tot--;

		return temp.getData();
	}

	/*************************************************************************************
	 * Remove data from a specific node
	 * @param i the index to remove data
	 * @return the data from the specific node
	 ************************************************************************************/
	public E removeData(E data) {
		Node<E> temp = top;
		while(true) {
			if(temp.getData() == data)
				break;

			if(temp.getBelow() == null)
				return null;

			temp = temp.getBelow();
		}

		if(temp.getBelow() == null)
			return removeLast();
		if(temp.getAbove() == null)
			return removeFirst();

		temp.getBelow().setAbove(temp.getAbove());
		temp.getAbove().setBelow(temp.getBelow());

		tot--;

		return temp.getData();
	}

	/*************************************************************************************
	 * return the total size of the linked list
	 * @return tot the size of the the linked list
	 ************************************************************************************/
	public int size() {
		return tot;
	}

	/*************************************************************************************
	 * Clears the linked list and resets it
	 ************************************************************************************/
	public void clear() {
		top = null;
		tail = null;
		tot = 0;
	}
	
	/*************************************************************************************
	 * Prints out the Linked List for a visual aid
	 ************************************************************************************/
	public void display() {
		Node<E> temp = top;
		System.out.println("-------------------------------------------------------------------------------------");
		while(true) {
			if(temp == null)
				break;

			System.out.println(temp.getData() + " " + temp + " " + 
					temp.getAbove() + " " + temp.getBelow() + "\n");
			if(temp == tail)
				break;
			temp = temp.getBelow();
		}
		System.out.println("Num nodes: " + tot);
		System.out.println("-------------------------------------------------------------------------------------\n");
	}

	/*************************************************************************************
	 * Main method to run the LinkedList class when called
	 * @param args
	 ************************************************************************************/
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();

		list.addLast("B");
		for(int i = 0; i < 10; i++)
			list.addFirst("" + i);

		list.addLast("A");
		list.removeFirst();
		list.removeLast();
		list.removeLast();
		String[] temp = {"A", "B", "C", "D"};
		list.addAll(temp);
		System.out.println(list.get(12) + "\n");
		//list.clear();
		//System.out.println(list.get(12) + "\n");
		System.out.println(list.removeIndex(3) + "\n");
		//System.out.println(list.removeData("D") + "\n");
		list.display();

		list.clear();
		list.add("H");
		list.display();
		System.out.println(list.removeLast());
		list.display();
	}
}
