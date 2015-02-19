/* Stack implementation using Linked Lists
   
   Name: Alex Kiernan

   Date: 2015-01-23
*/

class Stack {
    
    class Node {
        int data;
        Node next; // reference [pointer] to next node  
    }
    private Node top;
      
    public Stack()
    { 
        top = null;
    }
        
    public void push(int x) {
        Node t = new Node();
        t.data = x; // assign value to new node
        t.next = top;
        top = t;
    }

    // only to be called if list is non-empty.
    // Otherwise an exception should be thrown.
    public int pop() {
    	int x = top.data;
    	top = top.next;
    	return x;    
    } 

    public boolean isEmpty() {
    	if (top == null) {
            System.out.println("Empty brah!");
            return true;
        } else {
            System.out.println("Swimming brah!");
            return false;
        }
    }


    public void display() {
        Node t = top;
        //Console.Write("\nStack contents are:  ");
        System.out.println("\nStack contents are:  ");
        
        while (t != null) {
            //Console.Write("{0} ", t.data);
            System.out.print(t.data + " ");
            t = t.next;
        }
        //Console.Write("\n");
        System.out.println("\n");
    }

    public boolean isMember(int x) {
    	Node t = top;

    	while (t != null) {

    		if (t.data == x) {
    			System.out.println(t.data + " is a memeber of the stack \n");
    			return true;
    		} else {
    			t = t.next;
    		}
    	}

    	return false;
    }

}


public class StackTest
{
    public static void main( String[] arg){
        Stack s = new Stack();
        //Console.Write("Stack is created\n");
        System.out.println("Stack is created\n");

        s.isEmpty();
        
        s.push(10); s.push(3); s.push(11); s.push(7);
        s.display();

        s.isEmpty();

        s.isMember(10);
        
        for (int j = 0; j < 4; j++) {
	        int i = s.pop();
	        System.out.println("Just popped " + i);
	        s.display();
	    }

        s.isEmpty();
    }
}


