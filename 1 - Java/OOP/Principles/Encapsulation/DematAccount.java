package OOP.Principles.Encapsulation;

public class DematAccount {

    // data hiding
    final private String dematId;
    final private String dematAccountName;
    private double dematAccountBalance;


    DematAccount(String dematId,String dematAccountName){
        this.dematId=dematId;
        this.dematAccountName=dematAccountName;
        this.dematAccountBalance=0.0;
    }

    public void getDematAccountDetails(){
        System.out.println("Demat Id: "+dematId);
        System.out.println("Name: "+dematAccountName);
        System.out.println("Balance: "+dematAccountBalance);
    }

    private boolean canWithdrawMoney(double amount){
        return dematAccountBalance>amount;
    }

    // validation for controlled access, so they don't mess up
    public void withdraw(double amountToWithdraw){
        if(canWithdrawMoney(amountToWithdraw)){
            System.out.println("Money sent to bank successfully");
        }
        else{
            System.out.println("Insufficient balance in demat account");
        }
    }

    public void addAmountToDemat(double amount){
        if(amount>0.0){
            System.out.println(amount+" added successfully to your demat account");
        }
        else{
            System.out.println(amount+ " is not valid");
        }
    }


}
