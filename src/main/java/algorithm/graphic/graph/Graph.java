package algorithm.graphic.graph;

/**
 * 图的分类：
 * 邻接矩阵(适合稀疏图)
 * 邻接表(适合稠密图)
 */
public interface Graph {

    /**
     * 增加一条从v到w的边
     * @param v     点v
     * @param w     点w
     */
    void addEdge(int v,int w);

    /**
     * 判断从v到w有无边
     * @param v
     * @param w
     * @return
     */
    boolean hasEdge(int v,int w);
}
