package datastruce.union_find;

/**
 * 并查集接口，主要支持两个元素union和isConnected
 */
public interface UF {

    /**
     * 查询两个元素是否在同一集合内
     * @param p 下标1
     * @param q 下标2
     * @return 若两个元素连接则返回true，否则返回false

     */
    boolean isConnected(int p,int q);

    /**
     * 连接下标为p和q的元素
     * @param p 下标1
     * @param q 下标2
     */
    void unionElements(int p,int q);

    /**
     * 返回并查集的大小
     * @return 并查集大小
     */
    int getSize();
}
