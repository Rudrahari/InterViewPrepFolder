package OOP.Principles.Polymorphism;

public class NewYork extends City{
    String city="NewYork";

    @Override
    void welcome(){
        System.out.println("Welcome to the financial capital of the world");
    }
    static void comeBackAgain(){
        System.out.println("Comeback to New York City");
    }

    @Override
    public String toString() {
        return "NewYork{" +
                "city='" + city + '\'' +
                '}';
    }
}
