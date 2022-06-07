package com.company.util;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DPQ {

    private final int V;
    private final int[] dist;
    private final Set<Integer> settled;
    private final PriorityQueue<Node> pq;
    private List<List<Node>> adj;

    public DPQ(int V) {
        this.V = V;
        dist = new int[V];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(V, new Node());
    }

    public int[] getDist() {
        return dist;
    }

    public void dijkstra(List<List<Node>> adj, int src) {
        this.adj = adj;
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        pq.add(new Node(src, 0));
        dist[src] = 0;

        while (settled.size() != V) {
            if (pq.isEmpty()) {
                return;
            }
            int u = pq.remove().node;
            settled.add(u);

            eNeighbours(u);
        }
    }

    private void eNeighbours(int u) {
        int edgeDistance, newDistance;

        for (int i = 0; i < adj.get(u).size(); i++) {
            Node v = adj.get(u).get(i);

            if (!settled.contains(v.node)) {
                edgeDistance = v.cost;
                newDistance = dist[u] + edgeDistance;

                if (newDistance < dist[v.node]) {
                    dist[v.node] = newDistance;
                }
                pq.add(new Node(v.node, dist[v.node]));
            }
        }
    }

    public static class Node implements Comparator<Node> {
        public int node;
        public int cost;

        public Node() {
        }

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            return Integer.compare(node1.cost, node2.cost);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "node=" + node +
                    ", cost=" + cost +
                    '}';
        }
    }
}
