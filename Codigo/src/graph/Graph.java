package src.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Graph {
    public int[][] matrix;
    public int n_vertices;
    public int k_centers;
    public int maxDistance = 0;

    public Graph(int n_vertices, int k_centers) {
        this.matrix = new int[n_vertices][n_vertices];
        this.n_vertices = n_vertices;
        this.k_centers = k_centers;
    }

    public void correctInfiniteWays() {
        for (int i = 0; i < n_vertices; i++) {
            for (int j = 0; j < n_vertices; j++) {
                if (i != j && this.matrix[i][j] == 0) {
                    this.matrix[i][j] = 9999;
                }
            }
        }
    }

    @Override
    public String toString() {
        String graphStrMatrix = "";
        for (int i = 0; i < n_vertices; i++) {
            graphStrMatrix += i + " ";
            for (int j = 0; j < n_vertices; j++) {
                graphStrMatrix += String.format("| %-6d ", this.matrix[i][j]);
            }
            graphStrMatrix += "\n";
        }
        return graphStrMatrix;
    }

    public void floydWharshall() {
        for (int k = 0; k < n_vertices; k++) {
            for (int i = 0; i < n_vertices; i++) {
                for (int j = 0; j < n_vertices; j++) {
                    if (this.matrix[i][j] > (this.matrix[i][k] + this.matrix[k][j])) {
                        this.matrix[i][j] = this.matrix[i][k] + this.matrix[k][j];
                    }
                }
            }
        }
    }

    static int maxIndex(int[] dist, int n) {
        int mi = 0;
        for (int i = 0; i < n; i++) {
            if (dist[i] > dist[mi])
                mi = i;
        }
        return mi;
    }

    public void selectCenters() {
        selectCenters(n_vertices, this.matrix, k_centers);
    }

    public void selectCenters(int n, int weights[][],
            int k) {
        int[] dist = new int[n];
        ArrayList<Integer> centers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        int max = 0;

        for (int i = 0; i < k; i++) {
            centers.add(max);
            for (int j = 0; j < n; j++) {
                dist[j] = Math.min(dist[j],
                        weights[max][j]);

            }

            max = maxIndex(dist, n);
        }
        

        
        maxDistance = dist[max];
        
        // Exibição dos centros

        /*for (int i = 0; i < centers.size(); i++) {
            System.out.print(centers.get(i) + " ");
        }
        System.out.print("\n");*/
    }

    public void solve() {
        solve(this.matrix, 30);
    }

    public void solve(int[][] graph, int k) {
        boolean[] added = new boolean[graph.length];
        List<Integer> nodes = new ArrayList<>();
        added[0] = true;
        nodes.add(0);
        while (nodes.size() != k) {
            int maxDisFromNode = Integer.MIN_VALUE;
            int maxDisNodeToBeAdded = 0;
            for (Integer node : nodes) {
                int minDisFromNode = Integer.MAX_VALUE;
                int minDisNodeToBeAdded = 0;
                for (int i = 0; i < graph.length; i++) {
                    if (!added[i] && graph[node][i] != 0 && graph[node][i] < minDisFromNode) {
                        minDisFromNode = graph[node][i];
                        minDisNodeToBeAdded = i;
                    }
                }
                if (minDisFromNode > maxDisFromNode) {
                    maxDisFromNode = minDisFromNode;
                    maxDisNodeToBeAdded = minDisNodeToBeAdded;
                }
            }
            added[maxDisNodeToBeAdded] = true;
            nodes.add(maxDisNodeToBeAdded);
        }

        int raioMax = 0;
        int raioMin = Integer.MAX_VALUE;
        for (Integer n : nodes) {
            for (int j = 0; j < n_vertices; j++) {
                if (this.matrix[n][j] > raioMax) {
                    raioMax = this.matrix[n][j];
                }
                if (this.matrix[n][j] < raioMin && this.matrix[n][j] != 0) {
                    raioMin = this.matrix[n][j];
                }
            }
        }
        for (Integer n : nodes)
            System.out.print(n + " ");

        System.out.println("RaioMax: " + raioMax + " - RaioMin: " + raioMin + "\n\n");
    }

}
