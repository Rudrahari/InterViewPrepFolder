package Concurrency;

import java.util.concurrent.*;

public class ExecutorServiceExample {
    // custom thread pools, have to give params
//    static ExecutorService customThreadPool=
//            new ThreadPoolExecutor();

    // Fixed Thread pools executor
    static ExecutorService fixedThreadExecutor= Executors.newFixedThreadPool(5);

    // Cached Thread pools executor, dynamically creates new thread for handling tasks
    static ExecutorService cachedThreadExecutor=Executors.newCachedThreadPool();

    static ExecutorService singleThreadExecutor=Executors.newSingleThreadExecutor();

    static ScheduledExecutorService scheduleThreadExecutor=Executors.newScheduledThreadPool(2);

    public static void main(String[] args) {

        System.out.println("---- Fixed Thread Pools Executor ----");
        for(int count=0;count<100;count++){
            final int taskCount=count;
            fixedThreadExecutor.submit(
                    ()->{
                        System.out.println("Counter: "+taskCount
                                +" Executed by Fixed Pool Thread: "+Thread.currentThread().getName());
                    }
            );
        }

        try {
            // shut it down, or it will run forever
            // graceful shutdown to give remaining tasks some time
            fixedThreadExecutor.shutdown();
            if(fixedThreadExecutor.awaitTermination(5, TimeUnit.SECONDS)){
                System.out.println("True if all the tasks completed," +
                        " and thread terminate within the time limit");
                // if we want to stop it before giving it some time like above
                // use below
                // fixedThreadExecutor.shutdownNow();
            }else{
                System.out.println("False if all tasks haven't been completed," +
                        "and thread haven't been terminated");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("---- Cached Thread Pools Executor ----");
        for(int count=0;count<100;count++){
            final int taskCount=count;
            cachedThreadExecutor.submit(
                    ()->{
                        System.out.println("Counter: "+taskCount
                                +" Executed by Cached Thread Pool: "+Thread.currentThread().getName());
                    }
            );
        }

        // as cached thread will dynamically create threads
        // it will be completed before forcibly shutting down as below
        cachedThreadExecutor.shutdownNow();

        System.out.println("---- Single Thread Pools Executor ----");
        for(int count=0;count<100;count++){
            final int taskCount=count;
            singleThreadExecutor.submit(
                    ()->{
                        System.out.println("Counter: "+taskCount
                                +" Executed by Single Pool Thread: "+Thread.currentThread().getName());
                    }
            );
        }

        try {
            // as it's graceful shutdown, and the main thread ran all the 100 tasks
            // single thread will run all the tasks which were queued
            // forcible shutdown will not allow to execute those tasks queued
            singleThreadExecutor.shutdown();
            if(singleThreadExecutor.awaitTermination(10, TimeUnit.SECONDS)){
                System.out.println("True if all the tasks completed," +
                        " and thread terminate within the time limit");
            }else{
                System.out.println("False if all tasks haven't been completed," +
                        "and thread haven't been terminated");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("---- Scheduled Thread Pools Executor ----");

        // some examples
        scheduleThreadExecutor.schedule(
                ()->{
                    System.out.println("Executed once, after the configured delay");
                },5,TimeUnit.SECONDS
        );

        scheduleThreadExecutor.scheduleAtFixedRate(
                ()->{
                    System.out.println("Will be executed in a calculated interval, check docs");
                },1,2,TimeUnit.SECONDS
        );

        scheduleThreadExecutor.scheduleWithFixedDelay(
                ()->{
                    System.out.println("Executed in a timed interval");
                },0,3,TimeUnit.SECONDS
        );

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scheduleThreadExecutor.shutdown();
    }
}
