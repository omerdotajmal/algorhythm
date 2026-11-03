import java.util.*;

public class KruskalMST {

    static int[] parent;   // Union-Find: parent[x] == x means x is a root

    // Find the root (representative) of x's component.
    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);   // path compression: point x straight at the root
        return parent[x];
    }

    public static void main(String[] args) {
        String[] names = {"A", "B", "C", "D", "E", "F"};
        int V = 6;

        int[][] edges = {         // {u, v, weight}  - undirected
            {0, 1, 5},  // A-B
            {1, 2, 6},  // B-C
            {3, 4, 2},  // D-E
            {4, 5, 7},  // E-F
            {0, 3, 4},  // A-D
            {1, 4, 3},  // B-E
            {2, 5, 1}   // C-F
        };

        // Step 1: sort edges by weight. Integer.compare avoids overflow,
        // unlike the shortcut (a, b) -> a[2] - b[2].
        Arrays.sort(edges, (a, b) -> Integer.compare(a[2], b[2]));

        // Step 2: every vertex starts as its own component.
        parent = new int[V];
        for (int i = 0; i < V; i++) parent[i] = i;

        int total = 0, count = 0;
        List<String> mst = new ArrayList<>();

        // Step 3: take each edge in order unless it would close a cycle.
        for (int[] e : edges) {
            int ru = find(e[0]), rv = find(e[1]);
            String edge = names[e[0]] + "-" + names[e[1]];

            if (ru == rv) {                               // same component -> cycle
                System.out.printf("%-4s w=%d  find=%s,%s  SKIP (cycle)%n",
                        edge, e[2], names[ru], names[rv]);
                continue;
            }
            parent[rv] = ru;                              // union: attach one root to the other
            mst.add(edge + " (" + e[2] + ")");
            total += e[2];
            count++;
            System.out.printf("%-4s w=%d  find=%s,%s  TAKE         parent=%s%n",
                    edge, e[2], names[ru], names[rv], show(parent, names));

            if (count == V - 1) break;                    // tree complete: stop early
        }

        System.out.println("MST edges: " + mst);
        System.out.println("Total weight: " + total);
    }

    static String show(int[] p, String[] names) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < p.length; i++)
            sb.append(names[i]).append(":").append(names[p[i]]).append(i < p.length - 1 ? " " : "]");
        return sb.toString();
    }
}
