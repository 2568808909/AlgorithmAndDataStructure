package datastruce.list;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private static final int RESIZE_FACTOR = 2;

    private Object[] elements;

    private int capacity;

    private int size;

    public ArrayList(int capacity) {
        this.elements = new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(E data) {
        if (size == capacity) {
            resize();
        }
        elements[size++] = data;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E removeValue = (E) elements[index];
        removeFromIndex(index);
        return removeValue;
    }

    @Override
    public boolean remove(E data) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(data)) {
                removeFromIndex(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeAll(E data) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(data)) {
                removeFromIndex(i);
            }
        }
    }

    private void removeFromIndex(int index) {
        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }
        size--;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E data) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(data)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) elements[index];
    }

    @Override
    public void set(int index, E data) {
        elements[index] = data;
    }

    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    private void resize() {
        int oldCapacity = this.capacity;
        this.capacity = oldCapacity + (oldCapacity >> 1);
        elements = Arrays.copyOf(elements, capacity);
    }

    class Itr implements Iterator<E> {

        private int size;
        private Object[] elements;
        private int pos;

        Itr() {
            this.elements = ArrayList.this.elements;
            this.size = ArrayList.this.size;
            this.pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos != size;
        }

        @Override
        public E next() {
            return (E) elements[pos++];
        }
    }

    public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (; ; ) {
            E e = it.next();
            sb.append(e == this ? "(this Collection)" : e);
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }
}
