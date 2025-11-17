package OOP.Principles.Abstraction;

public class University1 extends UniversityAbstractClass {
    public University1(String country, String universityName, String studentName,int age) {
        super(country, universityName, studentName);
        this.age=age;
    }

    @Override
    public void welcomeToUniversity() {
        System.out.println("Welcome to " + getUniversityName());
    }

    @Override
    public void congratsForGraduating() {
        System.out.println("Congratulations for graduating! "+getStudentName());
        System.out.println(getCountry()+ " is lucky to have you as a contributor");
    }
}
