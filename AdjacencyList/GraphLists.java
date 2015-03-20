/* Simple weighted graph representation that uses an adjacency linked list.
 * Contains nodes and depth/breadth first search methods.
 * 
 * Name: Alex Kiernan
 *
 */

import java.io.*;

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

class GraphLists {
    class Node {
        public int vert;
        public int wgt;
        public Node next;
    }
    
    // V = number of vertices
    // E = number of edges
    // adj[] is the adjacency lists array
    private int V, E;
    private Node[] adj;
    private Node sentinel;
    
    // used for traversing graph
    private int[] visited;
    private int id;
    
    
    // default constructor
    public GraphLists(String graphFile)  throws IOException
    {
        int i, j;
        int u, v;
        int e, wgt;
        Node node;

        FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr);
	           
        String splitter = "\\s+";  // multiple whitespace as delimiter
		String line = reader.readLine(); 
        String[] parts = line.split(splitter);
        //System.out.println("Parts[] = " + parts[0] + " " + parts[1]);
        
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);
        System.out.println("Vertices: " + V);
        System.out.println("Edges: " + E + "\n");

        // create sentinel node
        sentinel = new Node(); 
        sentinel.next = sentinel;
        
        // create adjacency lists, initialised to sentinel node 
        visited = new int[V+1];
        adj = new Node[V+1];
        for(i = 1; i <= V; ++i) {
           adj[i] = sentinel; 
        }
        
        // read the edges
        System.out.println("Reading edges from text file");

        // missing for loop here
        for(e = 1; e <= E; ++e) {
            line = reader.readLine();
            parts = line.split(splitter);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]); 
            wgt = Integer.parseInt(parts[2]);

            Node t1 = new Node();
            t1.next = adj[u]; // point next node field of new node to the previous node
            adj[u] = t1; // start working with adj, not t! (They are same locations btw)
            adj[u].vert = v;
            adj[u].wgt = wgt;

            Node t2 = new Node();
            t2.next = adj[v]; 
            adj[v] = t2;
            adj[v].vert = u;
            adj[v].wgt = wgt;
        }
    }
   
    // convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
    
    // method to display the graph representation
    public void display() {
        int v;
        Node n;
        
        for(v=1; v<=V; ++v) {

            System.out.print("\nadj[" + toChar(v) + "] ->" );

            for(n = adj[v]; n != sentinel; n = n.next) {
                System.out.print(" |" + toChar(n.vert) + " | " + n.wgt + "| ->");    
            }
            System.out.print(" Sentinel");
        }
        System.out.println("");
    }

    // method to initialise Depth First Traversal of Graph
    public void DF( int s) 
    {
        id = 0;

        for (int v = 1; v <= V; v++) { // initialise array, that's it
            visited[v] = 0;
        }

        dfVisit(0, s); // first call based on vertx passed in, prev is obviously 0 
    }

    // Recursive Depth First Traversal for adjacency lists
    private void dfVisit( int prev, int v)
    {
        Node n;

        visited[v] = ++id;
        System.out.println("Depth First: Visited vertex: " + toChar(v) + " along edge: " + toChar(prev) + " -- " + toChar(v));

        for (n = adj[v]; n != sentinel; n = n.next) // go to all possible adjacent nodes
        {
            if(visited[n.vert] == 0) { // if node not visited, put on stack
                dfVisit(v, n.vert); // pass u as next node
            }
        }
    }

    public void BF(int s) {
        Queue q = new Queue();
        int v;
        id = 0;

        Node n;

        for (v = 1; v < V; ++v) {
            visited[v] = 0;
        }

        q.enQueue(s); // enqueue our first vertex

        while(q.isEmpty() == false) {
            //q.display();
            v = q.deQueue();

            if(visited[v] == 0) 
            {
                visited[v] = ++id;
                
                for (n = adj[v]; n != sentinel; n = n.next) // go to all possible adjacent nodes
                {
                    //System.out.println("n.vert!!: " + n.vert);

                    if(visited[n.vert] == 0) { // if node not visited, put on queue
                        q.enQueue(n.vert);
                    }
                } // end for 
            } // end if
            System.out.println("Breadth First: Visited vertex: " + toChar(n.vert));
        } // end while
    }

    public static void main(String[] args) throws IOException
    {
        int s = 7;
        String fname = "graph2.txt";               

        GraphLists g = new GraphLists(fname);
       
        g.display();
        
        //g.DF(s);
        g.BF(s);
    }

}

