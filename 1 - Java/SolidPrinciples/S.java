package SolidPrinciples;

import java.util.UUID;

// S -> Single Responsibility Principle  (  SRP  )
// A class should have only one reason to change, anything else is a violation
// Imagine this scenario, where we register user,
// where we send email, sms acknowledgment,
// store user data in S3
public class S {

    // You can see there are four reason for a class to change it's behavior
    // this violates -> single responsibility principle

    // registers user it's the main responsibility
    void registerUser(S data) {
        // business logic
    }

    // what if we decided to send sms to whatsapp
    void sendAcknowledgementSms(String phoneNo) {
        // business logic
    }

    // what if the email service provider is changed
    void sendAcknowledgementEmail(String email) {
        // business logic
    }

    // what if we changed to a different cloud provider this logic will change
    // not a right practice to have it
    void storeDataInS3(S data) {
        // business logic
    }
}

// Good practice
// now every class have one reason to change

class UserService {

    private final AcknowledgementService acknowledgementService;
    private final CloudService cloudService;
    private final UserRepository userRepository;

    public UserService(AcknowledgementService acknowledgementService,
                       CloudService cloudService, UserRepository userRepository) {
        this.acknowledgementService = acknowledgementService;
        this.cloudService = cloudService;
        this.userRepository = userRepository;
    }

    void registerUser() {
        // register user business logic
        acknowledgementService.sendNotification();
        cloudService.sendDataToCloud();
        userRepository.save();
    }
}

class AcknowledgementService {
    private final EmailServiceClass emailServiceClass;
    private final SmsServiceClass smsServiceClass;


    AcknowledgementService(EmailServiceClass emailServiceClass, SmsServiceClass smsServiceClass) {
        this.emailServiceClass = emailServiceClass;
        this.smsServiceClass = smsServiceClass;
    }

    void sendNotification() {
        emailServiceClass.sendEmail();
        smsServiceClass.sendSms();
    }
}

class EmailServiceClass {
    void sendEmail() {
    }
}

class SmsServiceClass {
    void sendSms() {
    }
}

// can be aws azure
class CloudService {
    void sendDataToCloud() {
    }
}

interface UserRepository {
    void save();
}


