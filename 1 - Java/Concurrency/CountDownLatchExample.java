package Concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(2);

        Message message1=new Message("Message 1");
        Message message2=new Message("Message 2");
        Message message3=new Message("Message 3");
        Message message4=new Message("Message 4");

        message1.start(countDownLatch);
        message2.start(countDownLatch);
        message3.start(countDownLatch);
        message4.start(countDownLatch);

        // blocks the main thread until 2 messages have been sent
        // as we configured 2 in count down latch
        // we can configure the count based on our requirement
        System.out.println("We will wait for 2 messages to be sent");
        countDownLatch.await();
        System.out.println("2 messages have been sent");
    }
}

class Message {
    private String name;

    Message(String name) {
        this.name = name;
    }

    void start(CountDownLatch countDownLatch) {
        new Thread(
                () -> {
                    try {
                        System.out.println("Processing Message: " + name);
                        Thread.sleep(1000);
                        System.out.println("Sent Message: " + name);
                        countDownLatch.countDown();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).start();
    }
}
