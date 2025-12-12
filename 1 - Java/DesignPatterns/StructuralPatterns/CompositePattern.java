package DesignPatterns.StructuralPatterns;

import java.util.List;

interface PaymentType {
    void getPaymentTypeDetails();
}

interface Card {
    void cardPaymentInstructions();
}

class Upi implements PaymentType {

    @Override
    public void getPaymentTypeDetails() {
        System.out.println("UPI - Pay Here");
    }
}

class CreditCard implements PaymentType, Card {

    @Override
    public void cardPaymentInstructions() {
        System.out.println("Credit Card attracts additional 2% transaction fees");
    }

    @Override
    public void getPaymentTypeDetails() {
        System.out.println("Credit Card - Pay Here by entering your card details");
        cardPaymentInstructions();
    }
}

class DebitCard implements PaymentType, Card {

    @Override
    public void cardPaymentInstructions() {
        System.out.println("Debit Card attracts additional 1.5% transaction fees");
    }

    @Override
    public void getPaymentTypeDetails() {
        System.out.println("Debit Card - Pay Here by entering your card details");
        cardPaymentInstructions();
    }
}

class Wallet implements PaymentType {

    @Override
    public void getPaymentTypeDetails() {
        System.out.println("Wallet - connect to your wallet to pay");
    }
}

class PaymentGateway implements PaymentType {

    private final List<PaymentType> allPaymentTypes;

    PaymentGateway(List<PaymentType> allPaymentTypes) {
        this.allPaymentTypes = allPaymentTypes;
    }

    @Override
    public void getPaymentTypeDetails() {
        System.out.println("All available payments in our payment gateway");
        for (PaymentType paymentType : allPaymentTypes) {
            paymentType.getPaymentTypeDetails();
        }
    }
}

public class CompositePattern {
    public static void main(String[] args) {
        PaymentType upi = new Upi();
        PaymentType creditCard = new CreditCard();
        PaymentType debitCard = new DebitCard();
        PaymentType wallet = new Wallet();

        PaymentType razorPaymentGateway =
                new PaymentGateway(List.of(upi,creditCard,debitCard,wallet));
        PaymentType payUPaymentGateway=
                new PaymentGateway(List.of(upi,creditCard,debitCard));

        razorPaymentGateway.getPaymentTypeDetails();
        payUPaymentGateway.getPaymentTypeDetails();

        // can also do this
        PaymentType paymentAggregators=new PaymentGateway(List.of(razorPaymentGateway,
                payUPaymentGateway));
        paymentAggregators.getPaymentTypeDetails();
    }
}
