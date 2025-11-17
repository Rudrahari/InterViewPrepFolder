package OOP.AccessControl.SubPackage;

import OOP.AccessControl.Access;

public class AccessSubPackage {
    public static void main(String[] args) {
        Access access = new Access();

        // not possible as it is not in the same package and not a sub class
        // System.out.println(access.protectedData);

        // not possible as we are trying to access from a different package
        // System.out.println(access.defaultData);

        // public is accessible
        System.out.println(access.publicData);

    }
}

class AccessSubPackageInheritance extends Access{
    public static void main(String[] args) {
        AccessSubPackageInheritance access=new AccessSubPackageInheritance();

        // accessible through subclass in different package
        System.out.println(access.protectedData);

        // same cannot be possible with below declaration
        Access access1=new AccessSubPackageInheritance();

        // compile time above obj references to the parent class
        // in the run time we have object created for it
        // Parent have no way of knowing it's child
        // System.out.println(access1.protectedData);
    }
}