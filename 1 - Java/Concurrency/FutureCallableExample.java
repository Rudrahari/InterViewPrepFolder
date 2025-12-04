package Concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// callable similar to runnable, used to execute a task that returns something while callable doesnt
// future is the state where  asynchronous tasks which returns something is stored.
public class FutureCallableExample {
    public static void main(String[] args) {
        // dynamically created and assigns task whenever needed
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<Integer>> futureTasks = new ArrayList<>();

        // as it's cached pool, tasks might not be executed in the same order as iteration
        for (int i = 100; i <= 1000; i += 100) {
            Future<Integer> futureTask = executorService.submit(new SumOfN(i));
            futureTasks.add(futureTask);
        }

        // asynchronous result will be shown one by one
        for (Future<Integer> task : futureTasks) {
            try {
                // blocking call calling thread(main) will wait until we get the result
                Integer result = task.get();
                System.out.println("Result from Future: " + result);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        executorService.shutdownNow();

        // Completable Future - non-blocking operation,unlike future

        CompletableFuture<Integer> asynyFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 100;
        });
        CompletableFuture<Integer> asyncSubsequentFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 100;
        });

        CompletableFuture<Integer> combinedResult = asyncSubsequentFuture.thenCombine(
                asyncSubsequentFuture,
                (a, b) -> {
                    System.out.println("Combining results: " + a + " + " + b);
                    return a + b;
                }
        );

        combinedResult.thenAccept((a) -> System.out.println("Final Result:" + a));

        // Giving some time for completable future async operation, otherwise main thread will be terminated
        // As completable future uses forkjoin.commonpool(), which are daemon threads
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class SumOfN implements Callable<Integer> {

    int number;

    public SumOfN(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        Thread.sleep(1000);
        int sum = 0;
        for (int i = 1; i <= number; i++) {
            sum += i;
        }
        System.out.println(Thread.currentThread().getName() + " computes result: " + sum);
        return sum;
    }
}
