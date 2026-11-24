public class Warshall {

    public static void main(String[] args) {
        String[] names = {"A", "B", "C", "D"};
        int V = 4;

        // reach[i][j] = true if there is an edge i -> j (directed)
        boolean[][] reach = {
            {false, true,  false, false},     // A -> B
            {false, false, false, true },     // B -> D
            {false, false, false, false},     // C has no outgoing edges
            {true,  false, true,  false}      // D -> A, D -> C
        };

        for (int k = 0; k < V; k++) {           // now allow vertex k as a stop-over
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (reach[i][k] && reach[k][j]) {
                        reach[i][j] = true;     // i reaches j through k
                    }
                }
            }
            System.out.println("after k = " + names[k] + ":");
            print(reach, names);
        }
    }

    static void print(boolean[][] r, String[] names) {
        for (int i = 0; i < r.length; i++) {
            StringBuilder row = new StringBuilder("  " + names[i] + ":");
            for (boolean b : r[i]) row.append(b ? "  1" : "  0");
            System.out.println(row);
        }
    }
}
