package algorithm.graphic.graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 稀疏图-邻接表
 */
@Data
public class SparseGraph implements Graph {
    private int edgeNum; //边数
    private int vertexNum; //节点数
    private boolean directed; //是否有向

    private List<List<Integer>> graph;

    public SparseGraph(int vertexNum, boolean directed) {
        this.vertexNum = vertexNum;
        this.directed = directed;
        this.edgeNum = 0;
        graph = new ArrayList<>();
        for (int i = 0; i < vertexNum; i++) {
            graph.add(new LinkedList<>());
        }
    }

    public SparseGraph(int vertexNum) {
        this(vertexNum, false);
    }

    @Override
    public void addEdge(int v, int w) {
        if (indexOutOfBound(v) || indexOutOfBound(w)) {
            throw new IndexOutOfBoundsException("index out of bound. bound:" + vertexNum);
        }
        graph.get(v).add(w);
        if (!directed) {
            graph.get(w).add(v);
        }
        edgeNum++;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if (indexOutOfBound(v) || indexOutOfBound(w)) {
            throw new IndexOutOfBoundsException("index out of bound. bound:" + vertexNum);
        }
        List<Integer> list = graph.get(v);
        for (Integer point : list) {
            if (point.equals(w)) {
                return true;
            }
        }
        return false;
    }

    private boolean indexOutOfBound(int index) {
        return index < 0 || index > vertexNum;
    }
}
