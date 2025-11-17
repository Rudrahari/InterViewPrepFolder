package OOP.Principles.Abstraction;

public interface CommonWealthGames {
    void partOfCommomWealthGames();
    default void haveFun(){
        System.out.println("Have fun in the common wealth games");
    }
}
