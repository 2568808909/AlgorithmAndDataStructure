package datastruce.heap;

import datastruce.list.ArrayList;
import datastruce.list.List;
import util.CommonUtils;

import java.util.Iterator;

public class Heap<E extends Comparable<E>> implements List<E> {

    private ArrayList<E> heap = new ArrayList<>();
    private boolean isMax;

    public static final boolean MAX = true;
    public static final boolean MIN = false;

    public Heap() {
        this(MAX);
    }

    public Heap(boolean isMax) {
        this.isMax = isMax;
    }


    @Override
    public void add(E data) {
        int pos = (heap.size() - 1) / 2, cur;
        heap.add(data);
        cur = heap.size() - 1;
        while (cur != 0) {
            if (compare(heap.get(pos), data)) {
                CommonUtils.swap(heap, pos, cur);
            }
            cur = pos;
            pos = (pos - 1) / 2;
        }
    }

    /**
     * 判断是否符合堆的性质，判断是否要交换
     *
     * @param o1 父元素
     * @param o2 子元素
     * @return 若为小根堆父元素小于子元素则返回false，否则返回true表示要交换；若为大根堆，父元素大于子元素返回false,否则返回true
     */
    private boolean compare(E o1, E o2) {
        return isMax ? o1.compareTo(o2) < 0 : o1.compareTo(o2) > 0;
    }

    public E pop() {
        E topElement = heap.get(0);
        E tailElement = heap.remove(heap.size() - 1);
        heap.set(0, tailElement);
        down();
        return topElement;
    }

    private void down() {
        int index = 0;
        int size = heap.size();
        int leftIdx, rightIdx, tempIdx;
        while (true) {
            leftIdx = index * 2 + 1;
            rightIdx = leftIdx + 1;
            if (leftIdx < size && rightIdx < size) {
                tempIdx = compare(heap.get(leftIdx), heap.get(rightIdx)) ? rightIdx : leftIdx;
                if (compare(heap.get(index), heap.get(tempIdx))) {
                    CommonUtils.swap(heap, index, tempIdx);
                    index = tempIdx;
                } else {
                    break;
                }
            } else if (leftIdx < size) {
                if (compare(heap.get(index), heap.get(leftIdx))) {
                    CommonUtils.swap(heap, index, leftIdx);
                }
                break;
            } else {
                break;
            }
        }
    }


    @Override
    public E remove(int index) {
        throw new RuntimeException("You only can remove element from the top of the heap");
    }

    @Override
    public boolean remove(E data) {
        throw new RuntimeException("You only can remove element from the top of the heap");
    }

    @Override
    public void removeAll(E data) {
        throw new RuntimeException("You only can remove element from the top of the heap");
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean contains(E data) {
        return heap.contains(data);
    }

    @Override
    public E get(int index) {
        return heap.get(index);
    }

    @Override
    public void set(int index, E data) {
        heap.set(index, data);
    }

    @Override
    public Iterator<E> iterator() {
        return heap.iterator();
    }
}
