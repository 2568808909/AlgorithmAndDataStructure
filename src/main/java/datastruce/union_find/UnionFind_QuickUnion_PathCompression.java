package datastruce.union_find;

/**
 * 路径压缩
 * <p>
 * 主要是在find操作时进行优化(parent[index]=parent[parent[index]])，在find操作的同时逐渐降低树的高度
 * 对于rank数组，其实可以不需要维护，rank之所以不被称作高度也是因为如此，更准确来说rank数组只是一个参考值，
 * 即使不太准确，后来也有路径压缩进行优化。
 */
public class UnionFind_QuickUnion_PathCompression implements UF {
    private int[] parent;
    private int[] rank;

    private UnionFind_QuickUnion_PathCompression(int size) {
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
            parent[index]=parent[parent[index]];  //路径压缩
            index = parent[index];
        }
        return index;
    }

//    /**
//     * 通过递归实现路径压缩，与上面的方式比较，这个可以在查找过程中，将沿途的节点都指向根节点，
//     * 而上面的实现方式要将所有的节点都直接指向根节点可能要多几次操作。但是，由于递归其实消耗
//     * 比循环大，所以这种实现方式可能会比上面的稍微慢一点。
//     *
//     * @param index
//     * @return
//     */
//    private int find(int index) {
//        if (index < 0 || index > parent.length) {
//            throw new IndexOutOfBoundsException("index out of bound");
//        }
//        if (index != parent[index]) {
//            parent[index] = find(parent[index]);
//        }
//        return index;
//    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
