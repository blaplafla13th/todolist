package blaplafla.todolist.models.datastructure;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;


@SuppressWarnings("unchecked")
public class SimpleVector<T extends Comparable<? super T>> implements Serializable, Iterable<T> {
    private transient T[] array;
    private int n = 0;

    public SimpleVector() {
        array = (T[]) new Comparable[50];
    }

    public void add(T data) {
        if (array == null) {
            array = (T[]) new Comparable[0];
        }
        if (n >= array.length) {
            array = Arrays.copyOf(array, array.length * 3 / 2);
        }
        array[n] = data;
        n++;
    }

    public T peek() {
        if (array == null) {
            throw new NullPointerException();
        } else if (n <= 0) return null;
        return array[n];
    }

    public T pop() {
        if (array == null) {
            throw new NullPointerException();
        } else if (n <= 0) return null;
        T temp = array[n];
        array[n] = null;
        n--;
        return temp;
    }

    public T get(int i) {
        if (array == null) {
            throw new NullPointerException();
        } else if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        return array[i];
    }

    public boolean isContain(T data) {
        if (array == null) {
            throw new NullPointerException();
        } else if (data == null) {
            return false;
        } else {
            for (int i = n - 1; i >= 0; i--) {
                if (array[i] != null && array[i].equals(data)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(T data) {
        if (data == null) {
            for (int i = n - 1; i >= 0; i--) {
                if (array[i] == null) return i;
            }
        } else if (isContain(data)) {
            for (int i = n - 1; i >= 0; i--) {
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

    public void removeIndex(int i) {
        if (array == null) {
            throw new NullPointerException();
        } else if (i < 0 || i > n) throw new IndexOutOfBoundsException();
        else {
            n--;
            if (n - i >= 0) System.arraycopy(array, i + 1, array, i, n - 1 - i);
        }
    }

    public void remove(T data) {
        int index = 0;
        T[] newArray = (T[]) new Comparable[n];
        if (array == null) {
            throw new NullPointerException();
        } else if (data == null) {
            return;
        }
        for (int i = n - 1; i >= 0; i--) {
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

    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private int currentIndex = n - 1;


        public boolean hasNext() {
            return currentIndex > 0;
        }


        public T next() {
            return array[currentIndex--];
        }
    }
}