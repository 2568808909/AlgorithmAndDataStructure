package datastruce.union_find;

/**
 * Quick Find
 * Union: O(n)
 * isConnected: O(1)
 */
public class UnionFind_QuickFind implements UF {

    private int[] id;

    public UnionFind_QuickFind(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 连接操作，通过遍历用qID替换所有原本值为pID的元素
     * @param p 下标1
     * @param q 下标2
     */
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (qID == pID) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
    }

    private int find(int index) {
        if (index < 0 || index > id.length) {
            throw new IndexOutOfBoundsException("index out of bound");
        }
        return id[index];
    }

    @Override
    public int getSize() {
        return id.length;
    }
}
