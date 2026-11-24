import java.util.*;

public class Knapsack01 {

    public static void main(String[] args) {
        int[] wt  = {2, 3, 4, 5};              // weight of item 1..4
        int[] val = {3, 4, 5, 6};              // value  of item 1..4
        int W = 5;                             // knapsack capacity
        int n = wt.length;

        // dp[i][w] = best value using only the first i items with capacity w
        int[][] dp = new int[n + 1][W + 1];    // row 0 and column 0 stay 0

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (wt[i - 1] > w)                              // item i does not fit
                    dp[i][w] = dp[i - 1][w];
                else                                            // best of skip / take
                    dp[i][w] = Math.max(dp[i - 1][w],
                                        val[i - 1] + dp[i - 1][w - wt[i - 1]]);
            }
            System.out.println("row " + i + ": " + Arrays.toString(dp[i]));
        }
        System.out.println("Max value = " + dp[n][W]);

        // Trace back from dp[n][W] to find which items were taken.
        List<Integer> taken = new ArrayList<>();
        int w = W;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {    // value changed: item i was taken
                taken.add(i);
                w -= wt[i - 1];
            }
        }
        Collections.reverse(taken);
        System.out.println("Items taken: " + taken);
    }
}
