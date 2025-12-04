package Concurrency;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        NewsPaper newsPaper=new NewsPaper(5);

        for(int i=1;i<=32;i++){
            int rollNo=i;
            new Thread(
                    ()->{
                        try {
                            newsPaper.readNewsPaper("RollNo-"+rollNo);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            ).start();
        }
    }
}

class NewsPaper {
    private Semaphore semaphore;

    NewsPaper(int count) {
        semaphore = new Semaphore(count);
    }

    void readNewsPaper(String studentName) throws InterruptedException {
        // acquire permit
        semaphore.acquire();

        // gets the permit
        System.out.println(studentName + " got permit to read news paper.started reading, "
                + "permits left for other students at this moment " + semaphore.availablePermits());
        Thread.sleep(1000);

        System.out.println(studentName+" finished reading");
        // releases permit
        semaphore.release();
    }
}
