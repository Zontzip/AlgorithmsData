// QueueTest.java
// Allocation of Queue objects in main()

class Queue {

	// nested class: nodes are local to the queue
	private class Node {
		int data;
		Node next;
	}

	Node head = null; // head is a node representing the TOP of the queue
	Node tail = null; // tail is a node representing the BOTTOM of the queue

	// constructor initialises nodes
	public Queue() {
		head = null;
		tail = null; // tail will always be null (a sentinel) that acts as a terminator
	}


	public void display() {
		System.out.println("\nThe queue values are: ");

		Node temp = head;
		while(temp != null) {
			System.out.print( temp.data + "  ");
			temp = temp.next;
		}
	
		System.out.println("\n");
	}


	public void enQueue(int x) {
		Node newTail = new Node();

		newTail.data = x;

		if (head == null) {
			head = newTail;
			tail = newTail;
		} else {
                // The new node becomes the new tail of the list.
                // (The head of the list is unaffected.)
             tail.next = newTail;
             tail = newTail;
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

		return firstItem;
	}


	public boolean isEmpty() {
		return (head == tail);
	}

} // end of Queue class



class QueueTest {

	// try out the ADT Queue using static allocation
	public static void main(String[] arg) {

		Queue q = new Queue();

		System.out.println("Inserting ints from 9 to 1 into queue gives...\n");
		for (int i = 9; i > 0; --i) {
			q.enQueue(i);
		}

		q.display();

		/*for (int i = 9; i > 0; --i) {
			q.deQueue();
		}

		if (q.isEmpty() == true) {
			System.out.println("Queue is empty");
		} else {
			System.out.println("Well shit, that didn't work...");
		}

		if( ! q.isEmpty()) {
			System.out.println("Deleting value from queue: " + q.deQueue() + "\n");

			System.out.println("Adding value to queue: " + 27 + "\n");
			q.enQueue(27);
			q.display();
		}*/
	}
} //end of Test class

