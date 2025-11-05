package Collections;

import java.util.Comparator;

public class ComparatorExampleClass implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        System.out.println("Comparator is executed");
        return o2 - o1;
    }
}
