package Algorithms.Sorting;

public class BubbleSort {

    static void sort(int[] array, int length) {
        // moves the largest value to the end in every iteration
        for (int i = 0; i < length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    isSorted = false;
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
            // just an optimized way to breakout when the array is sorted
            if (isSorted) {
                return;
            }
        }
    }

    static void recursiveSort(int[] array, int length) {
        // moves the largest value to the end in every iteration
        boolean isSorted = true;
        for (int j = 0; j <=length - 2; j++) {
            if (array[j] > array[j + 1]) {
                isSorted = false;
                int temp = array[j];
                array[j] = array[j+1];
                array[j+1] = temp;
            }
        }
        // just an optimized way to breakout when the array is sorted
        if (isSorted) {
            return;
        }
        recursiveSort(array,length-1);
    }


    public static void main(String[] args) {
        int[] array = new int[]{1, 8, -9, 78, 23, 19};
        int length = array.length;
        //sort(array, length);
        recursiveSort(array,length);

        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}
