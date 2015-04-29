// Kruskal's Minimum Spanning Tree Algorithm
// Union-find implemented using disjoint set trees

import java.io.*;
import java.util.Scanner;
 
class Edge {
    
    // missing code
	public int u;
	public int v;
	public int wgt;
    
    public void show() 
	{
        System.out.println("Edge " + toChar(u) + " -- " + wgt + " -- " + toChar(v)) ;
    }
    
    // convert vertex into char for pretty printing
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
}


class Heap
{
	private int[] h;
    int N, Nmax;
    Edge[] edge;


    // Bottom up heap constructor
    public Heap(int _N, Edge[] _edge) 
	{
        int i;
        Nmax = N = _N;
        h = new int[N+1];
        edge = _edge;
       
        // initially just fill heap array with 
        // indices of edge[] array.
        for (i=0; i <= N; ++i) 
            h[i] = i;
           
        // Then convert h[] into a heap
        // from the bottom up.
        for(i = N/2; i >= 1; i--) {
            siftDown(i);
        }
    }

    private void siftDown( int k) 
	{
		int v, j;
		v = h[k];
		while(2 * k <= N)
		{
			j = 2*k;
			if((j < N) && (edge[h[j+1]].wgt < edge[h[j]].wgt)) {
				++j;
			}
			if(edge[v].wgt <= edge[h[j]].wgt) {
				break;
			}

			h[k] = h[j];
			k = j;
		}

		h[k] = v;
    }


    public int remove() 
	{
        h[0] = h[1];
        h[1] = h[N--];
        siftDown(1);
        return h[0];
    }
	
	/*Method to display the content of heap
	public void display() 
    {
        Console.WriteLine("{0}", h[1]);

        for(int i = 1; i <= N/2; i = i * 2) {
            for(int j = 2*i; j < 4*i && j <= N; ++j)
                Console.Write("{0}  ", h[j]);
            Console.Write("\n");
        }
    }*/
}

// UnionFind partition to support union-find operations
class UnionFindSets
{
    private int[] treeParent;
    private int N;
    
    public UnionFindSets(int V) //constructor;
    {
       N = V;
	   treeParent = new int[N+1];
	   for(int i = 1; i <= N; i++)
	   {
			treeParent[i] = i;
	   }
    }

    public int findSet(int vertex)
    {   
       while(vertex != treeParent[vertex])
	   {
			vertex = treeParent[vertex];
	   }
	   return vertex;
    }
    
    public void union(int set1, int set2)
    {
      treeParent[set2] = set1;
    }
    
    public void showTrees()
    {
        int i;

        for(i=1; i<=N; ++i) 
        {
            System.out.println(toChar(i) + " --> " + toChar(treeParent[i]));
        }

        System.out.println("\n");
    }
    
    public void showSets()
    {
		for(int i=1; i<=N; ++i)
		{
			int j = findSet(i);
			System.out.println("Element " + toChar(i) + " in set " + toChar(treeParent[j]));
		}

		System.out.println("\n");
    }

    private void showSet(int root)
    {
		System.out.println("The set with root " + toChar(root) + " contains the following elements: ");
		
		for(int i=1; i<=N; ++i)
		{
			if(treeParent[i] == root)
				System.out.println(toChar(i) + " ");
		}
		System.out.println("\n");
    }
    
    private char toChar(int u)
    {  
        return (char)(u + 64);
    }
}

class Graph 
{ 
    private int V, E;
    private Edge[] edge;
    private Edge[] mst;        

    public Graph(String graphFile) throws IOException
    {
        int u, v;
        int w, e;

        FileReader fr = new FileReader(graphFile);
        BufferedReader reader = new BufferedReader(fr);
               
        String splitter = "\\s+";  // multiple whitespace as delimiter
        String line = reader.readLine(); 
        String[] parts = line.split(splitter);

        // find out the number of vertices and edges
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);

        
        // create edge array
		edge = new Edge[E+1];
        
        // read the edges
		System.out.println("Reading edges from text file");

	    for(e = 1; e <= E; ++e)
	    {
            line = reader.readLine();
            parts = line.split(splitter);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]); 
            w = Integer.parseInt(parts[2]);
            
			Edge ed = new Edge();
			ed.u = u;
			ed.v = v;
			ed.wgt = w;
			edge[e] = ed; 
  
            System.out.println("Edge " + toChar(u) + "--" + "(" + w + ") -- " + toChar(v));
        }
		
		for(e = 1; e <= E; ++e)
	    {
			System.out.println("\nedge[" + e + "] = " + edge[e].u + ", " + edge[e].v + ", " +  edge[e].wgt);
		}
		System.out.println("\n\n");
    }


	/**********************************************************
	*
	*       Kruskal's minimum spanning tree algorithm
	*
	**********************************************************/
	public Edge[] MST_Kruskal() 
	{
	    int ei, i = 0;
	    Edge e;
	    int uSet, vSet;
	    UnionFindSets partition;
		int wgt_sum = 0;
	    
	    // create edge array to store MST
	    // Initially it has no edges.
	    mst = new Edge[V-1];

	    // priority queue for indices of array of edges
	    Heap h = new Heap(E, edge);

	    // create partition of singleton sets for the vertices
	    partition = new UnionFindSets(V);
		
	    partition.showSets();
		//Comment this code out if you want to see what is inside the heap at each step
		//Console.WriteLine("Heap content is:");
		//h.display();
		
	    while(i < V-1) 
		{
			ei = h.remove();
			//I used the commented code to see how are elements removed from the heap each time
			//Console.WriteLine("\nRemoving element {0} from heap", ei);
			//h.display();
			e = edge[ei];
			uSet = partition.findSet(e.u);
			vSet = partition.findSet(e.v);
			if(uSet != vSet)
			{
				partition.union(uSet, vSet);
				mst[i++] = e;
				wgt_sum += e.wgt;
			}
	    }
		System.out.print("Weight of MST is: " + wgt_sum);
	    return mst;
		
	}


	    // convert vertex into char for pretty printing
	    private char toChar(int u)
	    {  
	        return (char)(u + 64);
	    }

	    public void showMST()
	    {
	        System.out.print("\nMinimum spanning tree build from following edges:\n");
	        for(int e = 0; e < V-1; ++e) 
			{
	            mst[e].show(); 
	        }
	        System.out.print("\n");
	       
	    }

} // end of Graph class

// test code
public class KruskalTrees {
    public static void main(String[] args) throws IOException
    {
        String fname = "graph.txt";
        //Console.Write("\nInput name of file with graph definition: ");
        //fname = Console.ReadLine();

        Graph g = new Graph(fname);

        g.MST_Kruskal();

        g.showMST();
    }
}