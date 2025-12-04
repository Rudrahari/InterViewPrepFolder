package Concurrency;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolExample {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long result;
        SumOfNTask sumOfNTask = new SumOfNTask(0, 100000000);
        // this line replaces the below fork join lines
        result = forkJoinPool.invoke(sumOfNTask);
//        sumOfNTask.fork();
//        result = sumOfNTask.join();
        System.out.println(result);

        int[] data = new int[10000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }

        ArrayTask arrayTask = new ArrayTask(data, 0, 9999);
        forkJoinPool.invoke(arrayTask);
        System.out.println(Arrays.toString(data));
    }
}

class SumOfNTask extends RecursiveTask<Long> {

    private static final int TASKTHRESHOLD = 10000;
    private long start;
    private long end;

    SumOfNTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        long length = (end - start) + 1;
        if (length <= TASKTHRESHOLD) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
        long mid = start + (end - start) / 2;
        SumOfNTask leftSumOfNTask = new SumOfNTask(start, mid);
        SumOfNTask rightSumOfNTask = new SumOfNTask(mid + 1, end);
        // using fork to calculate it asynchronously
        leftSumOfNTask.fork();
        // calculating in the same thread
        long rightSum = rightSumOfNTask.compute();
        // get the result
        long leftSum = leftSumOfNTask.join();
        return leftSum + rightSum;
    }
}

class ArrayTask extends RecursiveAction {

    private static final int TASKTHRESHOLD = 10000;
    private int[] array;
    private int start;
    private int end;

    ArrayTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        long length = (end - start) + 1;
        if (length <= TASKTHRESHOLD) {
            for (int i = start; i <= end; i++) {
                array[i] = array[i] * 10;
            }
            return;
        }
        int mid = start + (end - start) / 2;

        ArrayTask leftArray = new ArrayTask(array, start, mid);
        ArrayTask rightArray = new ArrayTask(array, mid + 1, end);

        leftArray.fork();
        rightArray.fork();
        leftArray.join();
        rightArray.join();
        // above equivalent in one line
        //invokeAll(leftArray,rightArray);
    }
}
