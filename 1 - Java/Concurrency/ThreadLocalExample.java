package Concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalExample {
    public static void main(String[] args) {
        // every thread will have it's own copy of thread local
        ThreadLocal<Pair> curPair=new ThreadLocal<>();

        ExecutorService executorService= Executors.newCachedThreadPool();

        // all the threads from the thread pools will have it's own copy
        // set method on already initialized object on thread local variables will be overridden.
        for(int i=0;i<100;i++){
            int key=i;
            int value=i*2+100;
            executorService.submit(
                    ()->{
                        curPair.set(new Pair(key,value));
                        System.out.println("Current Thread: "+Thread.currentThread().getName()
                                +" Current Pair: "+curPair.get());
                    }
            );
        }

        executorService.shutdown();
    }
}

class Pair{
    int key;
    int value;

    Pair(int key, int value){
        this.key=key;
        this.value=value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
