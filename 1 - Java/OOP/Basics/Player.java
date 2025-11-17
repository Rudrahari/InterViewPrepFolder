package OOP.Basics;

public class Player {
    // always have to initialize the value for final variables
    static final int century = 21;
    static long totalPlayers;
    int jerseyNo;
    String team;
    String sport;

    Player() {
        // calling another constructor from a constructor
        this(-1, "NIL", "NIL");
        // updating player count
        Player.totalPlayers++;
    }

    // invoked automatically when a object is created
    Player(int jerseyNo, String team, String sport) {
        //binding provided arguments with instance variable of this class
        // this points to current object
        this.jerseyNo = jerseyNo;
        this.sport = sport;
        this.team = team;
        // the below code will not assign the value to the instance variable
        // as variable inside method have more precedence , it's just assigning value to itself
        // team=team;

        //updating player count
        Player.totalPlayers++;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Destroyed");
    }
}
