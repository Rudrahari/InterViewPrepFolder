package DesignPatterns.StructuralPatterns;

interface Bank {
    void getAccountOpeningDetails();

    void getTypesOfAccounts();
}

class RetailBank implements Bank {

    @Override
    public void getAccountOpeningDetails() {
        System.out.println("For accounting opening");
        System.out.println("Visit nearby branch for filling account opening form, and do kyc");
    }

    @Override
    public void getTypesOfAccounts() {
        System.out.println("To know more about our account types");
        System.out.println("Contact the nearby branch for more details about account types");
    }
}

class DigitalBank implements Bank {

    @Override
    public void getAccountOpeningDetails() {
        System.out.println("For accounting opening");
        System.out.println("Download our app, use can do anything from there.");
    }

    @Override
    public void getTypesOfAccounts() {
        System.out.println("To know more about our account types");
        System.out.println("Open our app, and click account types");
    }
}

abstract class BankBridge {
    final Bank bank;

    BankBridge(Bank bank) {
        this.bank = bank;
    }

    abstract void getAllQueriesAboutAccountOpening();

    // can add more functionality here as well
    abstract void welcomeBenefits();
}

class AbcBank extends BankBridge {

    AbcBank(Bank bank) {
        super(bank);
    }

    @Override
    void getAllQueriesAboutAccountOpening() {
        bank.getAccountOpeningDetails();
        bank.getTypesOfAccounts();
    }

    @Override
    void welcomeBenefits() {
        System.out.println("Get Personal Loan with interest rate @ 1.20% p/m");
    }
}

class BcdBank extends BankBridge {

    BcdBank(Bank bank) {
        super(bank);
    }

    @Override
    void getAllQueriesAboutAccountOpening() {
        bank.getAccountOpeningDetails();
        bank.getTypesOfAccounts();
    }

    @Override
    void welcomeBenefits() {
        System.out.println("Get Gift Card worth $100");
    }
}

// bridge helps us decoupling abstraction from implementation
// abstraction - implementation - bridge - bridge implementations
// instead of directly relying on abstraction's implementation, we use it through bridge
public class BridgePattern {
    public static void main(String[] args) {
        Bank retailBank = new RetailBank();
        Bank digitalBank = new DigitalBank();

        BankBridge abcBank = new AbcBank(retailBank);
        abcBank.welcomeBenefits();
        abcBank.getAllQueriesAboutAccountOpening();

        abcBank = new AbcBank(digitalBank);
        abcBank.welcomeBenefits();
        abcBank.getAllQueriesAboutAccountOpening();

        BankBridge bcdBank = new BcdBank(retailBank);
        bcdBank.welcomeBenefits();
        bcdBank.getAllQueriesAboutAccountOpening();

        bcdBank = new BcdBank(digitalBank);
        bcdBank.welcomeBenefits();
        bcdBank.getAllQueriesAboutAccountOpening();

    }
}
