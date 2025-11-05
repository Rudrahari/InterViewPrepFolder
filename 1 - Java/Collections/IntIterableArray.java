package Collections;

import java.util.Iterator;

public class IntIterableArray implements Iterable<Integer> {

    private int[] data;
    private int size;
    private int index;

    IntIterableArray() {
        int size = 100;
        data = new int[size];
        this.index = 0;
        this.size = size;
    }

    IntIterableArray(int size) {
        this.data = new int[size];
        this.index = 0;
        this.size = size;
    }

    void add(int val) {
        this.data[index++] = val;
    }

    int getItemAtIndex(int idx) {
        return data[idx];
    }

    int getSize() {
        return index;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IntArrayIterator();
    }

    private class IntArrayIterator implements Iterator<Integer> {

        int index = 0;

        @Override
        public boolean hasNext() {
            return index < getSize();
        }

        @Override
        public Integer next() {
            return data[index++];
        }
    }
}
