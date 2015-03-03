// Simple weighted graph representation 
// Uses an Adjacency Matrix, suitable for dense graphs
// Converted from C# some of which has been commented out

import java.io.*;

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
        
        String splits = "\\s+";  // multiple whitespaces as delimiter		   
		String line = reader.readLine();    
		System.out.println(line); 
		//System.out.println("Splits: " + splits);  
        String[] parts = line.split(splits);
        System.out.println("Parts[] = " + parts[0] + " " + parts[1]);
		
        // find out number of vertices and edges
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);
        System.out.println("Vertices: " + V);
        System.out.println("Edges: " + E);

        // create adjacency matrix, initialised to 0's
        adj = new int[V+1][V+1];        
        
        visited = new int[V+1];
        
       // read the edges
        System.out.println("Reading edges from text file");
        for(e = 1; e <= E; ++e)
        {
            line = reader.readLine();
            parts = line.split(splits);
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
    	System.out.println("Visited vertex: " + v + " along edge: " + prev + " -- " + v);

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
        int s = 4;
        String fname = "graph.txt";               

        GraphMatrix g = new GraphMatrix(fname);
       
        g.display();
        
        g.DF(s);
        //g.BF;
    }

}

