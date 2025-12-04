package Concurrency;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicVariablesExample {
    private static AtomicInteger atomicInteger=new AtomicInteger(0);
    private static volatile int volatileInteger=0;

    static void increment(){
        atomicInteger.incrementAndGet();
        volatileInteger++;
    }

    static void decrement(){
        atomicInteger.decrementAndGet();
        volatileInteger--;
    }

    static void getValues(){
        System.out.println("Atomic Integer: "+atomicInteger.get());
        System.out.println("Volatile Integer: "+volatileInteger);
    }

    public static void main(String[] args) {

        ExecutorService executor= Executors.newCachedThreadPool();

        for(int i=0;i<1000;i++){
            executor.submit(()->{
                increment();
            });
        }
        // shutting down and waiting for some time to get results
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // atomic integer will always give 1000, while volatile will give inconsistent results
        getValues();

        // create new instance as the previous one is terminated
        executor= Executors.newCachedThreadPool();
        for(int i=0;i<1000;i++){
            executor.submit(()->{
                decrement();
            });
        }
        // shutting down and waiting for some time to get results
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // atomic integer will always give 0, while volatile will give inconsistent results
        getValues();

        // other atomic types
        AtomicBoolean atomicBoolean=new AtomicBoolean(false);
        AtomicReference<ArrayList<Integer>> atomicObject=new AtomicReference<>();

    }

}
