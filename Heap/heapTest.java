/* Heap implementation using an array
   
   Name: Alex Kiernan

   Date: 2015-02-13
*/

import java.util.*;

class Heap
{
    private int[] heap;     // heap is our heap array
    private int N;          // N is our global position in the heap array
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
        heap[++N] = x; // N is the array position
        System.out.println("N equals: " + N);
        siftUp(N);
    }
  
    public void siftUp(int k)
    {
    	// v is the value we want to put in position. At this time it is a specified position (k) in the array.
        int v = heap[k];
        heap[0] = Integer.MAX_VALUE;    // sets first element of heap to some unwritable, super large number

        // remember, this is a binary tree of numbers
        while(v > heap[k/2]) {          // while the our number is less than the value stored in heap index/2
            heap[k] = heap[k/2];        // replace what is in our current position with the smaller number
            //hPos[heap[k]] = k;
            k = k/2;					// update the position of the array index
            System.out.println("While loop k: " + k);
            System.out.println("Value of heap[k]: " + heap[k]);
        }

        heap[k] = v; // now we store our number in the correct position
        //hPos[v] = k;
    }

    public void siftDown(int k)
    {
        int v, j;

        heap = h[k];

        while(heap <= k/2 ) {// while node at pos k has a left child node 
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