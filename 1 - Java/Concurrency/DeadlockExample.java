package Concurrency;

public class DeadlockExample {
    public static void main(String[] args) {
        // any object can be used as a lock, not primitive types
        Integer lock1=100;
        Integer lock2=101;

        Thread firstThread=new Thread(
                ()->{
                    synchronized (lock1){
                        System.out.println("Acquired Lock1, moving on to Lock2");
                        try {
                            Thread.sleep(100);
                            System.out.println("Trying to acquire Lock2");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        synchronized (lock2){
                            System.out.println("Acquired Lock2");
                        }
                    }
                }
        );
        Thread secondThread=new Thread(
                ()->{
                    synchronized (lock2){
                        System.out.println("Acquired Lock2, moving on to Lock1");
                        try {
                            Thread.sleep(100);
                            System.out.println("Trying to acquire Lock1");
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        synchronized (lock1){
                            System.out.println("Acquired lock1");
                        }
                    }
                }
        );

        firstThread.start();
        secondThread.start();
    }
}
