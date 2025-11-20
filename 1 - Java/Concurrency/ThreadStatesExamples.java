package Concurrency;

public class ThreadStatesExamples {

    private static Object lock = new Object();

    public static void main(String[] args) {

        Thread thread = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                        synchronized (lock) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    // extra sleep time for join example
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        // main thread is running -> RUNNABLE
        System.out.println(Thread.currentThread().getState());
        // child thread is created not yet started -> NEW
        System.out.println(thread.getState());
        thread.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // thread is sleeping for a time period -> TIMED_WAITING
        System.out.println(thread.getState());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // it acquired the lock and is waiting indefinitely -> WAITING
        System.out.println(thread.getState());
        synchronized (lock) {
            lock.notifyAll();
            // when the notification for a lock release is sent, blocked for reacquiring lock -> BLOCKED
            System.out.println(thread.getState());
        }
        try {
            // join will make the current tread to wait
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // with joinin, at the end all the process have been completed -> TERMINATED
        // without join thread will be sleeping in our case, so -> TIMED_WAITING
        System.out.println(thread.getState());
    }
}
