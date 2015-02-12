// Heap.cs
// Simple array based implementation of a Heap;

using System;

class Heap
{
    private int[] a;
    private int N;

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
        // code this method yourself.
    }

    
    public void display() 
    {
        Console.WriteLine("{0}", a[1]);

        for(int i = 1; i <= N/2; i = i * 2) {
            for(int j = 2*i; j < 4*i && j <= N; ++j)
                Console.Write("{0}  ", a[j]);
            Console.Write("\n");
        }
    }

}

class HeapTest
{
    static void Main(string[] args)
    {
        Heap h = new Heap();

        Random r = new Random();

        int i, x;
        for (i = 0; i < 10; ++i)
        {
            x = r.Next(99);
            Console.WriteLine("\nInserting {0} ", x);
            h.insert(x);
            h.display();
        }

        //x = h.remove();
        //Console.WriteLine("\nRemoving {0} ", x);
       // h.display();

        Console.ReadKey();
       
    }
}
