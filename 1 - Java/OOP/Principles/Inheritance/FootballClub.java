package OOP.Principles.Inheritance;

public class FootballClub {
    String location;
    int stadiumCapacity;
    int noOfTrainingGrounds;
    // assume as normal football, can be changed in subclass for american(rugby)
    String type="European";

    FootballClub(){
        this.location=" ";
        this.stadiumCapacity=-1;
        this.noOfTrainingGrounds=-1;
    }

    FootballClub(String location){
        this.location=location;
        this.stadiumCapacity=-1;
        this.noOfTrainingGrounds=-1;
    }

    FootballClub(String location,int stadiumCapacity,int noOfTrainingGrounds){
        this.location=location;
        this.stadiumCapacity=stadiumCapacity;
        this.noOfTrainingGrounds=noOfTrainingGrounds;
    }

    // Copy Constructor
    FootballClub(FootballClub oldObj){
        this.location= oldObj.location;
        this.stadiumCapacity= oldObj.stadiumCapacity;
        this.noOfTrainingGrounds=oldObj.noOfTrainingGrounds;
    }

    @Override
    public String toString() {
        return "FootballClub{" +
                "location='" + location + '\'' +
                ", stadiumCapacity=" + stadiumCapacity +
                ", noOfTrainingGrounds=" + noOfTrainingGrounds +
                ", type='" + type + '\'' +
                '}';
    }
}
