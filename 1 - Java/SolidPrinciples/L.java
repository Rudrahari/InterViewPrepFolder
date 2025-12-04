package SolidPrinciples;

// L -> Liskov Substitution Principle (LSP)
// Imagine a scenario where we have two types of bank accounts, savings, and current account
// savings account gets credited with interest quarterly, while current account does not

class BankAccount{
     void getBalance(){};
     void getInterestRate(){};
}
class SavingsAccount extends BankAccount{
    @Override
    void getBalance() {
        super.getBalance();
    }

    @Override
    void getInterestRate() {
        super.getInterestRate();
    }
}
class CurrentAccount extends BankAccount{
    @Override
    void getBalance() {
        super.getBalance();
    }

    @Override
    void getInterestRate() {
        System.out.println("Illegal Operation-> Exception");
    }
}
// In LSP, a subclass should be substitutable for it's parent
public class L {
    public static void main(String[] args) {
        BankAccount bankAccount=new CurrentAccount();
        // we should have to handle scenario like this, it's an unnecessary overhead
        if(!bankAccount.getClass().getSimpleName().equals("CurrentAccount")){
            bankAccount.getInterestRate();
        }
    }

}

// Good Practice-> separating the functionality for the types of accounts
class RetailBanking{
    void getBalance(){};
    void getInterestRate(){};
}
class CommercialBanking{
    void getBalance(){};
}

// now with this child class will be substitutable for it's parent without violating LSP
class SavingsAccountClass extends RetailBanking{

}
class CurrentAccountClass extends CommercialBanking{

}
