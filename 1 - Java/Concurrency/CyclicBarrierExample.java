package Concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrierAction = new CyclicBarrier(2,
                () -> System.out.println("All cities can move onto next phase"));

        City cityA = new City("CityA");
        City cityB = new City("CityB");

        new Thread(() -> {
            try {
                cityA.start(cyclicBarrierAction);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                cityB.start(cyclicBarrierAction);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

//        Will run indefinitely as it's a normal method not a thread, where the main have to complete the method's execution,
//        but we will hit the await() and waits for the another process which didn't happen
//        try {
//            cityB.start(cyclicBarrierAction);
//            cityA.start(cyclicBarrierAction);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } catch (BrokenBarrierException e) {
//            throw new RuntimeException(e);
//        }
    }
}

class City {
    String name;

    City(String name) {
        this.name = name;
    }

    void start(CyclicBarrier cyclicBarrier) throws InterruptedException, BrokenBarrierException {
        System.out.println(name + " voting count - phase 1 have started");
        Thread.sleep(1000);
        System.out.println(name + " voting count - phase 1 is completed");

        // will be blocked till the configured no of thread reach this phase
        cyclicBarrier.await();

        System.out.println(name + " voting count - phase 2 have started");
        Thread.sleep(1000);
        System.out.println(name + " voting count - phase 2 is completed");

        cyclicBarrier.await();

        System.out.println(name + " is ready for results");
    }
}
