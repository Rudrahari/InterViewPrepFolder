package DesignPatterns.BehavioralPatterns;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void receiveUpdates(String techEvent);
}

interface Subject {
    void subscribe(Observer observer);

    void unSubscribe(Observer observer);

    void notifyObservers(String data);
}

class TechnologyClub implements Subject {

    List<Observer> observerList;

    TechnologyClub() {
        observerList = new ArrayList<>();
    }

    @Override
    public void subscribe(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unSubscribe(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String data) {
        for (Observer observer : observerList) {
            observer.receiveUpdates(data);
        }
    }

    public void addNewTechEvent(String eventName) {
        notifyObservers(eventName);
    }
}

class CommunityMember implements Observer {

    String memberName;

    public CommunityMember(String memberName) {
        this.memberName = memberName;
    }

    @Override
    public void receiveUpdates(String techEvent) {
        System.out.println("Hello! " + memberName + " Community have a new event: " + techEvent);
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        TechnologyClub techClub = new TechnologyClub();

        Observer memberA = new CommunityMember("memberA");
        Observer memberB = new CommunityMember("memberB");
        Observer memberC = new CommunityMember("memberC");
        techClub.subscribe(memberA);
        techClub.subscribe(memberB);
        techClub.subscribe(memberC);

        techClub.addNewTechEvent("Event 1");
    }
}
