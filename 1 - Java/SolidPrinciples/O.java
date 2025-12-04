package SolidPrinciples;

import java.util.Arrays;
import java.util.List;

// O -> Open/Close Principles ( OCP )
// class should be open for extension, and closed for modification
// imagine a scenario where you are designing payment gateway
// not a good practice, as we have to keep on modifying entity for all the things
// we have to separate them in a neat manner
public class O {

    void processPayment(String paymentType, Object paymentData) {
        // processing payment
        if (paymentType.equals("CreditCard")) {
        } else if (paymentType.equals("UPI")) {
        } else if (paymentType.equals("BNPL")) {
        }
        // and so on
    }

    boolean validatePayment() {
        // business logic for validation
        return true;
    }

    void acknowledgementSender() {
        // send SMS
        // send email
    }
}

// OCP -> emphasis on abstraction

interface PaymentMethod {
    void paymentProcessor(Object data);

    boolean validate(Object data);
}

// if we need a common acknowledgement service we can implement this class, and use that class to extend
interface AcknowledgementServiceInterface {
    void sendAcknowledgemnt();
}

class EmailService implements AcknowledgementServiceInterface {

    @Override
    public void sendAcknowledgemnt() {

    }
}

class SmsService implements AcknowledgementServiceInterface {

    @Override
    public void sendAcknowledgemnt() {

    }
}

class WhatsAppService implements AcknowledgementServiceInterface {

    @Override
    public void sendAcknowledgemnt() {

    }
}

class MobileAppNotificationService implements AcknowledgementServiceInterface {

    @Override
    public void sendAcknowledgemnt() {

    }
}

// Composite Pattern
class BroadCastingService implements AcknowledgementServiceInterface {

    private final List<AcknowledgementServiceInterface> allAcknowledgementServices;

    BroadCastingService(List<AcknowledgementServiceInterface> allAcknowledgementServices) {
        this.allAcknowledgementServices = allAcknowledgementServices;
    }

    @Override
    public void sendAcknowledgemnt() {
        for (AcknowledgementServiceInterface service : allAcknowledgementServices) {
            service.sendAcknowledgemnt();
        }
    }
}

class CreditCard implements PaymentMethod {

    private final AcknowledgementServiceInterface acknowledgementServiceInterface;

    CreditCard(AcknowledgementServiceInterface acknowledgementServiceInterface) {
        this.acknowledgementServiceInterface = acknowledgementServiceInterface;
    }

    @Override
    public void paymentProcessor(Object data) {
        // business logic
        acknowledgementServiceInterface.sendAcknowledgemnt();
    }

    @Override
    public boolean validate(Object data) {
        return true;
    }
}

class UPI implements PaymentMethod {

    private final AcknowledgementServiceInterface acknowledgementServiceInterface;

    UPI(AcknowledgementServiceInterface acknowledgementServiceInterface) {
        this.acknowledgementServiceInterface = acknowledgementServiceInterface;
    }


    @Override
    public void paymentProcessor(Object data) {
        //business logic
        acknowledgementServiceInterface.sendAcknowledgemnt();
    }

    @Override
    public boolean validate(Object data) {
        return false;
    }
}

class PaymentGateway {

    private final PaymentMethod paymentMethod;

    PaymentGateway(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    void processPayment(Object data) {
        if (paymentMethod.validate(data)) {
            paymentMethod.paymentProcessor(data);
        } else {
            // couldn't validate
        }
    }
}

// this could be improved much further
class PaymentInfra {
    public static void main(String[] args) {
        List<AcknowledgementServiceInterface> allNotifiersForUPI =
                Arrays.asList(new EmailService(), new SmsService(),
                        new WhatsAppService(), new MobileAppNotificationService());
        List<AcknowledgementServiceInterface> allNotifiersForCreditCard =
                Arrays.asList(new EmailService(), new MobileAppNotificationService());


        AcknowledgementServiceInterface upiNotifiers =
                new BroadCastingService(allNotifiersForUPI);
        AcknowledgementServiceInterface creditCardNotifiers =
                new BroadCastingService(allNotifiersForCreditCard);

        PaymentMethod creditCard = new CreditCard(creditCardNotifiers);
        PaymentMethod upi = new UPI(upiNotifiers);

        PaymentGateway paymentGateway=new PaymentGateway(creditCard);
        Object creditCardTransactionData=new Object();
        paymentGateway.processPayment(creditCardTransactionData);

        paymentGateway=new PaymentGateway(upi);
        Object upiTransactionData=new Object();
        paymentGateway.processPayment(upiTransactionData);

    }
}
