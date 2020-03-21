package algorithm.graphic.graph;

import lombok.Data;

/**
 * 稠密图--邻接矩阵
 */
@Data
public class DenseGraph implements Graph {

    private int edgeNum; //边数
    private int vertexNum; //节点数
    private boolean directed; //是否有向

    private int[][] graph;

    public DenseGraph(int vertexNum, boolean directed) {
        this.vertexNum = vertexNum;
        this.directed = directed;
        this.edgeNum = 0;
        graph = new int[vertexNum][vertexNum];
    }

    public DenseGraph(int vertexNum) {
        this(vertexNum, false);
    }

    @Override
    public void addEdge(int v, int w) {
        addEdge(v, w, 1);
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if (indexOutOfBound(v) || indexOutOfBound(w)) {
            throw new IndexOutOfBoundsException("index out of bound. bound:" + vertexNum);
        }
        return graph[v][w] == 0;
    }

    @Override
    public void removeEdge(int v, int w) {
        addEdge(v, w, 0);
    }

    private boolean indexOutOfBound(int index) {
        return index < 0 || index > vertexNum;
    }

    @Override
    public void addEdge(int v, int w, int weight) {
        if (indexOutOfBound(v) || indexOutOfBound(w)) {
            throw new IndexOutOfBoundsException("index out of bound. bound:" + vertexNum);
        }
        if (hasEdge(v, w))
            return;
        graph[v][w] = weight;
        if (!directed) {
            graph[w][v] = weight;
        }
        edgeNum++;
    }
}
