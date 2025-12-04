package Algorithms.Sorting;

public class QuickSort {
    static void sort(int[] array,int start, int end){
         if(start<end){
             int nextPivot=nextPivotFinder(array,start,end);
             sort(array,start,nextPivot-1);
             sort(array,nextPivot+1,end);
         }
    }
    static int nextPivotFinder(int[] array,int start,int end){
        // choosing last element in array as pivot
        int pivot=array[end];
        int i=start;
        // traverse and swap elements with i, and j index when it's smaller than pivot
        for(int j=start;j<end;j++){
            if(pivot>array[j]){
                int temp=array[j];
                array[j]=array[i];
                array[i]=temp;
                i++;
            }
        }
        int temp=array[end];
        array[end]=array[i];
        array[i]=temp;
        return i;
    }
    public static void main(String[] args) {
        int[] array = new int[]{1, 8, -9, 78, 23, 19};
        int length = array.length;
        sort(array, 0, length-1);

        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}
