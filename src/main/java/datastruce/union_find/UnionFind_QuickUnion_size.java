package datastruce.union_find;

/**
 * size的优化
 *
 * 对于之前的QuickUnion有一个问题，在极端环境下，我们按顺序进行union操作，就可能形成一条比较长的链，
 * 这样，在执行find操作时就比较慢，所以在这里实现了一种优化方案：维护一个sz数组，数组存储的是，
 * 某个元素作为根元素时，其树中的节点个数(当然也可以维护树高)。每次合并时，将sz比较大的合并到sz比较小的元素中
 */
public class UnionFind_QuickUnion_size implements UF {
    private int[] parent;
    private int[] sz;

    private UnionFind_QuickUnion_size(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
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
        //此处维护了一个sz数组，目的是让树的size比较大的元素指向size比较小的元素
        if (sz[pID] < sz[qID]) {
            parent[pID] = qID;
            sz[qID] += sz[pID];
        } else {
            parent[qID] = pID;
            sz[pID] += sz[qID];
        }
    }

    /**
     * 查找操作，返回树的根
     *
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
