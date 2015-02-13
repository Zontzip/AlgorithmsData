// heapTest.java
// Simple array based implementation of a Heap;
import java.util.*;

class Heap
{
    private int[] a;
    private int N;
    private int hPos;

    private static int hmax = 100;
    
    public Heap()
    {
        a = new int[hmax + 1];
        N = 0;
    }

    public Heap(int _hmax)
    {
        a = new int[_hmax + 1];
        N = 0;
    }

    public void insert(int x)
    {
        a[++N] = x;
        siftUp(N);
    }
  
    public void siftUp(int k)
    {
        int v = a[k];
        a[0] = Integer.MAX_VALUE;

        while( v > a[k/2]) {
            a[k] = a[k/2];
            //hPos[]
            k = k/2;
        }
        a[k] = v;
    }

    /*public void siftDown(int k)
    {
        int v, j;

        a = h[k];

        while( a<= k/2 ) {// while node at pos k has a left child node 
            j = 2k
            if( j < N ∧ h[j] < h[j+1]) ++j
            if( v ≥ h[j] ) break
            h[k] = h[j]; 
            k = j;
            h[k] = v; 
    }*/

    public void display() 
    {
        System.out.println("\nThe tree structure of the heaps is: \n" + a[1]);

        for(int i = 1; i<= N/2; i = i * 2) {
            for(int j = 2*i; j < 4*i && j <= N; ++j) {
                System.out.print( a[j] + "  ");
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