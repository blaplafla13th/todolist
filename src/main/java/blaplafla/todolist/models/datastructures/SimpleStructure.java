package blaplafla.todolist.models.datastructures;

public interface SimpleStructure<T> {
    T[] getArray();

    int size();

    int indexOf(T data);

    void add(T data);

    T get(int i);

    boolean isEmpty();
}
