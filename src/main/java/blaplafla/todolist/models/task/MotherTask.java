package blaplafla.todolist.models.task;

import blaplafla.todolist.models.datastructures.*;

import java.io.Serializable;
import java.util.Date;

public class MotherTask extends Task implements Serializable, Comparable<Task> {
    private SimpleArrayList<Task> undoneSubTask;
    private SimpleStack<Task> doneSubTask;

    public MotherTask(String title, String description, Date deadline, int priority) {
        super(title, description, deadline, priority);
        undoneSubTask = new SimpleArrayList<>();
        doneSubTask = new SimpleStack<>();
    }

    public SimpleArrayList<Task> getSubTask() {
        return undoneSubTask;
    }

    public int doneSubtask(Task task) {
        if (undoneSubTask.isContain(task)) {
            doneSubTask.add(task);
            undoneSubTask.remove(task);
            return 100;
        } else return 501;
    }

    public int undoneSubtask(Task task) {
        if (doneSubTask.isContain(task)) {
            undoneSubTask.add(task);
            doneSubTask.remove(task);
            return 100;
        }else return 501;
    }

    public Task getSubTaskById(int from, int id) {
        Task task;
        if (from == 1) {
            return undoneSubTask.get(id);
        } else if (from == 2) {
            return doneSubTask.get(id);
        } else return null;
    }

    public int toggleTask(Task task) {
        if (undoneSubTask.isContain(task)){
            return doneSubtask(task);
        }else if (doneSubTask.isContain(task)){
            return undoneSubtask(task);
        }
        else return 501;
    }

    public int deleteSubTaskById(int from, int id) {
        if (from == 1) {
            undoneSubTask.removeIndex(id);
            return 100;
        } else if (from == 2) {
            doneSubTask.removeIndex(id);
            return 100;
        } else return 501;
    }

    public Task getLastDoneTask() {
        return doneSubTask.peek();
    }

    public void addSubTask(String title, String description, Date deadline, int priority) {
        MotherTask newTask = new MotherTask(title, description, deadline, priority);
        undoneSubTask.add(newTask);
    }

    public SimpleArrayList<Task> getUndoneSubTask() {
        return undoneSubTask;
    }

    public SimpleStack<Task> getDoneSubTask() {
        return doneSubTask;
    }
}
