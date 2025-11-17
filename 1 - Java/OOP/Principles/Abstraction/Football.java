package OOP.Principles.Abstraction;

public class Football implements WorldCup,Olympics{
    @Override
    public void partOfOlympics() {
        System.out.println("Football is part of olympics");
    }

    @Override
    public void hasItsOwnWorldCup() {
        System.out.println("Football has it's own organised world cup event");
    }

    @Override
    public void haveFun() {
        // you could use super to call one of the conflicting defaults
        WorldCup.super.haveFun();
        Olympics.super.haveFun();
        // can also have our custom implementation
        System.out.println("Have with football games in world cup, and olympics");
    }
}
