package algorithm.graphic.graph;

import lombok.Data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 稀疏图-邻接表
 */
@Data
public class SparseGraph implements Graph {
    private int edgeNum; //边数
    private int vertexNum; //节点数
    private boolean directed; //是否有向

    private List<List<Edge>> graph;

    class Edge {
        int v;
        int weight;

        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return v == edge.v;
        }

        @Override
        public int hashCode() {
            return Objects.hash(v);
        }
    }

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
        addEdge(v, w, 0);
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if (indexOutOfBound(v) || indexOutOfBound(w)) {
            throw new IndexOutOfBoundsException("index out of bound. bound:" + vertexNum);
        }
        List<Edge> list = graph.get(v);
        for (Edge point : list) {
            if (point.v == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeEdge(int v, int w) {
        graph.get(v).remove(new Edge(w, 0));
        if (!directed) {
            graph.get(w).remove(new Edge(v, 0));
        }
    }

    private boolean indexOutOfBound(int index) {
        return index < 0 || index > vertexNum;
    }

    @Override
    public void addEdge(int v, int w, int weight) {
        if (indexOutOfBound(v) || indexOutOfBound(w)) {
            throw new IndexOutOfBoundsException("index out of bound. bound:" + vertexNum);
        }
        graph.get(v).add(new Edge(w, weight));
        if (!directed) {
            graph.get(w).add(new Edge(v, weight));
        }
        edgeNum++;
    }
}
