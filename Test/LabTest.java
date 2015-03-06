/* Class test
   
   Name: Alex Kiernan

   Date: 2015-02-20
*/

class Stack {
    
    class Node {
        char data;
        Node next; // reference [pointer] to next node  
    }
    private Node top;
      
    public Stack()
    { 
        top = null;
    }
        
    public void push(char x) {
        Node t = new Node();
        t.data = x; // assign value to new node
        t.next = top;
        top = t;
    }

    // only to be called if list is non-empty.
    // Otherwise an exception should be thrown.
    public char pop() {
    	char x = top.data;
    	top = top.next;
    	return x;    
    } 

    public boolean isEmpty() {
    	if (top == null) {
            System.out.println("The stack is empty :o \n");
            return true;
        } else {
            System.out.println("The stack is full :) \n");
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

    public boolean isMember(char x) {
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

    public boolean remove(char x) {
        Node t = top;
        System.out.println("Attempting to remove: " + x);

        while (t != null) {
            if (t.next.data == x) { 
                t.next = t.next.next;                
                System.out.println(x + " was removed from the stack!");
                return true;
            } else {
                t = t.next;
            }
        }

        return false;
    }

}


public class LabTest
{
    public static void main( String[] arg){
        Stack s = new Stack();
        //Console.Write("Stack is created\n");
        System.out.println("Stack is created\n");

        s.isEmpty();
        
        s.push('c'); s.push('d'); s.push('a'); s.push('h');
        s.display();

        s.remove('h');
        s.display();

        /*s.isEmpty();

        s.isMember('h');
        
        for (int j = 0; j < 4; j++) {
	        char i = s.pop();
	        System.out.println("Just popped " + i);
	        s.display();
	    }

        s.isEmpty();*/
    }
}


