package OOP.Principles.Polymorphism;

public class PolyMorphism {
    public static int addNums(int a, int b) {
        return a + b;
    }

    public static int addNums(int a, int b, int c) {
        return a + b + c;
    }

    public static void addNums(int a) {
        System.out.println("Num: " + a);
    }

    public static void addNums(double a) {
        System.out.println("Num: " + a);
    }

    public static void main(String[] args) {

        // Polymorphism
        // Types: Compile Time/Static -> Method Overloading
        //      : Runtime/Dynamic -> Method Overriding

        // Method Overloading
        // Early Binding/Static Binding - method call is resolved at compile time
        // static methods, final method, method overloading, private methods are early binded
        int a = addNums(1, 2);
        a = addNums(1, 3, 2);
        addNums(0);
        addNums(9.0);


        // Method Overriding
        // Late Binding/Dynamic Binding - method call is resolved at runtime
        City city = new NewYork();
        city.welcome();
        // early binding, as static belows to class not instance
        //at compile time it is resolved which method should be invoked
        city.comeBackAgain(); // will not work same as above line of code

        city = new SanFrancisco();
        city.welcome();

        // Method overriding similar with inbuilt Object class
        // Our class doesn't implement toString, so it will use the inbuilt object class toString
        System.out.println(city);

        // lets override toString
        // now child class toString method is overrided
        // JVM uses dynamic method dispatch to determine which version of method to use
        city = new NewYork();
        System.out.println(city);

        Object obj=new City();
        System.out.println(obj); // Object class toString is invoked

        NewYork newYork = new NewYork();
        // it depends on reference type, as it is compile time
        // variable shadowing happens only if both reference type and object creation is child
        System.out.println(newYork.city);
        newYork.comeBackAgain();
        // if parent is referred, parent variable is called
        System.out.println(city.city);


    }
}
