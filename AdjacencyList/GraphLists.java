// Simple weighted graph representation 
// Uses an Adjacency Linked Lists, suitable for sparse graphs
// Uses nodes and Depth First search
//
// Name: Alex Kiernan

import java.io.*;

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

            Node t = new Node();
            t.next = adj[u]; // point next node field of new node to the previous node
            adj[u] = t; // start working with adj, not t! (They are same locations btw)
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

            System.out.print("\nadj[" + v + "] ->" );

            for(n = adj[v]; n != sentinel; n = n.next) {
                System.out.print(" |" + n.vert + " | " + n.wgt + "| ->");    
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

    public static void main(String[] args) throws IOException
    {
        int s = 7;
        String fname = "graph.txt";               

        GraphLists g = new GraphLists(fname);
       
        g.display();
        
        g.DF(s);
    }

}

