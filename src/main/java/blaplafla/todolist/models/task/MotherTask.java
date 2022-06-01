package blaplafla.todolist.models.task;

import blaplafla.todolist.models.datastructures.SimpleArrayList;
import blaplafla.todolist.models.datastructures.SimpleStack;

import java.util.Date;

public class MotherTask
        extends Task {
    private final SimpleArrayList<Task> undoneSubTask;
    private final SimpleStack<Task> doneSubTask;

    public MotherTask(String title, String description, Date deadline, int priority) {
        super(title, description, deadline, priority);
        undoneSubTask = new SimpleArrayList<>();
        doneSubTask = new SimpleStack<>();
    }

    public Task getSubTaskById(int from, int id) {
        if (from == 1) {
            return undoneSubTask.get(id);
        }
        else if (from == 2) {
            return doneSubTask.get(id);
        }
        else
            return null;
    }

    public int toggleTask(Task task) {
        if (!undoneSubTask.isContain(task) && !doneSubTask.isContain(task))
            return 501;
        if (task.isStatus()) {
            return undoneSubtask(task);
        }
        else
            return doneSubtask(task);
    }

    public int undoneSubtask(Task task) {
        if (doneSubTask.isContain(task)) {
            task.toggle();
            undoneSubTask.add(task);
            doneSubTask.remove(task);
            return 100;
        }
        else
            return 501;
    }

    public int doneSubtask(Task task) {
        if (undoneSubTask.isContain(task)) {
            task.toggle();
            doneSubTask.add(task);
            undoneSubTask.remove(task);
            return 100;
        }
        else
            return 501;
    }

    public int deleteSubTaskById(int from, int id) {
        if (from == 1) {
            undoneSubTask.removeIndex(id);
            return 100;
        }
        else if (from == 2) {
            doneSubTask.removeIndex(id);
            return 100;
        }
        else
            return 501;
    }

    public Task getLastDoneTask() {
        return doneSubTask.peek();
    }

    public void addSubTask(String title, String description, Date deadline, int priority) {
        MotherTask newTask = new MotherTask(title, description, deadline, priority);
        undoneSubTask.add(newTask);
    }

    public SimpleArrayList<Task> getUndoneSubTask() {
        undoneSubTask.sort();
        return undoneSubTask;
    }

    public SimpleStack<Task> getDoneSubTask() {
        return doneSubTask;
    }

    public int subTaskSize() {
        return doneSubTask.size() + undoneSubTask.size();
    }

    public int undoneSubTaskSize() {
        return undoneSubTask.size();
    }
}
