public class InsertionSort {

    public static void sort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {

            int key = arr[i]; // save the element to be inserted
            int j = i - 1;

            // shift all elements greater than key one position right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // j+1 is now the correct position for key
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 5, 2, 4, 6, 1, 3 };

        System.out.print("Before: ");
        print(arr);

        sort(arr);

        System.out.print("After:  ");
        print(arr);
    }

    private static void print(int[] a) {
        System.out.print("[");
        for (int i = 0; i < a.length; i++)
            System.out.print(a[i] + (i < a.length - 1 ? ", " : ""));
        System.out.println("]");
    }
}