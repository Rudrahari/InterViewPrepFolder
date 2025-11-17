package OOP.Principles.Abstraction;

public abstract class UniversityAbstractClass {

    private String country;
    private String universityName;
    private String studentName;
    int age;

    public String getCountry() {
        return country;
    }

    public String getUniversityName() {
        return universityName;
    }

    public String getStudentName() {
        return studentName;
    }

    public UniversityAbstractClass(String country, String universityName, String studentName) {
        this.country = country;
        this.universityName = universityName;
        this.studentName = studentName;
    }

    // multiple universities can have different welcome message
    public abstract void welcomeToUniversity();

    // multiple universities can have different graduation message
    public abstract void congratsForGraduating();

    public static void welcome(){
        System.out.println("Welcome");
    }

    public void comeBackAgain(){
        System.out.println("Come back again!");
    }

}
