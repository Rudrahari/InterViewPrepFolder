package Algorithms.Sorting;

public class MergeSort {
    static void sort(int[] array, int length){
        if(length<=1){
            return;
        }

        // split and copy the values from main array
        int leftSize=length/2;
        int rightSize=length-leftSize;

        int[] leftSubArray=new int[leftSize];
        int[] rightSubArray=new int[rightSize];

        for(int i=0;i<length;i++){
            if(i<leftSize){
                leftSubArray[i]=array[i];
            }else{
                rightSubArray[i-leftSize]=array[i];
            }
        }
        // left sub array will come out as sorted
        sort(leftSubArray,leftSize);
        // right sub array will come out as sorted
        sort(rightSubArray,rightSize);
        merge(leftSubArray,rightSubArray,array);
    }
    static void merge(int[] left,int[] right,int[] array){
        int l=0,r=0,a=0;

        while(l<left.length && r<right.length){
            if(left[l]<right[r]){
                array[a++]=left[l++];
            }else{
                array[a++]=right[r++];
            }
        }

        while(l<left.length){
            array[a++]=left[l++];
        }

        while(r<right.length){
            array[a++]=right[r++];
        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{1, 8, -9, 78, 23, 19,0};
        int length = array.length;
        sort(array, length);

        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}
