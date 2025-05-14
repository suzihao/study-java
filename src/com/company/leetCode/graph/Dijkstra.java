package com.company.leetCode.graph;

import java.util.*;

public class Dijkstra {
    // 记录从起点到各顶点的最短距离
    private double[] distTo;
    // 记录路径（前驱节点）
    private int[] edgeTo;
    // 是否已访问
    private boolean[] visited;

    public Dijkstra(WeightedGraph graph, int start) {
        distTo = new double[graph.getVertices()];
        edgeTo = new int[graph.getVertices()];
        visited = new boolean[graph.getVertices()];
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[start] = 0;

        // 使用优先队列（最小堆）
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(node -> node.dist));
        pq.offer(new Node(start, 0.0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int v = node.vertex;

            if (visited[v]) continue;
            visited[v] = true;

            for (WeightedGraph.Edge e : graph.adj(v)) {
                if (distTo[e.to] > distTo[v] + e.weight) {
                    distTo[e.to] = distTo[v] + e.weight;
                    edgeTo[e.to] = v;
                    pq.offer(new Node(e.to, distTo[e.to]));
                }
            }
        }
    }

    // 返回从起点到目标顶点的最短距离
    public double getDistance(int target) {
        return distTo[target];
    }

    // 返回从起点到目标顶点的最短路径
    public List<Integer> getPath(int target) {
        List<Integer> path = new ArrayList<>();
        int current = target;
        while (current != -1) {
            path.add(current);
            current = edgeTo[current];
        }
        Collections.reverse(path);
        return path;
    }

    // 辅助类：节点 + 距离
    private static class Node {
        int vertex;
        double dist;

        public Node(int vertex, double dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 3);

        Dijkstra dijkstra = new Dijkstra(graph, 0);
        System.out.println("从 0 到 4 的最短距离: " + dijkstra.getDistance(4));
        System.out.println("最短路径: " + dijkstra.getPath(4));
    }
}
