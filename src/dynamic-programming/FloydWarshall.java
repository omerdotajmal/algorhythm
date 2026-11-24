public class FloydWarshall {

    static final int INF = Integer.MAX_VALUE;   // no path known yet

    public static void main(String[] args) {
        String[] names = {"A", "B", "C", "D"};
        int V = 4;

        // dist[i][j] = weight of the edge i -> j (directed); 0 on the diagonal
        int[][] dist = {
            {0,   3,   INF, 7  },
            {8,   0,   2,   INF},
            {5,   INF, 0,   1  },
            {2,   INF, INF, 0  }
        };

        for (int k = 0; k < V; k++) {           // now allow vertex k as a stop-over
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF
                            && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];   // shorter through k
                    }
                }
            }
            System.out.println("after k = " + names[k] + ":");
            print(dist, names);
        }
    }

    static void print(int[][] d, String[] names) {
        for (int i = 0; i < d.length; i++) {
            StringBuilder row = new StringBuilder("  " + names[i] + ":");
            for (int x : d[i]) row.append(String.format("%5s", x == INF ? "INF" : x));
            System.out.println(row);
        }
    }
}
