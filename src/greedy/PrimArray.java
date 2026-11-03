import java.util.*;

public class PrimArray {

    static final int INF = Integer.MAX_VALUE;

    public static void prim(int[][] graph, int start, String[] names) {
        int V = graph.length;
        int[] key = new int[V];          // cheapest edge connecting v to the tree
        int[] parent = new int[V];       // the tree vertex at the other end of that edge
        boolean[] inMST = new boolean[V];

        Arrays.fill(key, INF);
        Arrays.fill(parent, -1);
        key[start] = 0;

        int total = 0;
        for (int count = 0; count < V; count++) {
            // Pick the vertex outside the tree with the smallest key.
            int u = -1;
            for (int v = 0; v < V; v++)
                if (!inMST[v] && (u == -1 || key[v] < key[u]))
                    u = v;

            if (key[u] == INF) break;    // the rest is unreachable: graph is disconnected
            inMST[u] = true;

            if (parent[u] == -1)
                System.out.printf("add %s (start)%n", names[u]);
            else {
                System.out.printf("add %s via %s-%s (%d)%n",
                        names[u], names[parent[u]], names[u], key[u]);
                total += key[u];
            }

            // Update keys of u's neighbours that are still outside the tree.
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !inMST[v]
                        && graph[u][v] < key[v]) {   // Dijkstra would test dist[u] + w here
                    key[v] = graph[u][v];
                    parent[v] = u;
                }
            }
            System.out.println("    key = " + show(key, inMST, names));
        }
        System.out.println("Total weight: " + total);
    }

    static String show(int[] key, boolean[] in, String[] names) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < key.length; i++) {
            if (in[i]) continue;
            sb.append(names[i]).append(":").append(key[i] == INF ? "INF" : String.valueOf(key[i])).append(" ");
        }
        return sb.length() == 0 ? "(all vertices in tree)" : sb.toString().trim();
    }

    public static void main(String[] args) {
        String[] names = {"A", "B", "C", "D", "E", "F"};
        //               A  B  C  D  E  F
        int[][] graph = {
                        {0, 5, 0, 4, 0, 0},   // A
                        {5, 0, 6, 0, 3, 0},   // B
                        {0, 6, 0, 0, 0, 1},   // C
                        {4, 0, 0, 0, 2, 0},   // D
                        {0, 3, 0, 2, 0, 7},   // E
                        {0, 0, 1, 0, 7, 0},   // F
        };
        prim(graph, 0, names);   // start = A
    }
}
