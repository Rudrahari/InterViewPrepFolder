package OOP.AccessControl;

public class AccessControlExamples {
    public static void main(String[] args) {
        Access access = new Access();
        // access.privateData; not possible as it's scope is within the same class
        // System.out.println(access.privateData);
        // accessible through a controlled medium( getter, setter)
        System.out.println(access.getPrivateData());

        // accessible as it is public
        System.out.println(access.publicData);

        // accessible as it is in the same package
        System.out.println(access.protectedData);

        // accessible as it is in the same package
        System.out.println(access.defaultData);
    }
}
