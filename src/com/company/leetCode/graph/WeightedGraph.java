package com.company.leetCode.graph;

import java.util.ArrayList;
import java.util.List;

public class WeightedGraph {

    private int vertices;       // 顶点数量
    private int edges;          // 边数量
    private List<List<Edge>> adj;

    // 定义边结构（带权）
    class Edge {
        int to;     // 目标顶点
        double weight;  // 权重

        public Edge(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }// 邻接表

    public WeightedGraph(int vertices) {
        this.vertices = vertices;
        this.edges = 0;
        adj = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // 添加一条带权边
    public void addEdge(int v, int w, double weight) {
        if (v < 0 || v >= vertices || w < 0 || w >= vertices) {
            throw new IllegalArgumentException("顶点编号超出范围");
        }
        adj.get(v).add(new Edge(w, weight));
        adj.get(w).add(new Edge(v, weight)); // 无向图
        edges++;
    }

    public List<Edge> adj(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("顶点编号超出范围");
        }
        return adj.get(v);
    }

    public int getVertices() {
        return vertices;
    }

    public int getEdges() {
        return edges;
    }

    // 深拷贝图
    public static WeightedGraph deepCopy(WeightedGraph original) {
        WeightedGraph copy = new WeightedGraph(original.getVertices());

        for (int v = 0; v < original.getVertices(); v++) {
            for (Edge e : original.adj(v)) {
                // 防止重复添加边（无向图每条边添加两次）
                if (v < e.to) {
                    copy.addEdge(v, e.to, e.weight);
                }
            }
        }

        return copy;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("顶点数: ").append(vertices).append(", 边数: ").append(edges).append("\n");
        for (int i = 0; i < vertices; i++) {
            sb.append(i).append(": ");
            for (Edge e : adj.get(i)) {
                sb.append("(").append(e.to).append(", ").append(e.weight).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        WeightedGraph original = new WeightedGraph(3);
        original.addEdge(0, 1, 1.0);
        original.addEdge(1, 2, 2.0);

        WeightedGraph copy = deepCopy(original);
        System.out.println("原图: " + original);
        System.out.println("拷贝图: " + copy);
    }
}
