package blaplafla.todolist.models.datastructures;

import blaplafla.todolist.models.algorithms.MergeSort;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")

public class SimpleArrayList<T extends Comparable<? super T>>
        implements Serializable, Iterable<T>, SimpleStructure<T> {
    private final MergeSort<T> sort;
    private T[] array;
    private int n;

    public SimpleArrayList() {
        int DEFAULT_SIZE = 100;
        array = (T[]) new Comparable[DEFAULT_SIZE];
        sort = new MergeSort<>(array);
    }

    @Override
    public T[] getArray() {
        return array;
    }

    public int size() {
        return n;
    }

    public int indexOf(T data) {
        if (data == null) {
            for (int i = 0; i < n; i++) {
                if (array[i] == null)
                    return i;
            }
        }
        else if (isContain(data)) {
            for (int i = 0; i < n; i++) {
                if (array[i] != null && array[i].equals(data)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void add(T data) {
        if (array == null) {
            array = (T[]) new Comparable[0];
        }
        if (n >= array.length) {
            array = Arrays.copyOf(array, array.length * 3 / 2);
            sort.setArray(array);
        }
        array[n] = data;
        n++;
    }

    public T get(int i) {
        if (array == null) {
            throw new NullPointerException();
        }
        else if (i < 0 || i > n)
            throw new IndexOutOfBoundsException();
        return array[i];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean isContain(T data) {
        if (array == null) {
            throw new NullPointerException();
        }
        else if (data == null) {
            return false;
        }
        else {
            for (int i = 0; i < n; i++) {
                if (array[i] != null && array[i].equals(data)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void set(int i, T data) {
        if (array == null) {
            throw new NullPointerException();
        }
        if (i < 0 || i > n)
            throw new IndexOutOfBoundsException();
        array[i] = data;
    }

    public void remove(T data) {
        if (array == null) {
            throw new NullPointerException();
        }
        else if (data == null) {
            return;
        }
        for (int i = 0; i < n; i++) {
            if (array[i] != null && array[i].equals(data)) {
                removeIndex(i);
            }
        }
    }

    public void removeIndex(int i) {
        if (array == null) {
            throw new NullPointerException();
        }
        else if (i < 0 || i > n)
            throw new IndexOutOfBoundsException();
        else {
            n--;
            if (n - i >= 0)
                System.arraycopy(array, i + 1, array, i, n - i);
        }
    }

    public Iterator<T> iterator() {
        return new Itr();
    }

    public void sort() {
        sort.sort(n);
    }

    private class Itr
            implements Iterator<T> {
        private int currentIndex;


        public boolean hasNext() {
            return currentIndex < n;
        }


        public T next() {
            return array[currentIndex++];
        }
    }


}
