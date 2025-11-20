package Concurrency;

public class ThreadClassExample extends Thread {

    ThreadClassExample() {
    }

    ThreadClassExample(String threadName) {
        super(threadName);
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Running Thread: "
                + threadName);

        for (int i = 0; i < 10; i++) {
            System.out.println(threadName + ": " + i);
        }
        // will  show it's name, priority, parent thread name
        System.out.println(Thread.currentThread());
    }
}
