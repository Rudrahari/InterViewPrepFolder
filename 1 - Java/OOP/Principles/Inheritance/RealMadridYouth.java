package OOP.Principles.Inheritance;

public class RealMadridYouth extends RealMadrid{
    String ageGroup;

    RealMadridYouth(){
        super.stadiumCapacity=-1;
        ageGroup="NIL";
    }

    RealMadridYouth(String ageGroup){
        super.stadiumCapacity=-1;
        this.ageGroup=ageGroup;
    }

    public RealMadridYouth(RealMadrid oldMadrid, String ageGroup) {
        super(oldMadrid);
        super.stadiumCapacity=-1;
        this.ageGroup = ageGroup;
    }

    @Override
    public String toString() {
        return "RealMadridYouth{" +
                "ageGroup='" + ageGroup + '\'' +
                ", noOfSpanishLeagueTitles=" + noOfSpanishLeagueTitles +
                ", location='" + location + '\'' +
                ", stadiumCapacity=" + stadiumCapacity +
                ", noOfTrainingGrounds=" + noOfTrainingGrounds +
                ", type='" + type + '\'' +
                '}';
    }
}
