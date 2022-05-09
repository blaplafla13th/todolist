package blaplafla.todolist.models.dictionary;

public class English extends Dictionary {
    @Override
    public String errorExplain(int errorCode) {
        return switch (errorCode) {
            case 100 -> "Successful";
            case 200 -> "Data file not found";
            case 201 -> "Language file not found";
            case 300 -> "Data file can't be read or written";
            case 301 -> "Language file can't be read or written";
            case 400 -> "Data file is invalid";
            case 401 -> "Language file is invalid";
            case 500 -> "User input wrong data type";
            case 501 -> "Input id not found";
            case 502 -> "Invalid day";
            case 503 -> "Invalid time";
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
            case "todolist-name" -> "To-do list assigned to ";
            case "current-lang" -> "Current language: ";
            case "set-lang" -> "Set language to: ";
            case "sel-lang-term" -> "Input number of option: ";
            case "def" -> "Return default value: ";
            case "input-date" -> "Input date (form y-m-d:) ";
            case "input-time" -> "Input time (form h:m:s (24h):) ";
            default -> "Unknown";
        };
    }

    @Override
    public String getInfo() {
        return "English";
    }
}
