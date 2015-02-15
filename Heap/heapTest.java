// heapTest.java
// Simple array based implementation of a Heap;
import java.util.*;

class Heap
{
    private int[] heap;     // heap is our heap array
    private int N;          // N is our position in the array
    private int[] hPos;

    private static int hmax = 100;
    
    public Heap()
    {
        heap = new int[hmax + 1];
        N = 0;
    }

    public Heap(int _hmax)
    {
        heap = new int[_hmax + 1];
        N = 0;
    }

    public void insert(int x)
    {
        heap[++N] = x;
        siftUp(N);
    }
  
    public void siftUp(int k)
    {
        int v = heap[k];   // v is the vertex, so the current position when sifting through shit
        heap[0] = Integer.MAX_VALUE;    // sets first element to some super large number

        while(v > heap[k/2]) {          // while the current vertex is less than our heap/2
            heap[k] = heap[k/2];        // replace 
            //hPos[heap[k]] = k;
            k = k/2;
        }

        heap[k] = v;
        //hPos[v] = k;

        //System.out.println(hPos[v]);
    }

    /*public void siftDown(int k)
    {
        int v, j;

        heap = h[k];

        while( heap<= k/2 ) {// while node at pos k has a left child node 
            j = 2k
            if( j < N ∧ h[j] < h[j+1]) ++j
            if( v ≥ h[j] ) break
            h[k] = h[j]; 
            k = j;
            h[k] = v; 
    }*/

    public void display() 
    {
        System.out.println("\nThe tree structure of the heaps is: \n" + heap[1]);

        for(int i = 1; i <= N/2; i = i * 2) {
            for(int j = 2 * i; j < 4 * i && j <= N; ++j) {
                System.out.print( heap[j] + "  ");
                System.out.println();
            }
        }
    }
}

class heapTest
{
    public static void main(String[] args)
    {
        Heap h = new Heap();

        Random r = new Random();

        int i, x;
        for (i = 0; i < 10; ++i)
        {
            x = r.nextInt(99);
            System.out.println("\nInserting " + x);
            h.insert(x);
            h.display();
        }

        //x = h.remove();
        //System.out.println("\nRemoving {0} ", x);
        // h.display();
    }
}