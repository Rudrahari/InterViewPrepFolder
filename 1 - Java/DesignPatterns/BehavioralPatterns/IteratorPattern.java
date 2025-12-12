package DesignPatterns.BehavioralPatterns;

import java.util.ArrayList;
import java.util.List;

interface Iterator<T> {
    boolean hasNext();

    T next() throws Exception;
}

interface ElementsContainer<T> {
    Iterator<T> getIterator();
}

// Iterator hides the internal mechanism of number collections
class NumberCollections implements ElementsContainer<Integer> {

    private List<Integer> allIntegers = new ArrayList<>();

    public void addNumber(Integer data) {
        allIntegers.add(data);
    }

    @Override
    public Iterator<Integer> getIterator() {
        return new IntegerIterator();
    }

    private class IntegerIterator implements Iterator<Integer> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < allIntegers.size();
        }

        @Override
        public Integer next() throws Exception {
            if (!hasNext()) {
                throw new Exception();
            }
            return allIntegers.get(index++);
        }
    }
}

public class IteratorPattern {
    public static void main(String[] args) throws Exception {
        NumberCollections numberCollections = new NumberCollections();
        numberCollections.addNumber(12);
        numberCollections.addNumber(90);
        numberCollections.addNumber(100);

        Iterator<Integer> iterator = numberCollections.getIterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
