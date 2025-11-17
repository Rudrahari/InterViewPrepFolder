package OOP.Principles.Inheritance;

public class NewYorkJets extends FootballClub{

    String type;

    NewYorkJets(){
        System.out.println(super.type);
    }

    NewYorkJets(String type){
        // example for when we have same variable name distinguish with super keyword
        this.type=type;
        System.out.println(super.type);
        System.out.println(this.type);
    }
}
