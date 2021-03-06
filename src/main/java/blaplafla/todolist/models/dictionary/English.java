package blaplafla.todolist.models.dictionary;

public class English
        extends Dictionary {
    @Override
    public String errorExplain(int errorCode) {
        return switch (errorCode) {
            case 100 -> "Successful";
            case 200 -> "Data file not found";
            case 201 -> "Language file not found";
            case 202 -> "FXML file not found";
            case 300 -> "Data file can't be read or written";
            case 301 -> "Language file can't be read or written";
            case 302 -> "FXML file can't be read or written";
            case 303 -> "Can't Create system tray";
            case 304 -> "Can't set UTF-8 to terminal";
            case 400 -> "Data file is invalid";
            case 401 -> "Language file is invalid";
            case 402 -> "Params is invalid";
            case 500 -> "User input wrong data type";
            case 501 -> "Input id not found";
            case 502 -> "Invalid day";
            case 503 -> "Invalid time";
            default -> "Unknown";
        };
    }

    @Override
    public String prettyTime(long day, long hour, long minute, long second) {
        return String.format("Time left: %d d, %d h : %d m : %d s", day, hour, minute, second);
    }

    @Override
    public String label(String label) {
        return switch (label) {
            case "input-num-option" -> "Input number of option ";
            case "todolist-name" -> "Assigned task in ";
            case "current-lang" -> "Current language: ";
            case "set-lang" -> "Set language to: ";
            case "sel-lang-term" -> "Input number of option: ";
            case "def" -> "Return default value: ";
            case "input-date" -> "Input date (form y-m-d:) ";
            case "input-time" -> "Input time (form h:m:s (24h):) ";
            case "list-undone" -> "List undone task:";
            case "list-done" -> "List done task:";
            case "title" -> "Title: ";
            case "desc" -> "Description: ";
            case "input-command" -> "Input Command";
            case "list command" -> "List available command";
            case "next-button" -> "Next page";
            case "prev-button" -> "Prev page";
            case "set-username-button" -> "Rename";
            case "add-button" -> "Add";
            case "delete-button" -> "Delete";
            case "detail-button" -> "Detail";
            case "set-name" -> "New name";
            case "unknown-command" -> "Unknown command, type 'help' for list command";
            case "pause" -> "Press Enter to continue...";
            case "open-button" -> "Import file";
            case "save-button" -> "Export file";
            case "path-to-file" -> "Input filepath";
            case "not-file" -> "Invalid filepath";
            case "warning-overwrite-data" -> "Do you want override data into current file?";
            case "warning-load-data" -> "Do you want override data from file to this session?";
            case "type-yes-to-continue" -> "Type 'yes' to continue...";
            case "process-done" -> "Done";
            case "subtask-incomplete" -> "Remaining subtask: ";
            case "toggle-button" -> "Change status ";
            case "subtask-total" -> "SubTask: ";
            case "set-lang-button" -> "Change language ";
            case "done list-button" -> "Done List ";
            case "undone list-button" -> "Undone list ";
            case "exit-button" -> "Exit ";
            case "done-list-name" -> "Done list of ";
            case "back-button" -> "Back";
            case "index" -> "Input index";
            case "-" -> "--------------------------------";
            case "create-sub" -> "Create new subtask";
            case "create-task" -> "Create new task";
            case "placeholder-title" -> "Title: ";
            case "placeholder-desc" -> "Description: ";
            case "placeholder-deadline" -> "Deadline: ";
            case "placeholder-priority" -> "Priority (int): ";
            case "priority" -> "Priority: ";
            case "last-done" -> "Latest done task: ";
            case "add-subtask-button" -> "Create subtask";
            case "toggle-this-button" -> "Toggle this";
            case "delete-this-button" -> "Delete this";
            case "edit-this-button" -> "Edit";
            case "mother-task-name" -> "Main task: ";
            case "task-name" -> "Title: ";
            case "deadline-time" -> "Deadline: ";
            case "done-sub-list" -> "List done subtasks";
            case "edit-task" -> "Edit task";
            case "subtask-remaining" -> "Subtasks Remaining";
            case "undone-sub-list" -> "List undone subtasks";
            case "tasks" -> "Tasks";
            case "subtasks" -> "SubTasks";
            case "refresh" -> "Refresh";
            case "old-value" -> "Old Value: ";
            case "ok" -> "Accept";
            case "cancel" -> "Cancel";
            case "path" -> "Filepath:";
            case "changepath" -> "Change File";
            case "error" -> "Error ";
            case "click-to-show" -> "Click to show ";
            case "hour" -> "h";
            case "minute" -> "m";
            case "second" -> "s";
            case "done-button" -> "Done";
            case "start-button" -> "Open";
            case "undone-button" -> "Undone";
            case "page" -> "Page ";
            case "subtask-completed" -> "Subtask Completed ";
            case "back" -> "Back";
            case "name" -> "List:";
            default -> "Unknown";
        };
    }

    @Override
    public String toString() {
        return "English";
    }
}
