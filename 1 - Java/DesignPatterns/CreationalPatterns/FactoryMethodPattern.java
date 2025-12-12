package DesignPatterns.CreationalPatterns;

interface Player {
    void play();
}

class FootballPlayer implements Player {

    @Override
    public void play() {
        System.out.println("Plays football");
    }
}

class RugbyPlayer implements Player {

    @Override
    public void play() {
        System.out.println("Plays rugby");
    }
}

interface PlayerFactory {
    Player getPlayer();
}

abstract class Match {
    abstract Player getPlayer();

    void matchStarts() {
        Player player = getPlayer();
        System.out.println("Match Starts");
        player.play();
    }
}

class FootballMatch extends Match {
    @Override
    Player getPlayer() {
        return new FootballPlayer();
    }
}

class RugbyMatch extends Match {

    @Override
    Player getPlayer() {
        return new RugbyPlayer();
    }
}

class FootballPlayerFactory implements PlayerFactory {

    @Override
    public Player getPlayer() {
        return new FootballPlayer();
    }
}

class RugbyPlayerFactory implements PlayerFactory {

    @Override
    public Player getPlayer() {
        return new RugbyPlayer();
    }
}

public class FactoryMethodPattern {
    public static void main(String[] args) {
        // instead of direct instantiation, we make use of a factory class
        PlayerFactory playerFactory = new FootballPlayerFactory();
        Player player = playerFactory.getPlayer();
        player.play();

        playerFactory = new RugbyPlayerFactory();
        player = playerFactory.getPlayer();
        player.play();

        // using abstract factory class
        Match match =
                new FootballMatch();
        match.matchStarts();
        match =
                new RugbyMatch();
        match.matchStarts();

    }
}
