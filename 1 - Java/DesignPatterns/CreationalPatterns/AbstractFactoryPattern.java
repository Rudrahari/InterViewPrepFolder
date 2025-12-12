package DesignPatterns.CreationalPatterns;

// abstract product
interface CloudStorage {
    void connectToCloud();

    void getDataFromCloud();
}

// concrete product
class AwsCloudStorage implements CloudStorage {

    @Override
    public void connectToCloud() {

    }

    @Override
    public void getDataFromCloud() {

    }
}

// concrete product
class AzureCloudStorage implements CloudStorage {

    @Override
    public void connectToCloud() {

    }

    @Override
    public void getDataFromCloud() {

    }
}

// abstract product
interface CloudComputing {
    void connectToRemoteServer();

    void processData();
}

// concrete product
class AwsCloudComputing implements CloudComputing {

    @Override
    public void connectToRemoteServer() {

    }

    @Override
    public void processData() {

    }
}

// concrete product
class AzureCloudComputing implements CloudComputing {

    @Override
    public void connectToRemoteServer() {

    }

    @Override
    public void processData() {

    }
}

// abstract factory
interface CloudServiceProvider {
    CloudStorage getCloudStorage();

    CloudComputing getCloudComputing();
}

// concrete factory
class AwsCloudServiceProvider implements CloudServiceProvider {

    @Override
    public CloudStorage getCloudStorage() {
        return new AwsCloudStorage();
    }

    @Override
    public CloudComputing getCloudComputing() {
        return new AwsCloudComputing();
    }
}

// concrete factory
class AzureCloudServiceProvider implements CloudServiceProvider {

    @Override
    public CloudStorage getCloudStorage() {
        return new AzureCloudStorage();
    }

    @Override
    public CloudComputing getCloudComputing() {
        return new AzureCloudComputing();
    }
}

class CloudApp {
    private CloudStorage cloudStorage;
    private CloudComputing cloudComputing;

    CloudApp(CloudServiceProvider cloudServiceProvider) {
        this.cloudComputing = cloudServiceProvider.getCloudComputing();
        this.cloudStorage = cloudServiceProvider.getCloudStorage();
    }

    public void startJob() {
        cloudStorage.connectToCloud();
        cloudStorage.getDataFromCloud();

        cloudComputing.connectToRemoteServer();
        cloudComputing.processData();
    }
}
// abstract factory pattern helpful in creating a families of related objects, without mentioning the underlying concrete classes.
public class AbstractFactoryPattern {
    public static void main(String[] args) {
        CloudServiceProvider cloudServiceProvider = new AwsCloudServiceProvider();

        CloudApp cloudApp = new CloudApp(cloudServiceProvider);
        cloudApp.startJob();

        cloudServiceProvider = new AzureCloudServiceProvider();

        cloudApp = new CloudApp(cloudServiceProvider);
        cloudApp.startJob();
    }
}
