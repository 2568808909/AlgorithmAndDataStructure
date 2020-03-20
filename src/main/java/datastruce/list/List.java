package datastruce.list;

public interface List<E> extends Iterable<E> {

    void add(E data);

    E remove(int index);

    boolean remove(E data);

    void removeAll(E data);

    boolean isEmpty();

    int size();

    boolean contains(E data);

    E get(int index);

    void set(int index, E data);
}
