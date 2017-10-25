package IteratorIterable;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Iterator;

/**
 * Created by kingfernandez on 10/24/17.
 */

public class SampleIterable  implements Iterable<Integer> {

    int currIndex = 0;
    int arr[];

    public SampleIterable(int maxCap) {
        this.arr = new int[maxCap];
    }

    public void addItem(int newVal) {
        arr[currIndex++] = newVal;
    }

    @Override
    public Iterator iterator() {
        return new SomeIterator();
    }

    private class SomeIterator implements Iterator {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < arr.length;
        }

        @Override
        public Object next() {
            return arr[cursor++];
        }

        @Override
        public void remove() {
            throw new NotImplementedException();
        }
    }

}

