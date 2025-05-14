package com.company.leetCode.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS {
    private boolean[] visited;
    private List<Integer> traversalOrder;

    public BFS(WeightedGraph graph, int start) {
        visited = new boolean[graph.getVertices()];
        traversalOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            traversalOrder.add(v);

            for (WeightedGraph.Edge e : graph.adj(v)) {
                if (!visited[e.to]) {
                    visited[e.to] = true;
                    queue.offer(e.to);
                }
            }
        }
    }

    public List<Integer> getTraversalOrder() {
        return traversalOrder;
    }

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(5);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 3, 3);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 5);

        BFS bfs = new BFS(graph, 0);
        System.out.println("BFS 遍历顺序: " + bfs.getTraversalOrder());
    }
}
