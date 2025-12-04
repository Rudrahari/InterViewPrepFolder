package Algorithms.Sorting;

public class HeapSort {
    static void sort(int[] array,int length){
        for(int i=(length/2)-1;i>=0;i--){
            maxheapify(array,i,length);
        }

        for(int i=length-1;i>=0;i--){
            // largest which was move to the index 0 is swapped with decrementing index from end
            int largest=array[0];
            array[0]=array[i];
            array[i]=largest;

            maxheapify(array,0,i);
        }
    }
    // we assume start is the largest and heapify
    static void maxheapify(int[] array,int start,int end){
        int parentNode=start;
        int leftChildNode=(parentNode*2)+1;
        int rightChildNode=(parentNode*2)+2;

        if(leftChildNode<end && array[leftChildNode]>array[parentNode]){
            parentNode=leftChildNode;
        }
        if(rightChildNode<end && array[rightChildNode]>array[parentNode]){
            parentNode=rightChildNode;
        }

        // swap the next parent with assumed parent
        if(parentNode!=start){
            int nextParent=array[parentNode];
            array[parentNode]=array[start];
            array[start]=nextParent;

            // fix the affected tree either left or right subtree
            maxheapify(array,parentNode,end);
        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{1, 8, -9, 78, 23, 19};
        int length = array.length;
        sort(array,length);

        for (int j : array) {
            System.out.print(j + " ");
        }
    }
}
