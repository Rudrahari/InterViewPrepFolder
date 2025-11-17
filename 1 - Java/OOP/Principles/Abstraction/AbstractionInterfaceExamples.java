package OOP.Principles.Abstraction;

public class AbstractionInterfaceExamples {
    public static void main(String[] args) {
        // abstract class
        UniversityAbstractClass abstractClass =
                new University1("USA", "MIT", "Mark", 24);
        UniversityAbstractClass.welcome(); // static method in parent
        abstractClass.welcomeToUniversity();
        abstractClass.congratsForGraduating();
        System.out.println(abstractClass.age);

        abstractClass =
                new University2("UK", "Cambridge", "Moritz", 28);
        abstractClass.welcomeToUniversity();
        abstractClass.congratsForGraduating();
        System.out.println(abstractClass.age);
        abstractClass.comeBackAgain(); // not static method in parent

        // abstract,interface cannot be used for creating instances
        // abstract doesn't support multiple inheritance
        // UniversityAbstractClass obj=new UniversityAbstractClass();

        // interface
        Football football = new Football();
        football.hasItsOwnWorldCup();
        football.partOfOlympics();
        football.haveFun();

        TrackEvents trackEvents = new TrackEvents();
        trackEvents.partOfOlympics();
        trackEvents.partOfCommomWealthGames();

        Olympics track=new TrackEvents();
        track.partOfOlympics();
        //  not possible, we know Olympics doesn't have that method
        //track.partOfCommomWealthGames();

        WorldCup cricket = new Cricket();
        cricket.hasItsOwnWorldCup();
    }
}
