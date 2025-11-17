package OOP.Principles.Abstraction;

public interface Olympics {
    void partOfOlympics();
    default void haveFun(){
        System.out.println("Have fun in the olympics");
    }
}
