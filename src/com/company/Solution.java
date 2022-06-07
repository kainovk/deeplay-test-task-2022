package com.company;

import com.company.util.DPQ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.company.util.DPQ.Node;

public class Solution {

    private static final int ROW_COUNT = 4;
    private static final int COL_COUNT = 4;

    private static final Map<String, Map<Character, Integer>> costs = new HashMap<>();

    static {
        costs.put(Race.HUMAN.name(), Map.ofEntries(
                Map.entry('S', 5),
                Map.entry('W', 2),
                Map.entry('T', 3),
                Map.entry('P', 1))
        );
        costs.put(Race.SWAMPER.name(), Map.ofEntries(
                Map.entry('S', 2),
                Map.entry('W', 2),
                Map.entry('T', 5),
                Map.entry('P', 2))
        );
        costs.put(Race.WOODMAN.name(), Map.ofEntries(
                Map.entry('S', 3),
                Map.entry('W', 3),
                Map.entry('T', 2),
                Map.entry('P', 2))
        );
    }

    public static int getResult(String coords, String race) {
        if (coords.length() != ROW_COUNT * COL_COUNT) {
            return -1;
        }

        Map<Character, Integer> requiredCosts = costs.get(race);

        int[][] paths = new int[ROW_COUNT][COL_COUNT];
        for (int i = 0; i < ROW_COUNT; i++) {
            for (int j = 0; j < COL_COUNT; j++) {
                int index = i * COL_COUNT + j;
                paths[i][j] = requiredCosts.get(coords.charAt(index));
            }
        }

        return getShortestPath(paths);
    }

    private static int getShortestPath(int[][] matrix) {
        int V = ROW_COUNT * COL_COUNT;
        List<List<Node>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

        int curIndex, bottomPathCost, rightPathCost;
        for (int i = 0; i < ROW_COUNT - 1; i++) {
            for (int j = 0; j < COL_COUNT - 1; j++) {
                rightPathCost = matrix[i][j + 1];
                bottomPathCost = matrix[i + 1][j];
                curIndex = COL_COUNT * i + j;
                adj.get(curIndex).add(new Node(curIndex + 1, rightPathCost));
                adj.get(curIndex).add(new Node(curIndex + COL_COUNT, bottomPathCost));
            }
            bottomPathCost = matrix[i + 1][COL_COUNT - 1];
            curIndex = COL_COUNT * (i + 1) - 1;
            adj.get(curIndex).add(new Node(curIndex + COL_COUNT, bottomPathCost));
        }
        for (int j = 0; j < COL_COUNT - 1; j++) {
            rightPathCost = matrix[ROW_COUNT - 1][j + 1];
            curIndex = COL_COUNT * (ROW_COUNT - 1) + j;
            adj.get(curIndex).add(new Node(curIndex + 1, rightPathCost));
        }

        return dijkstra(adj);
    }

    private static int dijkstra(List<List<Node>> adj) {
        int V = ROW_COUNT * COL_COUNT;
        DPQ dpq = new DPQ(V);
        dpq.dijkstra(adj, 0);

        int[] dist = dpq.getDist();
        int length = dist.length;
        if (length > 0) {
            return dist[length - 1];
        }
        return -1;
    }
}
