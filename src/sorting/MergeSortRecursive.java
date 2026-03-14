
import java.util.Arrays;

public class MergeSortRecursive {

    public static void mergeSort(int[] a) {
        if (a == null || a.length < 2)
            return;
        int[] temp = new int[a.length];
        mergeSort(a, 0, a.length - 1, temp);
    }

    private static void mergeSort(int[] a, int left, int right, int[] temp) {
        // Base case: 1 element (already sorted)
        if (left >= right)
            return;

        int mid = left + (right - left) / 2;

        // Divide
        mergeSort(a, left, mid, temp);
        mergeSort(a, mid + 1, right, temp);

        // Conquer (merge two sorted halves)
        merge(a, left, mid, right, temp);
    }

    // left half: a[left..mid]
    // right half: a[mid+1..right]
    private static void merge(int[] a, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[k] = a[i];
                i++;
                k++;
            } else if (a[j] < a[i]) {
                temp[k] = a[j];
                j++;
                k++;
            }

        }

        while (i <= mid) {
            temp[k] = a[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = a[j];
            j++;
            k++;
        }

        for (int l = left; l <= right; l++) {
            a[l] = temp[l];
        }
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 1, 2, 0, 3 };
        System.out.println("Before: " + Arrays.toString(a));
        mergeSort(a);
        System.out.println("After : " + Arrays.toString(a));
    }
}