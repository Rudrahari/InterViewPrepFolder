package OOP.Principles.Abstraction;

public class TrackEvents implements Olympics,CommonWealthGames{
    @Override
    public void partOfCommomWealthGames() {
        System.out.println("TrackEvents is part of common wealth games");
    }

    @Override
    public void partOfOlympics() {
        System.out.println("TrackEvents is part of olympics");
    }

    @Override
    public void haveFun() {
        Olympics.super.haveFun();
    }
}
