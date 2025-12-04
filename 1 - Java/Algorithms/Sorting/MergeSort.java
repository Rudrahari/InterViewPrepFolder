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
    static void sort(int[] array,int start,int end){
        if(start<end){
            int mid=(start+end)/2;

            sort(array,start,mid);
            sort(array,mid+1,end);
            merge(array,start,mid,end);
        }
    }
    static void merge(int[] array,int start,int mid,int end){
        int[] sortedPart=new int[end-start+1];
        int left=start;
        int right=mid+1;
        int idx=0;
        while(left<=mid && right<=end){
            if(array[left]<array[right]){
                sortedPart[idx++]=array[left++];
            }else{
                sortedPart[idx++]=array[right++];
            }
        }
        while(left<=mid){
            sortedPart[idx++]=array[left++];
        }
        while(right<=end){
            sortedPart[idx++]=array[right++];
        }

        for(int i=0;i<sortedPart.length;i++){
            array[start+i]=sortedPart[i];
        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{1, 8, -9, 78, 23, 19,0};
        int length = array.length;
        // sub array based
        // sort(array, length);
        // index based
        sort(array,0,length-1);
        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}
