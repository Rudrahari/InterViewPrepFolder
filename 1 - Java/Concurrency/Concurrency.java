package Concurrency;

public class Concurrency {
    public static void main(String[] args) {
        System.out.println("Current running thread is " +
                Thread.currentThread().getName());
        Thread.currentThread().setName("UpdatedMainThread");
        System.out.println("Running Thread: "
                + Thread.currentThread().getName());

        // by extending Thread class
        Thread firstThread = new ThreadClassExample();
        firstThread.start(); // Thread-0

        Thread secondThread = new ThreadClassExample("secondThread");
        secondThread.start();

        // by implementing Runnable interface
        Thread thirdThread = new Thread(new ThreadInterfaceExample(), "thirdThird");
        thirdThread.start();

        // using lambdas
        Thread fourthThread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Running Thread: "
                    + threadName);

            for (int i = 0; i < 10; i++) {
                System.out.println(threadName + ": " + i);
            }
        }, "fourthThread");
        fourthThread.start();

        // default name will be created ranging from Thread-0 to Thread-N based on the no of threads
        Thread fifthThread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Running Thread: "
                    + threadName);

            for (int i = 0; i < 100; i++) {
                System.out.println(threadName + ": " + i);
            }
        });
        // setting a thread on daemon thread will give it a less priority,
        // which will inturn terminate when all the user threads are done execution
        fifthThread.setDaemon(true);
        fifthThread.start(); // Thread-1

        System.out.println(Thread.currentThread());
    }
}
