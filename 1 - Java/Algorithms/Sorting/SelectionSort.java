package Algorithms.Sorting;

public class SelectionSort {

    static void sort(int[] array, int length) {
       // finds the smallest element in each iteration for respective index
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 8, -9, 78, 23, 19};
        int length = array.length;
        sort(array, length);

        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}

