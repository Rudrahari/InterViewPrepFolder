package Concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class ConcurrentCollections {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(5);

        Map<String, Integer> concurrentHashMap = new ConcurrentHashMap<>();
        Map<String, Integer> nonConcurrentHashMap = new HashMap<>();

        ConcurrentSkipListMap<Integer, Integer> concurrentSkipListMap =
                new ConcurrentSkipListMap<>();
        for (int i = 0; i < 100; i++) {
            // have to create a variable as variables used in final are effectively final,
            // cannot be used for these operations
            int iterationIndex = i;
            executor.submit(() -> {
                // not atomic operation-> it's not a single operation
                // have to get value, increment it, update it.
                // other threads can update it when it is calculating
                // thread 1 -> gets 0, start calculation , thread 2-> get 0 when thread 1 is calculating
                // to overcome we can use atomic integer or compute
                // concurrentHashMap.put("K", concurrentHashMap.get("K") + 1);
                // nonConcurrentHashMap.put("K", nonConcurrentHashMap.get("K") + 1);
                // atomic operation
                concurrentSkipListMap.put(iterationIndex, iterationIndex);
                concurrentHashMap.compute("K",
                        (key, value) -> value == null ? 1 : value + 1);
                nonConcurrentHashMap.compute("K",
                        (key, value) -> value == null ? 1 : value + 1);
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.SECONDS);
        // will always be 100
        System.out.println(concurrentHashMap.get("K"));
        // inconsistent results
        System.out.println(nonConcurrentHashMap.get("K"));
        // skip list map -> keys will be in sorted order
        System.out.println(concurrentSkipListMap);

        CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add(0);
        copyOnWriteArrayList.add(1);
        CopyOnWriteArraySet<Integer> copyOnWriteArraySet = new CopyOnWriteArraySet<>();
        copyOnWriteArraySet.add(0);
        copyOnWriteArrayList.add(1);

        for (Integer num : copyOnWriteArrayList) {
            // this will throw concurrent modification exception in non-concurrent collections
            copyOnWriteArraySet.add(num);
            copyOnWriteArraySet.add(num);
        }

        // multi producer consumer pattern
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();

        executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            int iterationIndex = i;
            executor.submit(
                    () -> {
                        if(iterationIndex % 2 == 0 ){
                            blockingQueue.add(iterationIndex);
                        }else{
                            try {
                                int val=blockingQueue.take();
                                System.out.println("Consumed: "+val);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
            );
        }

        executor.shutdown();
    }
}
