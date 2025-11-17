package OOP.Principles.Abstraction;

public class University2 extends UniversityAbstractClass {
    public University2(String country, String universityName, String studentName,int age) {
        super(country, universityName, studentName);
        this.age=age;
    }

    @Override
    public void welcomeToUniversity() {
        System.out.println("We are glad to welcome you! " + getUniversityName());
    }

    @Override
    public void congratsForGraduating() {
        System.out.println("Congratulations! " + getStudentName() + ". we are glad to see you go and have a great life ahead of you!");
        System.out.println(getCountry()+ "'s future looks bright with graduates like you");
    }
}
