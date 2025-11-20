package Concurrency;

public class BankSynchronization {

    private int balance;

    BankSynchronization() {
        this.balance = 0;
    }

    // if we didn't use a synchronized block, there may be a time
    // when multiple threads read the same balance and deposit it
    // which will cause inconsistent state
    public synchronized void depositMoney(int deposit) {
        System.out.println("Depositing : " + deposit);
        balance += deposit;
    }

    // just to make sure that the balance is fetched when no one is depositing money.
    public void getBankBalance() {
        // this is same as above method signature way
        synchronized (this) {
            System.out.println("Current Balance is: " + balance);
        }
    }

}
