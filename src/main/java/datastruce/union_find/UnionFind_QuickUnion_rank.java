package datastruce.union_find;

/**
 * rank优化
 *
 * 维护每棵树的高度，合并时，将高度较小的结合指向高度较高的
 */
public class UnionFind_QuickUnion_rank implements UF {
    private int[] parent;
    private int[] rank;

    private UnionFind_QuickUnion_rank(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
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
        //rank维护的是各自树的高度
        if (rank[pID] < rank[qID]) { //由于rank[pID] < rank[qID]将pID所在的树连接到qID上并不会增加qID所在的树的高度，所以不需要维护
            parent[pID] = qID;
        } else if (rank[qID] < rank[p]) {
            parent[qID] = pID;
        } else {
            parent[pID] = qID;
            rank[qID]++;
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
