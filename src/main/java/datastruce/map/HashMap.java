package datastruce.map;

import java.util.Objects;

/**
 * MyHashMap
 *
 * @param <K>
 * @param <V>
 */
public class HashMap<K, V> implements Map<K, V> {

    class Node<K, V> {
        int hash;
        K key;
        V value;
        Node next;

        Node(int hash, K key, V value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    private Node<K, V>[] table;

    private int threshold;

    private int capacity;

    private int size;

    private static final double FACTOR = 0.75;

    private int hashSeed = 0;

    public HashMap(int initialCapacity) {
        this.threshold = initialCapacity;
        this.size = 0;
    }

    public HashMap() {
        this(16);
    }

    private void initTable() {
        int capacity = computeCapacity(computeCapacity(threshold));
        table = new Node[capacity];
        this.capacity = capacity;
        this.threshold = (int) (capacity * FACTOR);
    }

    private int computeCapacity(int initCapacity) {
        return Integer.highestOneBit((initCapacity - 1) << 1);
    }

    private int hash(K key) {
        int h = hashSeed;
        h ^= key.hashCode();
        return h ^ (h >> 16) ^ (h >> 24);
    }

    private int indexFor(int hash) {
        return hash & (this.capacity - 1);
    }

    @Override
    public V put(K key, V value) {
        if (table == null) {
            initTable();
        }
        if (key == null) {
            table[0] = new Node<>(0, key, value, table[0]);
        } else {
            int hash = hash(key);
            int index = indexFor(hash);
            Node<K, V> p = table[index];
            if (p == null) {
                table[index] = new Node<>(hash, key, value, null);
            } else {
                while (true) {
                    if (p.key.equals(key)) {
                        V preValue = p.value;
                        p.value = value;
                        return preValue;
                    }
                    if (p.next == null) {
                        p.next = new Node(hash, key, value, null);
                        break;
                    }
                    p = p.next;
                }
            }
        }
        size++;
        if (size >= threshold) {
            resize();
        }
        return null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return table[0] != null && table[0].key == null ? table[0].value : null;
        }
        int hash = hash(key);
        int index = indexFor(hash);
        Node<K, V> p = table[index];
        while (p != null) {
            if (p.key.equals(key)) {
                return p.value;
            }
            p = p.next;
        }
        return null;
    }

    /**
     * 扩容
     */
    private void resize() {
        Node<K, V>[] oldTable = this.table;
        this.capacity = capacity << 1;
        this.threshold = threshold << 1;
        Node<K, V>[] newTable = new Node[this.capacity];
        int count = 0;
        for (Node<K, V> p : oldTable) {
            if (p == null) continue;
            count++;
            while (p != null) {
                int index = indexFor(p.hash);
                addIntoNewTable(newTable, index, p);
                p = p.next;
            }
        }
        this.table = newTable;
    }

    private void addIntoNewTable(Node<K, V>[] newTable, int index, Node<K, V> node) {
        Node<K, V> p = newTable[index];
        if (p == null) {
            newTable[index] = node;
        } else {
            while (p.next != null) {
                p = p.next;
            }
            if (p.key.equals(node.key)) {
                return;
            }
            p.next = node;
        }
        node.next = null;
    }

}
