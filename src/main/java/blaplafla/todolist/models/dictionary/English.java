package blaplafla.todolist.models.dictionary;

public class English extends Dictionary {
    @Override
    public String errorExplain(int errorCode) {
        return switch (errorCode) {
            case 1 -> "Successful";
            case 2 -> "File not found";
            case 3 -> "File can't be read or written";
            case 4 -> "File is invalid";
            default -> "Unknown";
        };
    }

    @Override
    public String prettyTime(long day, long hour, long minute, long second) {
        return String.format("time left: %d d, %d h : %d m : %d s", day, hour, minute, second);
    }

    @Override
    public String label(String label) {
        return switch (label) {
            case "todolist-name" -> "To-do list assigned to  ";
            default -> "Unknown";
        };
    }
}
