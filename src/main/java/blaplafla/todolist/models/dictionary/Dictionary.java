package blaplafla.todolist.models.dictionary;

public abstract class Dictionary {
    public abstract String errorExplain(int errorCode);

    public abstract String prettyTime(long day, long hour, long minute, long second);

    public abstract String label(String label);

    public abstract String toString();

}
