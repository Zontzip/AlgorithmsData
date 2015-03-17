// Simple weighted graph representation 
// Uses an Adjacency Matrix, suitable for dense graphs
// Depth first search
//
// Name: Alex Kiernan

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

class GraphMatrix 
{
    // V = number of vertices
    // E = number of edges
    // adj[ ][ ] is the adjacency matrix
    private int V, E;
    private int[][] adj;
    
    // used for traversing graph
    private int[] visited;
    private int id;
   
    // default constructor
    public GraphMatrix(String graphFile)  throws IOException
    {
        int u, v;
        int e, wgt;

		FileReader fr = new FileReader(graphFile);
		BufferedReader reader = new BufferedReader(fr); // efficient
        
        String splitter = "\\s+";  // multiple whitespaces as delimiter		   
		String line = reader.readLine(); // read file buffer line by line
        String[] parts = line.split(splitter); // array composed of line split by splitter!
        //System.out.println("Parts[] = " + parts[0] + " " + parts[1] + "\n");
		
        // find out number of vertices and edges
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);
        System.out.println("Vertices: " + V);
        System.out.println("Edges: " + E + "\n");

        // create adjacency matrix, initialised to null
        adj = new int[V+1][V+1];        
        
        visited = new int[V+1];
        
       // read the edges
        System.out.println("Reading edges from text file");
        for(e = 1; e <= E; ++e)
        {
            line = reader.readLine();
            parts = line.split(splitter);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]); 
            wgt = Integer.parseInt(parts[2]);
            
            System.out.println("Edge " + toChar(u) + "--(" + wgt + ")--" + toChar(v));    
            // edge into adjacency matrix     
            adj[u][v] = wgt;
            adj[v][u] = wgt;
    	}	       
    }

	// convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
    
	
    // method to display the graph representation
    public void display() 
    {
        int u,v;

        for(v=1; v<=V; ++v) {
            System.out.print("\nadj[" + v + "] = ");
            for(u=1; u<=V; ++u) 
                System.out.print("  " + adj[u][v]);
        }    
        System.out.println("");
    }

    // method to initialise Depth First Traversal of Graph
    public void DF( int s) 
    {
    	int v;
        int id = 0;

    	for (v = 1; v < V; v++) {
    		visited[v] = 0;
    	}
    	dfVisit(0, s);
    }

	// Recursive Depth First Traversal for adjacency matrix
    private void dfVisit( int prev, int v)
    {
    	visited[v] = ++id;
    	System.out.println("Depth First: Visited vertex: " + toChar(v) + " along edge: " + toChar(prev) + " -- " + toChar(v));

        // traverse through vertexes
    	for (int u = 1; u <= V; ++u) {
            if (adj[v][u] != 0) {
                if(visited[u] == 0) {
                    System.out.println("dfVisit(" + u +")");
                    dfVisit(v, u);
                }
            }
        }
    }

    /*public void BF(int s) {
        Queue q = new Queue();
        int v;
        id = 0;

        for (v = 1; v < V; ++v) {
            visited[v] = 0;
        }

        q.enQueue(s);

        while(! q.isEmpty()) {
            v = q.deQueue();
            if(visited[v] == 0) {
                visited[v] = ++id;

                for (int u = 1; u <= V; ++u) {
                    if (visited[u] == 0) {
                        q.enQueue(u);
                    }// end if
                    System.out.println("Breadth First: Visited vertex: " + toChar(v) + " along edge: " + toChar(u) + " -- " + toChar(v));

                } // end for
            } // end if
        } // end while
    }*/

    public static void main(String[] args) throws IOException
    {
        int s = 7;
        String fname = "graph.txt";               

        GraphMatrix g = new GraphMatrix(fname);
       
        g.display();
        
        g.DF(s);
        //g.BF;
    }
}