package OOP.Principles.Polymorphism;

public class SanFrancisco extends City{
    String city="SanFrancisco";

    static void comeBackAgain(){
        System.out.println("Comeback to Sanfrancisco city");
    }

    @Override
    void welcome(){
        System.out.println("Welcome to the technological capital of the world");
    }
}
