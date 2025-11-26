package Concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    static int counter = 0;
    final static Lock lock = new ReentrantLock();
    // fair lock where threads acquire locks based on first come, first served manner
    final static Lock fairLock=new ReentrantLock(true);

    static void incrementWithTryLock() {
        if (lock.tryLock()) { // returns immediately if it's able to acquire lock
            try {
                counter++;
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Couldn't acquire the lock");
        }
    }

    static void incrementWithTryLockTimeOut() {
        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) { // try acquiring lock for some time
                try {
                    counter++;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Couldn't acquire lock within the given");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void incrementWithBlockingLock() {
        fairLock.lock(); // threads will wait to acquire lock here
        try {
            counter++;
        } finally {
            fairLock.unlock();
        }
    }

    static void incrementWithInfiniteLoop() {
        while (true) {
            if (lock.tryLock()) {
                try {
                    counter++;
                    return; // returning once done
                } finally {
                    lock.unlock(); // finally will always be executed even with return above
                }
            } else {
                System.out.println("Couldn't acquire the lock, will try again");
            }

            // hinting jvm that it doesn't need cpu, and willing it relinquish it for now
            Thread.yield();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread1=new Thread(
                () -> {
                    for(int i=0;i<1000;i++){
                        //incrementWithTryLock();
                        //incrementWithTryLockTimeOut();
                        //incrementWithBlockingLock();
                        incrementWithInfiniteLoop();
                    }
                }
        );
        Thread thread2=new Thread(
                () -> {
                    for(int i=0;i<1000;i++){
                        //incrementWithTryLock();
                        //incrementWithTryLockTimeOut();
                        //incrementWithBlockingLock();
                        incrementWithInfiniteLoop();
                    }
                }
        );

        thread1.start();
        thread2.start();

        // wait till all the increment is done for the thread's
        thread1.join();
        thread2.join();

        System.out.println(counter);
    }
}
