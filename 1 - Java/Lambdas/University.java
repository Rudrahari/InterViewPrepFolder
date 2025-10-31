public class University {
    private String universityName;
    private int studentCount;

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public University(String universityName,int studentCount) {
        this.universityName = universityName;
        this.studentCount = studentCount;
    }

    @Override
    public String toString() {
        return "University{" +
                "universityName='" + universityName + '\'' +
                ", studentCount=" + studentCount +
                '}';
    }
}
