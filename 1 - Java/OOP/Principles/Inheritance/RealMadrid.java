package OOP.Principles.Inheritance;

public class RealMadrid extends FootballClub {

    int noOfSpanishLeagueTitles;

    RealMadrid(){
        // by default when super() in not called in any child constructor
        //  Parent() constructor will be called
        this.noOfSpanishLeagueTitles=-1;
        // super(); should be initialized in the first line of child constructor
        // parent should be initialized before child
    }

    RealMadrid(int noOfSpanishLeagueTitles){
        this.noOfTrainingGrounds=noOfSpanishLeagueTitles;
        // can access parent class member
        // can use both this and super for pointing to
        // when name of variable is same in parent and child, explicitly use super for accessing
        this.location="NIL";
        super.location="NIL";
        // refer other example for more detail
    }

    RealMadrid(String location, int stadiumCapacity, int noOfTrainingGrounds, int noOfSpanishLeagueTitles){
        // to initialize parent class members
        super(location, stadiumCapacity, noOfTrainingGrounds);
        this.noOfSpanishLeagueTitles=noOfSpanishLeagueTitles;
    }

    RealMadrid(RealMadrid oldMadrid){
        // this will refer to the copy constructor of the parent -FootballClub(FootballClub oldObj)
        // why this works? as we know child have all the properties of parent, it can be upcasted
        super(oldMadrid);
        this.noOfSpanishLeagueTitles= oldMadrid.noOfSpanishLeagueTitles;
    }

    @Override
    public String toString() {
        return "RealMadrid{" +
                "noOfSpanishLeagueTitles=" + noOfSpanishLeagueTitles +
                ", location='" + location + '\'' +
                ", stadiumCapacity=" + stadiumCapacity +
                ", noOfTrainingGrounds=" + noOfTrainingGrounds +
                ", type='" + type + '\'' +
                '}';
    }
}
