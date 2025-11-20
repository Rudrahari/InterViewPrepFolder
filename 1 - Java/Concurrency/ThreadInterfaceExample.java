package Concurrency;

import java.util.ArrayList;

public class ThreadInterfaceExample implements Runnable {
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("Running Thread: "
                + threadName);

        for (int i = 0; i < 10; i++) {
            System.out.println(threadName + ": " + i);
        }
    }
}
