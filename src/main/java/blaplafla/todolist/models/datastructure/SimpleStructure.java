package blaplafla.todolist.models.datastructure;

public interface SimpleStructure<T> {
    T[] getArray();
    int size();
    int indexOf(T data);
    void add(T data);
    T get(int i);
    boolean isEmpty();
}
