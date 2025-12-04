package Algorithms.Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {
    static int[] bellmanFord(ArrayList<Edge> edges,
                             int vertexCount, int source) throws Exception {
        int[] shortestPath = new int[vertexCount];
        Arrays.fill(shortestPath, Integer.MAX_VALUE);
        shortestPath[source] = 0;

        // we iterate n-1 times to relax the edges, as even in the worst case all the edges will be relaxed after n-1
        // for n nodes, we will have n-1 edges for a shortest path in worst case
        for (int i = 0; i < vertexCount - 1; i++) {
            for (Edge edge : edges) {
                int u = edge.u;
                int v = edge.v;
                int w = edge.w;

                if (shortestPath[u] != Integer.MAX_VALUE && shortestPath[v]
                        > shortestPath[u] + w) {
                    shortestPath[v] = shortestPath[u] + w;
                }
            }
        }

        // N iteration to detect negative cycle
        // even after n-1 iterations, we are finding out a shortest. There exists a negative cycle
        for (Edge edge : edges) {
            int u = edge.u;
            int v = edge.v;
            int w = edge.w;

            if (shortestPath[u] != Integer.MAX_VALUE && shortestPath[v]
                    > shortestPath[u] + w) {
                System.out.println("Negative Cycle Detected");
                throw new Exception();
            }
        }

        return shortestPath;
    }

    public static void main(String[] args) throws Exception {
        int vertexCount = 6;
        int source = 0;
        ArrayList<Edge> edges = new ArrayList<>() {
            {
                add(new Edge(0, 1, 5));
                add(new Edge(0, 2, 10));
                add(new Edge(0, 5, 20));
                add(new Edge(1, 3, 2));
                add(new Edge(2, 3, -8));
                add(new Edge(3, 4, 2));
                add(new Edge(4, 5, 2));
                add(new Edge(5, 1, 1));
            }
        };
        int[] dist = bellmanFord(edges, vertexCount, source);
        for (int i = 0; i < vertexCount; i++) {
            System.out.print(dist[i] + " ");
        }
    }
}

class Edge {
    int u, v, w;

    Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }
}
