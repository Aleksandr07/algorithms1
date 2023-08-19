import java.util.Arrays;
import java.util.Random;

public class ChooseSort {

    public static void main(String[] args) {
        int[] integers1 = new int[100_000];

        Random random = new Random();

        for (int i = 0; i < integers1.length; i++) {
            integers1[i] = random.nextInt(100) + 1;
        }
        int[] integers2 = Arrays.copyOf(integers1, integers1.length);
        int[] integers3 = Arrays.copyOf(integers1, integers1.length);


        long start1 = System.currentTimeMillis();
        sortBubble(integers1);
        System.out.println(System.currentTimeMillis() - start1);

        long start2 = System.currentTimeMillis();
        sortSelection(integers2);
        System.out.println(System.currentTimeMillis() - start2);

        long start3 = System.currentTimeMillis();
        sortInsertion(integers3);
        System.out.println(System.currentTimeMillis() - start3);

    }

    public static void sortBubble(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    public static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}
