package OOP.Principles.Encapsulation;

public class Encapsulation {
    public static void main(String[] args) {
        // Encapsulation - wrapping data members and behavior as a single unit
        // restricting direct access to internal details
        // providing usage through a controlled medium
        // Encapsulation is implementation level methodology

        DematAccount dematAccount=
                new DematAccount("ABX340E","NewVue");

        dematAccount.getDematAccountDetails();
        dematAccount.addAmountToDemat(1000);
        dematAccount.addAmountToDemat(1500);
        dematAccount.withdraw(4000);
    }
}
