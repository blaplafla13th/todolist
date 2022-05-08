package blaplafla.todolist.Modals;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("unchecked")

public class SimpleArrayList<T extends Comparable<? super T>> implements Serializable, Iterable<T> {
    private transient T[] array;
    private MergeSort<T> sort;
    private int n = 0;

    public SimpleArrayList() {
        int DEFAULT_SIZE = 100;
        array = (T[]) new Comparable[DEFAULT_SIZE];
        sort = new MergeSort<>(array);
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
        } else if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        return array[i];
    }


    public void set(int i, T data) {
        if (array == null) {
            throw new NullPointerException();
        }
        if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        array[i] = data;
    }

    public void remove(T data) {
        int index = 0;
        T[] newArray = (T[]) new Comparable[n];
        if (array == null) {
            throw new NullPointerException();
        } else if (data == null) {
            for (int i = 0; i < n; i++) {
                if (array[i] == null) {
                    n--;
                    for (int j = 0; j < n + 1; j++) {
                        if (i != j) {
                            newArray[j] = array[i];
                        }
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (array[i] != null && array[i].equals(data)) {
                n--;
                for (int j = 0; j < n + 1; j++) {
                    if (i != j) {
                        newArray[j] = array[i];
                    }
                }
                break;
            }
        }
        array = newArray;
    }


    public boolean isContain(T data) {
        if (array == null) {
            throw new NullPointerException();
        } else if (data == null) {
            for (int i = 0; i < n; i++) {
                if (array[i] == null) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (array[i] != null && array[i].equals(data)) {
                    return true;
                }
            }
        }
        return false;
    }


    public int indexOf(T data) {
        if (data == null) {
            for (int i = 0; i < n; i++) {
                if (array[i] == null) return i;
            }
        } else if (isContain(data)) {
            for (int i = 0; i < n; i++) {
                if (array[i] != null && array[i].equals(data)) {
                    return i;
                }
            }
        }
        return -1;
    }


    public int size() {
        return n;
    }


    public boolean isEmpty() {
        return n == 0;
    }


    public Iterator<T> iterator() {
        return new Itr();
    }

    public Iterator<T> invertIterator() {
        return new InvItr();
    }

    public void removeIndex(int i) {
        if (array == null) {
            throw new NullPointerException();
        } else if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        else {
            n--;
            if (n - i >= 0) System.arraycopy(array, i + 1, array, i, n - 1 - i);
        }
    }

    public void sort() {
        sort.sort(n);
    }

    private class Itr implements Iterator<T> {
        private int currentIndex = 0;


        public boolean hasNext() {
            return currentIndex < n;
        }


        public T next() {
            return array[currentIndex++];
        }
    }

    private class InvItr implements Iterator<T> {
        private int currentIndex = n - 1;


        public boolean hasNext() {
            return currentIndex > 0;
        }


        public T next() {
            return array[currentIndex--];
        }
    }

}
