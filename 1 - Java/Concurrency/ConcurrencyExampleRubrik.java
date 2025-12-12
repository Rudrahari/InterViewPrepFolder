package Concurrency;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// found online
//Problem Statement:
//        There is single Bathroom to be used in a Voting agency for both Democrats(D) and Republicans(R)
//        * This single Bathroom which can accommodate 3 people at most
//        * each person takes f(N) secs to do his thing. f(N) is a function of the person's name and returns varying number
//        * CONDITION: At any given time, the bathroom cannot have a mixed set of people
//        i.e. * CONDITION: Bathroom can have at most 3 people * these combinations aren't allowed (2D, 1R) or (1D,1R)
//        * These are allowed (), (3D), (2D), (1R) i.e. pure Republicans or Pure Democrats
//        * While the bathroom is occupied people are to wait in a queue * What is the most optimal system where you would manage people in this queue, so that
//        * the most eligible person instants gets to use the bathroom whenever its has room, based on above conditions
class Solution1 {
    private  int democrats;
    private  int republicans;
    STATE bathroomState;
    // Introducing turn variable for fairness
    TURN turnState;

    Solution1(){
        democrats=0;
        republicans= 0;
        bathroomState = STATE.EMPTY;
        turnState = TURN.DEMOCRAT;
    }

    synchronized void entryRepublican(int time) throws InterruptedException {
        while(bathroomState.equals(STATE.DEMOCRAT) || republicans >= 3 || (turnState.equals(TURN.DEMOCRAT) && democrats > 0)){
            wait();
        }
        if(bathroomState.equals(STATE.EMPTY)){
            bathroomState = STATE.REPUBLICAN;
            turnState = TURN.REPUBLICAN;
        }
        republicans++;
        processR(time);
    }

    void processR(int time) throws InterruptedException {
        Thread th = new Thread(() -> {
            System.out.println("R is doing his thing for time: " + time);
            System.out.println("Number of (R,D) in bathroom : " + republicans + ", " + democrats);
            try {
                Thread.sleep(time * 1000);
                exitRepublican();

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        th.start();

    }

    synchronized void entryDemocrat(int time) throws InterruptedException {
        while(bathroomState.equals(STATE.REPUBLICAN) || democrats >= 3 || (turnState.equals(TURN.REPUBLICAN) && republicans > 0)){
            wait();
        }
        if(bathroomState.equals(STATE.EMPTY)){
            bathroomState = STATE.DEMOCRAT;
            turnState = TURN.DEMOCRAT;
        }
        democrats++;
        processD(time);
    }

    void processD(int time) throws InterruptedException {
        Thread th = new Thread(() -> {
            System.out.println("D is doing his thing for time: " + time);
            System.out.println("Number of (R,D) in bathroom : " + republicans + ", " + democrats);
            try {
                Thread.sleep(time * 1000);
                exitDemocrat();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        th.start();

    }
    synchronized void exitDemocrat() {
        democrats--;
        if (democrats == 0) {
            bathroomState = STATE.EMPTY;
            turnState = TURN.REPUBLICAN;
            notifyAll();
        }
    }

    synchronized void exitRepublican() {
        republicans--;
        if (republicans == 0) {
            bathroomState = STATE.EMPTY;
            turnState = TURN.DEMOCRAT;
            notifyAll();
        }
    }


}

enum STATE {
    REPUBLICAN,
    DEMOCRAT,
    EMPTY;
}

enum TURN {
    DEMOCRAT,
    REPUBLICAN
}




class ConcurencyExampleRubrik {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();

        List<Future<?>> futures = new ArrayList<Future<?>>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 5; i++){
            futures.add(executorService.submit(() -> {
                try {
                    solution.entryRepublican(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
        }

        for(int i = 0; i < 10; i++){
            futures.add(executorService.submit(() -> {
                try {
                    solution.entryDemocrat(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
        }




        for(int i = 0; i < 10; i++){
            futures.add(executorService.submit(() -> {
                try {
                    solution.entryRepublican(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
        }

        for(Future future : futures){
            try{
                future.get();
            }catch (Exception ex){

            }
        }

        executorService.shutdown();
    }
}