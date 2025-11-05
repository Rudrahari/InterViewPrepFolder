package Collections;

import java.util.Objects;

public class SetCustomClassExample {
    private int totalMarks;

    SetCustomClassExample(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    // Needed for comparing set objects
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SetCustomClassExample that = (SetCustomClassExample) o;
        System.out.println("Comparison takes place for: " + totalMarks + " and: " + that.totalMarks);
        return totalMarks == that.totalMarks;
    }

    @Override
    public int hashCode() {
        int hashcode = Objects.hashCode(totalMarks);
        System.out.println("HashCode called here for: " + totalMarks + " and it's hashcode: " + hashcode);
        return hashcode;
    }

    @Override
    public String toString() {
        return "SetCustomClassExample{" +
                "totalMarks=" + totalMarks +
                '}';
    }
}
