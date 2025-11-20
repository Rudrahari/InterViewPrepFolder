package Concurrency;

public class SynchronizationExample {
    public static void main(String[] args) {

        BankSynchronization bankAccount = new BankSynchronization();

        Thread personOne = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        bankAccount.depositMoney(100);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, "personOne"
        );

        Thread personTwo = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        bankAccount.getBankBalance();
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, "personTwo"
        );

        // try without synchronization balance would be inconsistent
        personOne.start();
        personTwo.start();

    }
}
