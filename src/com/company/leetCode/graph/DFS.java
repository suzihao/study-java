package com.company.leetCode.graph;

import java.util.ArrayList;
import java.util.List;

public class DFS {

    // 记录访问状态
    private boolean[] visited;
    private List<Integer> traversalOrder;

    public DFS(WeightedGraph graph, int start) {
        visited = new boolean[graph.getVertices()];
        traversalOrder = new ArrayList<>();
        dfs(graph, start);
    }

    private void dfs(WeightedGraph graph, int v) {
        visited[v] = true;
        traversalOrder.add(v);

        for (WeightedGraph.Edge e : graph.adj(v)) {
            if (!visited[e.to]) {
                dfs(graph, e.to);
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

        DFS dfs = new DFS(graph, 0);
        System.out.println("DFS 遍历顺序: " + dfs.getTraversalOrder());
    }
}
