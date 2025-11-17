package OOP.Principles.Polymorphism;

public class City {
    String city = "City";

    // final keyword to restrict child from overriding method, same can be used for class
//    final void welcome(){
//        System.out.println("Welcome to City");
//    }
    void welcome() {
        System.out.println("Welcome to City");
    }
    static void comeBackAgain(){
        System.out.println("Comeback to our city");
    }
}
