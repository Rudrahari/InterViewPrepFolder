package Concurrency;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static int price = 0;

    static void increasePrice() {
        // only one thread can acquire the lock
        readWriteLock.writeLock().lock();
        try {
            price += 100;
            System.out.println("Increased Price to: " + price);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    static void getPrice() {
        // when no thread has acquired write loc, multiple threads can acquire read lock
        readWriteLock.readLock().lock();
        try {
            System.out.println("Price: " + price);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {

        Thread writeThread = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        increasePrice();
                    }
                }
        );
        writeThread.start();

        for (int i = 0; i < 10; i++) {
            new Thread(
                    () -> {
                        for (int j = 0; j < 5; j++) {
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            getPrice();
                        }
                    }
            ).start();
        }

    }
}
