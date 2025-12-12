package DesignPatterns.BehavioralPatterns;

import java.util.Arrays;
import java.util.List;

interface CityVisitor {
    void visit(NewYork newYork);

    void visit(SaoPaulo saoPaulo);

    void visit(Paris paris);
}

interface City {
    void accept(CityVisitor cityVisitor);
}

class NewYork implements City {

    @Override
    public void accept(CityVisitor cityVisitor) {
        cityVisitor.visit(this);
    }
}

class SaoPaulo implements City {

    @Override
    public void accept(CityVisitor cityVisitor) {
        cityVisitor.visit(this);
    }
}

class Paris implements City {

    @Override
    public void accept(CityVisitor cityVisitor) {
        cityVisitor.visit(this);
    }
}

class VisitorA implements CityVisitor {

    @Override
    public void visit(NewYork newYork) {
        System.out.println("Will visit the most iconic places of new york");
    }

    @Override
    public void visit(SaoPaulo saoPaulo) {
        System.out.println("will checkout the beaches in sao paulo");
    }

    @Override
    public void visit(Paris paris) {
        System.out.println("Eiffel tower is a must visit for me in paris");
    }
}

class VisitorB implements CityVisitor {

    @Override
    public void visit(NewYork newYork) {
        System.out.println("Will visit the most underrated places of new york");
    }

    @Override
    public void visit(SaoPaulo saoPaulo) {
        System.out.println("will checkout the favelas in sao paulo");
    }

    @Override
    public void visit(Paris paris) {
        System.out.println("have to visit all the cafes in paris");
    }
}

class AllCities implements City {

    List<City> allCities;

    AllCities(List<City> allCities) {
        this.allCities = allCities;
    }

    @Override
    public void accept(CityVisitor cityVisitor) {
        for (City city : allCities) {
            city.accept(cityVisitor);
        }
    }
}

public class VisitorPattern {
    public static void main(String[] args) {
        CityVisitor cityVisitorA = new VisitorA();
        CityVisitor cityVisitorB = new VisitorB();
        AllCities allCities =
                new AllCities(
                        Arrays.asList(new SaoPaulo(),
                                new Paris(),
                                new NewYork()));

        allCities.accept(cityVisitorA);
        allCities.accept(cityVisitorB);
    }
}
