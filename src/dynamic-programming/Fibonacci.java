import java.util.Arrays;

public class Fibonacci {

    static long calls = 0;                    // how many times fibNaive is called

    // 1) Naive recursion: O(2^n) time, O(n) stack space
    static int fibNaive(int n) {
        calls++;
        if (n <= 1) return n;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    // 2) Memoization (top-down): O(n) time, O(n) space
    static int[] memo;

    static int fibMemo(int n) {
        memo = new int[n + 1];
        Arrays.fill(memo, -1);                // -1 means "not solved yet"
        memo[0] = 0;                          // store the base cases in the memo
        if (n >= 1) memo[1] = 1;
        return solve(n);
    }

    static int solve(int n) {
        if (memo[n] != -1) return memo[n];    // already solved: just look it up
        memo[n] = solve(n - 1) + solve(n - 2);
        return memo[n];
    }

    // 3) Tabulation (bottom-up): O(n) time, O(n) space
    static int fibTab(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    // 4) Space-optimized: O(n) time, O(1) space
    static int fibOptimized(int n) {
        if (n <= 1) return n;
        int prev2 = 0, prev1 = 1, curr = 0;
        for (int i = 2; i <= n; i++) {
            curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println("fibNaive(6)     = " + fibNaive(n) + "   (" + calls + " calls)");
        System.out.println("fibMemo(6)      = " + fibMemo(n));
        System.out.println("fibTab(6)       = " + fibTab(n));
        System.out.println("fibOptimized(6) = " + fibOptimized(n));

        // How fast does the naive version grow?
        for (int k : new int[] {10, 20, 30, 40}) {
            calls = 0;
            fibNaive(k);
            System.out.printf("n = %2d: naive makes %,13d calls, memo solves %2d subproblems%n",
                    k, calls, k + 1);
        }
    }
}
