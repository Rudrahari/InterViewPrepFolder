package Collections;

public class ComparableExampleClass implements Comparable<ComparableExampleClass> {
    public int totalmarks;

    ComparableExampleClass(int totalMarks) {
        this.totalmarks = totalMarks;
    }

    public int getTotalmarks() {
        return totalmarks;
    }

    public void setTotalmarks(int totalmarks) {
        this.totalmarks = totalmarks;
    }

    @Override
    public String toString() {
        return "ComparableExampleClass{" +
                "totalmarks=" + totalmarks +
                '}';
    }

    @Override
    public int compareTo(ComparableExampleClass obj) {
        System.out.println("Comparable is executed");
        return obj.totalmarks - this.totalmarks;
    }
}
