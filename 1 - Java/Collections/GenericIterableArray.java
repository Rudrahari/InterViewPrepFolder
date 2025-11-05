package Collections;

import java.util.Iterator;

public class GenericIterableArray<T> implements Iterable<T> {
    private T[] dataArray;
    private int index;
    private int size;

    GenericIterableArray() {
        int size = 100;
        dataArray = (T[]) new Object[size];
        index = 0;
        this.size = size;
    }

    GenericIterableArray(int size) {
        dataArray = (T[]) new Object[size];
        index = 0;
        this.size = size;
    }

    void add(T data) {
        dataArray[index++] = data;
    }

    T getItemAtIndex(int idx) {
        return dataArray[idx];
    }

    int getSize() {
        return index;
    }

    @Override
    public Iterator<T> iterator() {
        return new GenericArrayIterator();
    }

    private class GenericArrayIterator implements Iterator<T> {

        int index = 0;

        @Override
        public boolean hasNext() {
            return index < getSize();
        }

        @Override
        public T next() {
            return dataArray[index++];
        }
    }

}
