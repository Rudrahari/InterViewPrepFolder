package Algorithms.Sorting;

public class InsertionSort {
    static void sort(int[] array, int length) {

        for (int i = 1; i < length; i++) {
            int curEle = array[i];
            int prevousIndex = i - 1;

            while (prevousIndex >= 0 && curEle < array[prevousIndex]) {
                array[prevousIndex + 1] = array[prevousIndex];
                prevousIndex--;
            }

            array[prevousIndex + 1] = curEle;
        }
    }

    static void recursiveSort(int[] array, int curIndex, int length) {
        if (curIndex < length) {
            int curEle = array[curIndex];
            int prevousIndex = curIndex - 1;

            while (prevousIndex >= 0 && curEle < array[prevousIndex]) {
                array[prevousIndex + 1] = array[prevousIndex];
                prevousIndex--;
            }

            array[prevousIndex + 1] = curEle;

            recursiveSort(array,curIndex+1,length);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 8, -9, 78, 23, 19};
        int length = array.length;
        // sort(array, length);
        recursiveSort(array, 1, length);

        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}
