package blaplafla.todolist.models.dictionary;

public abstract class Dictionary {
    abstract public String errorExplain(int errorCode);
    abstract public String prettyTime(long day, long hour, long minute, long second);
    abstract public String label(String label);

}
