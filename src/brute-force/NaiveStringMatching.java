import java.util.*;

public class NaiveStringMatching {
    // Returns all starting indices where pattern occurs in text.
    public static List<Integer> matchAll(String text, String pattern) {
        List<Integer> hits = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        if (m == 0) { // define: empty pattern matches at every index
            for (int i = 0; i <= n; i++)
                hits.add(i);
            return hits;
        }
        if (m > n)
            return hits;
        for (int s = 0; s <= n - m; s++) {
            int j = 0;
            while (j < m && text.charAt(s + j) == pattern.charAt(j)) {
                j++;
            }
            if (j == m)
                hits.add(s);
        }
        return hits;
    }

    public static void main(String[] args) {
        String T = "AACAABA";
        String P = "AABA";
        System.out.println(matchAll(T, P));
    }
}