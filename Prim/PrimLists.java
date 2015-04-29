/* Simple weighted graph representation that uses an adjacency linked list.
 * Contains nodes and depth/breadth first search methods.
 * 
 * Name: Alex Kiernan
 *
 */

import java.io.*;
import java.util.Scanner;

class Heap
{
    private int[] heap;    // heap array
    private int[] dist;    // record the current distance of a vertex from MST 
    private int[] hPos;    // records position of any vertex within the heap array

    private int N;         // heap size

    public Heap(int hMax, int[] _dist, int[] _hPos)
    {
        N = 0;
        heap = new int[hMax + 1];
        dist = _dist;
        hPos = _hPos;
    }

    public void siftUp(int k)
    {
        // v is the value we want to put in position
        int v = heap[k];

        heap[0] = 0;
        dist[0] = Integer.MIN_VALUE;  // sets first element of heap to infinite

        while (dist[v] < dist[heap[k/2]])
        {
            heap[k] = heap[k/2];
            hPos[heap[k]] = k;
            k = k/2;
        }
        heap[k] = v;
        hPos[v] = k;
    }

    //sort the heap nodes into the correct positions
    public void siftDown(int k)
    {
        int v, j;

        v = heap[k];
        j = 2 * k;

        while (j <= N/2)
        {
            // check if there is value in heap, then find branch
            if (j + 1 <= N && dist[heap[j]] > dist[heap[j + 1]]) {
                ++j;
            }

            // compare node being sifted to position
            if (dist[heap[j]] >= dist[v]) {
                break;
            }

            heap[k] = heap[j];
            k = j;
            j = k * 2;
        }

        heap[k] = v;
        hPos[v] = k;
    }

    public void insert(int x)
    {
        heap[++N] = x; // N is the array position
        //System.out.println("N equals: " + N);
        siftUp(N);
    }

    public int remove()
    {
        heap[0] = heap[1];
        heap[1] = heap[N--];
        siftDown(1);
        return heap[0];
    }

    // check if heap is empty
    public boolean isEmpty()
    {
        return N == 0;
    }
}  // end of Heap class

class Graph 
{
    private int V, E;
    private Node[] adj;

    private Node sentinel;

    public class Node
    {
        public int data;
        public Node next;
        public int vert;
        public int wgt;
    }

    // default constructor
    public Graph(String graphFile) throws IOException
    {
        int u, v;
        int e, wgt;
        Node node;

        FileReader fr = new FileReader(graphFile);
        BufferedReader reader = new BufferedReader(fr);
               
        String splitter = "\\s+";  // multiple whitespace as delimiter
        String line = reader.readLine(); 
        String[] parts = line.split(splitter);

        // find out the number of vertices and edges
        V = Integer.parseInt(parts[0]);
        E = Integer.parseInt(parts[1]);
        System.out.println("Vertices: " + V);
        System.out.println("Edges: " + E + "\n");

        // create the sentinel node
        sentinel = new Node();
        sentinel.next = sentinel;

        // create adjacency lists, initialised to sentinel node 
        adj = new Node[V + 1];

        for (v = 1; v <= V; ++v) {
            adj[v] = sentinel;
        }

        System.out.println("Reading edges from text file...");

        for (e = 1; e <= E; ++e)
        {
            line = reader.readLine();
            parts = line.split(splitter);
            u = Integer.parseInt(parts[0]);
            v = Integer.parseInt(parts[1]); 
            wgt = Integer.parseInt(parts[2]);

            node = new Node();
            node.data = wgt;
            node.vert = u;
            node.next = adj[v]; 
            adj[v] = node;

            node = new Node();
            node.data = wgt;
            node.vert = v;
            node.next = adj[u];
            adj[u] = node;
        }
    }

    // convert vertex into char for pretty printing
    private char toChar(int u)
    {

        return (char)(u + 64);
    }

    public void display()
    {
        int v;
        Node n;

        for (v = 1; v <= V; ++v)
        {
            System.out.print("adj[" + toChar(v) + "] ->" );

            for (n = adj[v]; n != sentinel; n = n.next)
            {
                System.out.print(" | " + toChar(n.vert) + " | " + n.data + " | ->");
            }

            System.out.println(" sentinel");
        }
    }

    // find MST using prims' algorithm
    int[] MST_Prim(int s)
    {
        int vertex;
        int total_wgt = 0;
        int[] dist, parent, hPos;
        Node node;
        // records current distance of a vertex from the MST
        dist = new int[V + 1];

        // the parent node
        parent = new int[V + 1];

        // current heap position
        hPos = new int[V + 1];

        // initialise hPos and parent to 0
        for (int v = 1; v <= V; v++)
        {
            dist[v] = Integer.MAX_VALUE;
            parent[v] = 0;
            hPos[v] = 0;
        }

        Heap heap = new Heap(V + 1, dist, hPos);
        heap.insert(s);

        dist[s] = 0;

        while (!heap.isEmpty())
        {

            vertex = heap.remove();
            //System.out.print("\nvertex: " + vertex);
            //System.out.print("\nDist[vertex]: " + dist[vertex]);

            //System.out.print("\nSelected edge " + toChar(parent[vertex]) + "--[" + dist[vertex] + "]--" + toChar(vertex) + "\n");

            // total weight
            total_wgt += dist[vertex];
            System.out.println("Total weight: " + total_wgt);

            for (node = adj[vertex]; node != sentinel; node = node.next)
            {
                //System.out.println("If node.data(" + node.data + ") < dist[node.vert](" + dist[node.vert] + ")");
                if (node.data < dist[node.vert])
                {
                    //System.out.println("--dist[node.vert(" + node.vert + ")](" + dist[node.vert] + ") = node.data(" + node.data + ")");
                    dist[node.vert] = node.data;
                    //System.out.println("--parent[node.vert](" + parent[node.vert] + ") = vertex(" + vertex + ")");
                    parent[node.vert] = vertex;

                    //System.out.println("hPos[node.vert]: " + hPos[node.vert]);
                    // check if vertex is empty, if not: insert vertex
                    if (hPos[node.vert] == 0) {
                        //System.out.println("Yes");
                        heap.insert(node.vert);
                    }
                    else {
                        //System.out.println("No");
                        heap.siftUp(hPos[node.vert]);
                    }
                }
            }
        }
        
        System.out.print("\n\nWeight = " + total_wgt + "\n\n");
        return parent;
    }

    public void showMST(int[] mst)
    {
        System.out.print("Minimum Spanning tree parent array:\n");

        for (int v = 1; v <= V; ++v) 
        {
            System.out.println(toChar(v) + " -> " +  toChar(mst[v]));
        }

        System.out.println("");
    }
} 

public class PrimLists {
    public static void main(String[] args) throws IOException
    {
        String fileName;
        int vertex;

        Scanner in = new Scanner(System.in);

        System.out.print("\nEnter the name of a graph file: ");
        fileName = in.nextLine();
        System.out.print("\nFile name is: " + fileName);

        System.out.print("\n\nEnter a starting vertex: ");  
        vertex = in.nextInt();
        System.out.print("\nVertex: " + vertex + "\n");

        in.close();

        Graph g = new Graph(fileName);
       
        g.display();
               
        int[] mst = g.MST_Prim(vertex);

        g.showMST(mst);
    }
}