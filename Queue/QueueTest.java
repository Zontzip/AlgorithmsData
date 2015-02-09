// QueueTest.java
// Allocation of Queue objects in main()

class Queue {

	// nested class: nodes are local to the queue
    private class Node {
        int data;
        Node next;
    }

    Node z; // z is an initial node
    Node head; // head is a node representing the TOP of the queue
    Node tail; // tail is a node representing the BOTTOM of the queue

    // constructor initialises nodes
    public Queue() {
        z = new Node(); 
        z.next = z; // z.next points to z, which at this time is the only node
        head = z;  	// head of the queue contains z, which again is the only node at this time
        tail = null; // tail will always be null (a sentinel) that acts as a terminator
    }


  public void display() {
    System.out.println("\nThe queue values are:\n");

    Node temp = head;
    while( temp != temp.next) {
        System.out.print( temp.data + "  ");
        temp = temp.next;
    }
    System.out.println("\n");
  }


  public void enQueue(int x) {
    Node temp; // temp is our new node

    temp = new Node();
    temp.data = x; // our new nodes data attribute holds the number we want to store
    temp.next = z; // in a queue, the newest member is at the back so next is node is z, the initial node

    if(head == z)    // case of empty list
        head = temp;
    else                // case of list not empty
        tail.next = temp;

    tail = temp;        // new node is now at the tail
  }


  // assume the queue is non-empty when this method is called
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


  /*public boolean isEmpty() {
    do yourself
  }*/

} // end of Queue class



class QueueTest {
  
  // try out the ADT Queue using static allocation
  public static void main(String[] arg) {

    Queue q = new Queue();

    System.out.println("Inserting ints from 9 to 1 into queue gives:\n");
    for (int i = 9; i > 0; --i) {
       q.enQueue(i);
    }

    q.display();

    q.deQueue();

    q.display();

   /* if( ! q.isEmpty())
        System.out.println("Deleting value from queue " + q.deQueue() + "\n");

    System.out.println("Adding value to queue " + 27 + "\n");
    q.enQueue(27);
    q.display();
*/
  }

} //end of Test class

