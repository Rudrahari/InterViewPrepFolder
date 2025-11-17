package OOP.Principles.Inheritance;

public class Inheritance {
    public static void main(String[] args){

        // 1. Inheritance

        // can access all the members of the class unless if it's not private
        RealMadrid realMadrid=new RealMadrid();
        System.out.println(realMadrid.noOfTrainingGrounds+" "+realMadrid.noOfSpanishLeagueTitles);

        // initialize all the values of parent and child
        FootballClub club=
                new RealMadrid("Madrid",23000,5,1);
        // cannot access members of child(RealMadrid) class
        // but child can access member of parent(FootballClub) class
        //System.out.println(club.noOfSpanishLeagueTitles);
        System.out.println(club.location);

        // copy constructor
        // same as above one where we pass all the values in constructor - go through the constructor to know more
        RealMadrid copyClub=new RealMadrid(realMadrid);
        System.out.println(copyClub);

        // Hierarchical inheritance
        // FootballClub - RealMadrid
        //            | - NewYorkJets
        // when the constructor is invoked, it will print the values
        NewYorkJets newYorkJets=new NewYorkJets("Rugby");

        // Multilevel inheritance
        RealMadridYouth realMadridYouth=new RealMadridYouth(copyClub,"U17");
        System.out.println(realMadridYouth);

        // Multiple inheritance - through interface
        // Hybrid inheritance - through interface

    }
}
