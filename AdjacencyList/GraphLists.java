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
            t.next = adj[u]; // assign your current node address of previous
            adj[u] = t; // start working with adj, not t! (They are same locations btw)
            t.vert = v;
            t.wgt = wgt;

            Node t2 = new Node();
            t2.next = adj[v]; 
            adj[v] = t2;
            t2.vert = u;
            t2.wgt = wgt;
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
            System.out.print(" Sentinel break");
        }
        System.out.println("");
    }

    // method to initialise Depth First Traversal of Graph
    public void DF( int s) 
    {
        int v;
        id = 0;

        for (v = 1; v <= V; v++) {
            visited[v] = 0;
        }

        dfVisit(0, s);
    }

    // Recursive Depth First Traversal for adjacency lists
    private void dfVisit( int prev, int v)
    {
        /*visited[v] = ++id;
        System.out.println("Visited vertex: " + toChar(v) + " along edge: " + toChar(prev) + " -- " + toChar(v));

        // recur for all adjacent vertexes
        for (int u = 1; u <= V; ++u) {
            for (Node t = adj[v]; t != sentinel; t = t.next) {
                if(visited[v] == 0) {
                    dfVisit(v, u);
                }
            }
        }

        for (int u = 1; u <= V; ++u) 
        {
            if (adj[id] != sentinel) 
            {
                if(visited[u] == 0) 
                {
                    dfVisit(v, u);
                }
            }
        }*/

        visited[v] = ++id;
        System.out.println("Depth First: Visited vertex: " + toChar(v) + " along edge: " + toChar(prev) + " -- " + toChar(v));

        // traverse through vertexes
        for (int u = 1; u <= V; ++u) {
            if (adj[v][u] != 0) {
                if(visited[u] == 0) {
                    dfVisit(v, u);
                }
            }
        }

    }

    public static void main(String[] args) throws IOException
    {
        int s = 7;
        String fname = "graph2.txt";               

        GraphLists g = new GraphLists(fname);
       
        g.display();
        
        g.DF(s);
    }

}

