package datastruce.union_find;

/**
 * Quick Union
 * union: O(h) h为所属集合的树高度,一般来说h<n
 * find: O(h)
 */
public class UnionFind_QuickUnion implements UF {

    private int[] parent;

    private UnionFind_QuickUnion(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) {
            return;
        }
        parent[pID] = qID;
    }

    /**
     * 查找操作，返回树的根
     * @param index 下标
     * @return 返回下标为index的元素的根节点
     */
    private int find(int index) {
        if (index < 0 || index > parent.length) {
            throw new IndexOutOfBoundsException("index out of bound");
        }
        while (index != parent[index]) {
            index = parent[index];
        }
        return index;
    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
