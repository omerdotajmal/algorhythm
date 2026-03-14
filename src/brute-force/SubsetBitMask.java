import java.util.List;
import java.util.ArrayList;

public class SubsetBitMask {
    public static List<List<Integer>> allSubsets(int[] a) {
        int n = a.length;
        int total = 1 << n; // 2^n subsets (works safely for n <= 30)
        List<List<Integer>> res = new ArrayList<>(total);

        for (int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0)
                    subset.add(a[i]);
            }
            res.add(subset);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 3 };
        System.out.println(allSubsets(a));
    }
}
