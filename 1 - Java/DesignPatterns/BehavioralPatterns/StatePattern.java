package DesignPatterns.BehavioralPatterns;

// State Interface
interface HumanState {
    void currentHumanState();

    void nextHumanState();
}

class Foetus implements HumanState {

    Human human;

    public Foetus(Human human) {
        this.human = human;
    }

    @Override
    public void currentHumanState() {
        System.out.println("I am just a foetus now");
    }

    @Override
    public void nextHumanState() {
        human.setNextHumanState(human.getInfantState());
    }
}

class Infant implements HumanState {

    Human human;

    public Infant(Human human) {
        this.human = human;
    }

    @Override
    public void currentHumanState() {
        System.out.println("I am just an infant now");
    }

    @Override
    public void nextHumanState() {
        human.setNextHumanState(human.getChildState());
    }
}

class Child implements HumanState {

    Human human;

    public Child(Human human) {
        this.human = human;
    }

    @Override
    public void currentHumanState() {
        System.out.println("I am just a child now");
    }

    @Override
    public void nextHumanState() {
        human.setNextHumanState(human.getAdolescenceState());
    }
}

class Adolesence implements HumanState {

    Human human;

    public Adolesence(Human human) {
        this.human = human;
    }

    @Override
    public void currentHumanState() {
        System.out.println("I am just in my adolescence");
    }

    @Override
    public void nextHumanState() {
        human.setNextHumanState(human.getYoungAdultState());
    }
}

class YoungAdult implements HumanState {

    Human human;

    public YoungAdult(Human human) {
        this.human = human;
    }

    @Override
    public void currentHumanState() {
        System.out.println("I am considered young adult now, time goes by fast");
    }

    @Override
    public void nextHumanState() {
        human.setNextHumanState(human.getMiddleAdultState());
    }
}

class MiddleAdult implements HumanState {

    Human human;

    public MiddleAdult(Human human) {
        this.human = human;
    }

    @Override
    public void currentHumanState() {
        System.out.println("I am in my middle age(adult)");
    }

    @Override
    public void nextHumanState() {
        human.setNextHumanState(human.getOldAgState());
    }
}

class OldAge implements HumanState {

    Human human;

    public OldAge(Human human) {
        this.human = human;
    }

    @Override
    public void currentHumanState() {
        System.out.println("I am in my old age now");
    }

    @Override
    public void nextHumanState() {
        human.setNextHumanState(human.getDeathState());
    }
}

class Death implements HumanState {

    Human human;

    public Death(Human human) {
        this.human = human;
    }

    @Override
    public void currentHumanState() {
        System.out.println("I am dead now");
    }

    @Override
    public void nextHumanState() {
        System.out.println("No further human stages");
    }
}


class Human {
    private HumanState currentState;

    private HumanState foetusState;
    private HumanState infantState;
    private HumanState childState;
    private HumanState adolescenceState;
    private HumanState youngAdultState;
    private HumanState middleAdultState;
    private HumanState oldAgState;
    private HumanState deathState;

    Human() {
        this.foetusState = new Foetus(this);
        this.infantState = new Infant(this);
        this.childState = new Child(this);
        this.adolescenceState = new Adolesence(this);
        this.youngAdultState = new YoungAdult(this);
        this.middleAdultState = new MiddleAdult(this);
        this.oldAgState = new OldAge(this);
        this.deathState = new Death(this);

        this.currentState = foetusState;
    }

    void currentHumanState() {
        currentState.currentHumanState();
    }

    void nextHumanState() {
        currentState.nextHumanState();
    }

    void setNextHumanState(HumanState humanState) {
        this.currentState = humanState;
    }

    public HumanState getFoetusState() {
        return foetusState;
    }

    public HumanState getInfantState() {
        return infantState;
    }

    public HumanState getChildState() {
        return childState;
    }

    public HumanState getAdolescenceState() {
        return adolescenceState;
    }

    public HumanState getYoungAdultState() {
        return youngAdultState;
    }

    public HumanState getMiddleAdultState() {
        return middleAdultState;
    }

    public HumanState getOldAgState() {
        return oldAgState;
    }

    public HumanState getDeathState() {
        return deathState;
    }
}

public class StatePattern {
    public static void main(String[] args) {
        Human human = new Human();

        human.currentHumanState();
        human.nextHumanState();

        human.currentHumanState();
        human.nextHumanState();

        human.currentHumanState();
        human.nextHumanState();

        human.currentHumanState();
        human.nextHumanState();

        human.currentHumanState();
        human.nextHumanState();

        human.currentHumanState();
        human.nextHumanState();

        human.currentHumanState();
        human.nextHumanState();

        human.currentHumanState();
        human.nextHumanState();
    }
}
