package foodCourtSim;

/*************************************************************************************
 *	Creates a Linked List and contains methods which allow us to navigate said
 * Linked List
 * 
 * @author Ron, Patryk, Mitch, Tyler
 ************************************************************************************/
public class LinkedList<E> {

	private Node<E> top;
	private Node<E> tail;
	private int tot;

	public LinkedList() {
		top = null;
		tail = null;
		tot = 0;
	}

	public void addFirst(E data) {
		Node<E> temp = new Node<E>(data, null, top);
		
		if(top != null)
			top.setAbove(temp);
		else
			tail = temp;
		
		top = temp;
		tot++;
	}

	public void addLast(E data) {
		if(top != null) {
			tail.setBelow(new Node<E>(data, tail, null));
			tail = tail.getBelow();
		}else
			tail = top = new Node<E>(data, null, null);
		
		tot++;
	}

	public void add(E data) {
		addLast(data);
	}

	public void addAll(E[] data) {
		for(int i = 0; i < data.length; i++)
			addLast(data[i]);
	}

	public E removeFirst() {
		Node<E> temp = top;
		temp.getBelow().setAbove(null);
		top = temp.getBelow();
		
		tot--;
		
		return temp.getData();
	}

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
	
	public int size() {
		return tot;
	}
	
	public void clear() {
		top = null;
		tail = null;
		tot = 0;
	}

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
