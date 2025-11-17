package OOP.AccessControl;

public class Access {

    public int publicData; // can be accessed from anywhere

    int defaultData;  // accessible within the same package

    protected int protectedData;  // accessible within the same package and it's child

    private int privateData; // accessible within the class

    public int getPrivateData() {
        return privateData;
    }

    public void setPrivateData(int privateData) {
        this.privateData = privateData;
    }
}
