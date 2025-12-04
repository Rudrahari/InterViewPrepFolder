package SolidPrinciples;

import java.util.ArrayList;
import java.util.List;

// can be improved further
public class SOLID {
    public static void main(String[] args) {
        List<SwitchFeature> homeDevicesList = new ArrayList<>();
        homeDevicesList.add(new NormalLightBulb());
        homeDevicesList.add(new SmartLightBulb());

        HomeDevices homeDevices = new HomeDevices(homeDevicesList);

        List<NotificationService> notificationServiceList = new ArrayList<>();
        notificationServiceList.add(new Email());
        notificationServiceList.add(new InAppNotification());
        notificationServiceList.add(new SMS());

        BroadCastingNotificationService broadCastingNotificationService =
                new BroadCastingNotificationService(notificationServiceList);

        List<SwitchFeature> amazonHomeSecurityDevicesList = new ArrayList<>();
        amazonHomeSecurityDevicesList.add(new AmazonRoomTemperatureSensor());
        amazonHomeSecurityDevicesList.add(new AmazonMotionSensor(broadCastingNotificationService));

        // just added to turn on, and turn off from here.
        List<SwitchFeature> cromaHomeSecurityDevicesList = new ArrayList<>();
        cromaHomeSecurityDevicesList.add(new CromaRoomTemperatureSensor());
        cromaHomeSecurityDevicesList.add(new CromaMotionSensor(broadCastingNotificationService));

        HomeSecurityDevices amazonHomeSecurityDevices =
                new HomeSecurityDevices(amazonHomeSecurityDevicesList);
        HomeSecurityDevices cromaHomeSecurityDevices =
                new HomeSecurityDevices(cromaHomeSecurityDevicesList);
        SmartHomeApp amazonHomeApp = new AmazonHomeAppliancesApp(homeDevices,
                amazonHomeSecurityDevices);
        SmartHomeApp cromaHomeApp = new CromaHomeAppliancesApp(homeDevices,
                cromaHomeSecurityDevices);
    }
}

interface SwitchFeature {
    void turnOn();

    void turnOff();
}

interface BrightnessFeature extends SwitchFeature {
    void updateBrightness();
}

interface SmartHomeApp {
    void turnOn();

    void turnOff();

    void turnOnAllDevices();

    void turnOnAllHomeDevices();

    void turnOnAllHomeSecurityDevices();

    void turnOffAllDevices();

    void turnOffAllHomeDevices();

    void turnOffAllHomeSecurityDevices();
}

class AmazonHomeAppliancesApp implements SmartHomeApp {

    private final HomeDevices homeDevices;
    private final HomeSecurityDevices homeSecurityDevices;

    AmazonHomeAppliancesApp(HomeDevices homeDevices, HomeSecurityDevices homeSecurityDevices) {
        this.homeDevices = homeDevices;
        this.homeSecurityDevices = homeSecurityDevices;
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }


    @Override
    public void turnOnAllDevices() {
        homeDevices.turnOnAllDevices();
        homeSecurityDevices.turnOnAllDevices();
    }

    @Override
    public void turnOnAllHomeDevices() {
        homeDevices.turnOnAllDevices();
    }

    @Override
    public void turnOnAllHomeSecurityDevices() {
        homeSecurityDevices.turnOnAllDevices();
    }

    @Override
    public void turnOffAllDevices() {
        homeDevices.turnOffAllDevices();
        homeSecurityDevices.turnOffAllDevices();
    }

    @Override
    public void turnOffAllHomeDevices() {
        homeDevices.turnOffAllDevices();
    }

    @Override
    public void turnOffAllHomeSecurityDevices() {
        homeSecurityDevices.turnOffAllDevices();
    }

}

class CromaHomeAppliancesApp implements SmartHomeApp {

    private final HomeDevices homeDevices;
    private final HomeSecurityDevices homeSecurityDevices;

    CromaHomeAppliancesApp(HomeDevices homeDevices, HomeSecurityDevices homeSecurityDevices) {
        this.homeDevices = homeDevices;
        this.homeSecurityDevices = homeSecurityDevices;
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }


    @Override
    public void turnOnAllDevices() {
        homeDevices.turnOnAllDevices();
        homeSecurityDevices.turnOnAllDevices();
    }

    @Override
    public void turnOnAllHomeDevices() {
        homeDevices.turnOnAllDevices();
    }

    @Override
    public void turnOnAllHomeSecurityDevices() {
        homeSecurityDevices.turnOnAllDevices();
    }

    @Override
    public void turnOffAllDevices() {
        homeDevices.turnOffAllDevices();
        homeSecurityDevices.turnOffAllDevices();
    }

    @Override
    public void turnOffAllHomeDevices() {
        homeDevices.turnOffAllDevices();
    }

    @Override
    public void turnOffAllHomeSecurityDevices() {
        homeSecurityDevices.turnOffAllDevices();
    }

}

class NormalFan implements SwitchFeature {

    @Override
    public void turnOn() {
        System.out.println("Turning on the fan");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off the fan");
    }
}

class NormalLightBulb implements SwitchFeature {

    @Override
    public void turnOn() {
        System.out.println("Turning on the light bulb");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning off the light bulb");
    }
}

class SmartLightBulb implements BrightnessFeature {

    @Override
    public void updateBrightness() {

    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }
}

interface NotificationService {
    void sendNotification(Object data);
}

class Email implements NotificationService {

    @Override
    public void sendNotification(Object data) {

    }
}

class SMS implements NotificationService {

    @Override
    public void sendNotification(Object data) {

    }
}

class InAppNotification implements NotificationService {

    @Override
    public void sendNotification(Object data) {

    }
}

class BroadCastingNotificationService {

    private final List<NotificationService> notificationServices;

    BroadCastingNotificationService(List<NotificationService> notificationServices) {
        this.notificationServices = notificationServices;
    }

    void sendNotification() {
        for (NotificationService notificationService : notificationServices) {
            notificationService.sendNotification("Notification");
        }
    }
}

interface TemperatureSensor extends SwitchFeature {
    void displayTemperature();
}

class AmazonRoomTemperatureSensor implements TemperatureSensor {

    @Override
    public void displayTemperature() {
        // calculate temperature and display it
    }

    @Override
    public void turnOn() {
        //turn on
    }

    @Override
    public void turnOff() {
        //turn off
    }
}

class CromaRoomTemperatureSensor implements TemperatureSensor {

    @Override
    public void displayTemperature() {
        // calculate temperature and display it
    }

    @Override
    public void turnOn() {
        //turn on
    }

    @Override
    public void turnOff() {
        //turn off
    }
}

interface MotionSensor extends SwitchFeature {
    void triggerNotification();
}

class AmazonMotionSensor implements MotionSensor {

    final private BroadCastingNotificationService broadCastingNotificationService;

    AmazonMotionSensor(BroadCastingNotificationService broadCastingNotificationService) {
        this.broadCastingNotificationService = broadCastingNotificationService;
    }

    @Override
    public void triggerNotification() {
        broadCastingNotificationService.sendNotification();
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }
}

class CromaMotionSensor implements MotionSensor {

    final private BroadCastingNotificationService broadCastingNotificationService;

    CromaMotionSensor(BroadCastingNotificationService broadCastingNotificationService) {
        this.broadCastingNotificationService = broadCastingNotificationService;
    }

    @Override
    public void triggerNotification() {
        broadCastingNotificationService.sendNotification();
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }
}

class HomeDevices {
    private final List<SwitchFeature> devices;

    HomeDevices(List<SwitchFeature> devices) {
        this.devices = devices;
    }

    void turnOnAllDevices() {
        for (SwitchFeature device : devices) {
            device.turnOn();
        }
    }

    void turnOffAllDevices() {
        for (SwitchFeature device : devices) {
            device.turnOff();
        }
    }
}

class HomeSecurityDevices {
    private final List<SwitchFeature> securityDevices;

    HomeSecurityDevices(List<SwitchFeature> securityDevices) {
        this.securityDevices = securityDevices;
    }

    void turnOnAllDevices() {
        for (SwitchFeature device : securityDevices) {
            device.turnOn();
        }
    }

    void turnOffAllDevices() {
        for (SwitchFeature device : securityDevices) {
            device.turnOff();
        }
    }
}



