package com.company.leetCode.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    // 顶点数量
    private int vertices;
    // 边数量
    private int edges;
    // 邻接表：每个顶点对应的邻接顶点列表
    private List<List<Integer>> adj;

    /**
     * 构造函数
     * @param vertices 顶点数量
     */
    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = 0;
        this.adj = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
    }

    /**
     * 添加一条无向边
     * @param v 顶点 v
     * @param w 顶点 w
     */
    public void addEdge(int v, int w) {
        // 检查顶点是否合法
        if (v < 0 || v >= vertices || w < 0 || w >= vertices) {
            throw new IllegalArgumentException("顶点编号超出范围");
        }
        adj.get(v).add(w);
        adj.get(w).add(v);
        edges++;
    }

    // 深拷贝图
    public static Graph deepCopy(Graph original) {
        Graph copy = new Graph(original.getVertices());
        for (int v = 0; v < original.getVertices(); v++) {
            for (Integer w : original.adj(v)) {
                if (v<w){
                    copy.addEdge(v, w);
                }
            }
        }

        return copy;
    }

    /**
     * 获取与顶点 v 相邻的所有顶点
     * @param v 顶点 v
     * @return 邻接顶点列表
     */
    public List<Integer> adj(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("顶点编号超出范围");
        }
        return adj.get(v);
    }

    /**
     * 获取顶点数量
     * @return 顶点数量
     */
    public int getVertices() {
        return vertices;
    }

    /**
     * 获取边数量
     * @return 边数量
     */
    public int getEdges() {
        return edges;
    }

    /**
     * 打印图的邻接表
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("顶点数量: ").append(vertices).append(", 边数量: ").append(edges).append("\n");
        for (int i = 0; i < vertices; i++) {
            sb.append(i).append(": ");
            for (int w : adj.get(i)) {
                sb.append(w).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // 示例主函数
    public static void main(String[] args) {
        // 创建一个包含 5 个顶点的图
        Graph graph = new Graph(5);

        // 添加边
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // 打印邻接表
        System.out.println(graph);

        Graph graphCopy = deepCopy(graph);

        System.out.println(graphCopy);
    }
}
