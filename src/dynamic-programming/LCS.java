import java.util.Arrays;

public class LCS {

    public static void main(String[] args) {
        String X = "ABCBDAB";
        String Y = "BDCABA";
        int m = X.length(), n = Y.length();

        // L[i][j] = length of an LCS of the first i letters of X and the first j letters of Y
        int[][] L = new int[m + 1][n + 1];     // row 0 and column 0 stay 0

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1))
                    L[i][j] = L[i - 1][j - 1] + 1;                // match: diagonal + 1
                else
                    L[i][j] = Math.max(L[i - 1][j], L[i][j - 1]); // no match: best of up, left
            }
            System.out.println("row " + i + " (" + X.charAt(i - 1) + "): " + Arrays.toString(L[i]));
        }
        System.out.println("LCS length = " + L[m][n]);

        // Trace back from L[m][n] to recover one LCS.
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (X.charAt(i - 1) == Y.charAt(j - 1)) {     // match: this letter is in the LCS
                lcs.append(X.charAt(i - 1));
                i--;
                j--;
            } else if (L[i - 1][j] >= L[i][j - 1]) {
                i--;                                      // the value came from above
            } else {
                j--;                                      // the value came from the left
            }
        }
        System.out.println("One LCS    = " + lcs.reverse());
    }
}
