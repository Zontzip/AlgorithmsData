/* Queue implementation using Linked Lists
   
   Name: Alex Kiernan

   Date: 2015-01-30
*/

class Queue {

	// nested class: nodes are local to the queue
	private class Node {
		int data;
		Node next;
		Node prev;
	}

	Node head; // head is a node representing the TOP of the queue
	Node tail; // tail is a node representing the BOTTOM of the queue

	// constructor initialises nodes
	public Queue() {
		head = null;
		tail = null; 
	}

	public void display() {
		System.out.println("\nThe queue values are: ");

		Node temp = head;
		while (temp != null) {
			System.out.print( temp.data + "  ");
			temp = temp.next;
		}
	
		System.out.println("\n");
	}

	public void enQueue(int x) {
		Node t = new Node();

		t.data = x;

		if (head == null) {
			head = t;
			tail = t;
		} else {
            // The new node becomes the new tail of the list.
			// (The head of the list is unaffected.)
             tail.next = t;
             tail = t;
		}
	}

	public int deQueue() {
		int firstItem = head.data;

		head = head.next;  // The previous second item is now first.
		if (head == null) {
			// The queue has become empty.  The Node that was
			// deleted was the tail as well as the head of the
			// list, so now there is no tail.  (Actually, the
			// class would work fine without this step.)
			tail = null;
		} 
		System.out.println(firstItem + " was removed");

		return firstItem;
	}

	public boolean isEmpty() {
		return (tail == null);
	}

	public boolean isMember(int x) {
		Node temp = head;

		while (temp != null) {
			if (x == temp.data) {
				System.out.println(x + " was found\n");
				return true;
			} else {
				temp = temp.next;
			}
		}

		return false;
	}

} // end of Queue class

class QueueTest {

	// try out the ADT Queue using static allocation
	public static void main(String[] arg) {

		Queue q = new Queue();

		System.out.println("Inserting ints from 9 to 1 into queue...\n");
		for (int i = 9; i > 0; --i) {
			q.enQueue(i);
		}

		q.display();

		q.isMember(5);

		System.out.println("Queue empty: " + q.isEmpty());

		for (int i = 9; i > 0; --i) {
			q.deQueue();
		}

		System.out.println("Queue empty: " + q.isEmpty());

		if (q.isEmpty() == true) {
			System.out.println("Queue is empty bro");
		} else {
			System.out.println("Well shit, that didn't work...");
		}

		q.enQueue(19);

		System.out.println("Queue empty: " + q.isEmpty());

		if(q.isEmpty() == false) {
			System.out.println("Deleting value from queue: " + q.deQueue() + "\n");

			System.out.println("Adding value to queue: " + 27 + "\n");
			q.enQueue(27);
			q.display();
		}

		if (q.isEmpty() == true) {
			System.out.println("Queue is empty bro");
		} else {
			System.out.println("Well shit, that didn't work...");
		}

		q.deQueue();
	}
} //end of Test class

