package Concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class CustomBlockingQueue {

    private Queue<Integer> blockingQueue;
    private int capacityOfBlockingQueue;

    CustomBlockingQueue(int capacityOfBlockingQueue) {
        blockingQueue = new LinkedList<>();
        this.capacityOfBlockingQueue = capacityOfBlockingQueue;
    }

    synchronized void produce(int data) {
        while (blockingQueue.size() == capacityOfBlockingQueue) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        blockingQueue.offer(data);
        notifyAll();
        System.out.println("Produced Data: " + data);
    }

    synchronized void consume() {
        while (blockingQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        int data = blockingQueue.poll();
        notifyAll();
        System.out.println("Consumed Data: "+data);
    }

    public static void main(String[] args) {
        // Producer Consumer problem
        // where producer have to wait for the queue to be free
        // where consumer have to wait for the queue to have some element
        CustomBlockingQueue blockingQueue = new CustomBlockingQueue(5);

        Thread firstThread = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        blockingQueue.produce(i);
                    }
                }
        );
        Thread secondThread = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        blockingQueue.consume();
                    }
                }
        );

        firstThread.start();
        secondThread.start();

    }
}
