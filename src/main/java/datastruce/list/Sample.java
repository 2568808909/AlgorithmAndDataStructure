package datastruce.list;

import datastruce.heap.Heap;
import org.junit.Test;

import java.util.Random;

public class Sample {

    @Test
    public void testArrayList(){
        List<Integer> list=new ArrayList<>();
        for(int i=1;i<=100;i++){
            list.add(i);
        }
        list.add(50);
        System.out.println(list.contains(50));
        list.removeAll(50);
        System.out.println(list.contains(50));
        System.out.println(list);
    }

    @Test
    public void testHeap(){
        Heap<Integer> heap=new Heap<>();
        Random random=new Random();
        for(int i=0;i<15;i++){
            heap.add(random.nextInt(100));
        }
        for(int i=0;i<15;i++){
            System.out.println(heap.pop());
        }
    }
}
