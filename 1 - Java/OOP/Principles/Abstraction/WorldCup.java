package OOP.Principles.Abstraction;

public interface WorldCup {
    void hasItsOwnWorldCup();
    default void haveFun(){
        System.out.println("Have fun in the worldcup");
    }
}
