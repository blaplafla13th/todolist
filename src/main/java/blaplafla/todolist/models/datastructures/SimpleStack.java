package blaplafla.todolist.models.datastructures;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;


@SuppressWarnings("unchecked")
public class SimpleStack<T extends Comparable<? super T>>
        implements Serializable, Iterable<T>, SimpleStructure<T> {
    private T[] array;
    private int n;

    public SimpleStack() {
        array = (T[]) new Comparable[50];
    }

    @Override
    public T[] getArray() {
        return array;
    }

    public synchronized int size() {
        return n;
    }

    public synchronized int indexOf(T data) {
        if (data == null) {
            for (int i = n - 1; i >= 0; i--) {
                if (array[i] == null)
                    return i;
            }
        }
        else if (isContain(data)) {
            for (int i = n - 1; i >= 0; i--) {
                if (array[i] != null && array[i].equals(data)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public synchronized void add(T data) {
        if (array == null) {
            array = (T[]) new Comparable[0];
        }
        if (n >= array.length) {
            array = Arrays.copyOf(array, array.length * 3 / 2);
        }
        array[n] = data;
        n++;
    }

    public synchronized T get(int i) {
        if (array == null) {
            throw new NullPointerException();
        }
        else if (i < 0 || i > n)
            throw new IndexOutOfBoundsException();
        return array[n - 1 - i];
    }

    public synchronized boolean isEmpty() {
        return n == 0;
    }

    public synchronized boolean isContain(T data) {
        if (array == null) {
            throw new NullPointerException();
        }
        else if (data == null) {
            return false;
        }
        else {
            for (int i = n - 1; i >= 0; i--) {
                if (array[i] != null && array[i].equals(data)) {
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized T peek() {
        if (array == null) {
            throw new NullPointerException();
        }
        else if (n <= 0)
            return null;
        return array[n - 1];
    }

    public synchronized T pop() {
        if (array == null) {
            throw new NullPointerException();
        }
        else if (n <= 0)
            return null;
        T temp = array[n - 1];
        n--;
        return temp;
    }

    public synchronized void remove(T data) {
        if (array == null) {
            throw new NullPointerException();
        }
        else if (data == null) {
            return;
        }
        for (int i = n - 1; i >= 0; i--) {
            if (array[i] != null && array[i].equals(data)) {
                removeIndex(n - 1 - i);
            }
        }
    }

    public synchronized void removeIndex(int i) {
        i = n - 1 - i;
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

    public synchronized Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr
            implements Iterator<T> {
        private int currentIndex = n - 1;


        public boolean hasNext() {
            return currentIndex >= 0;
        }


        public T next() {
            return array[currentIndex--];
        }
    }
}
