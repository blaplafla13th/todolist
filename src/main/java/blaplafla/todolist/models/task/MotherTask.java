package blaplafla.todolist.models.task;

import blaplafla.todolist.models.arraylist.SimpleArrayList;

import java.io.Serializable;
import java.util.Date;

public class MotherTask extends Task implements Serializable, Comparable<Task> {
    private SimpleArrayList<Task> undoneSubTask;
    private SimpleArrayList<Task> doneSubTask;

    public MotherTask(String title, String description, Date deadline, int priority) {
        super(title, description, deadline, priority);
        undoneSubTask = new SimpleArrayList<>();
        doneSubTask = new SimpleArrayList<>();
    }

    public SimpleArrayList<Task> getSubTask() {
        return undoneSubTask;
    }

    public void doneSubtask(int id) {
        doneSubTask.add(undoneSubTask.get(id));
        undoneSubTask.removeIndex(id);
    }

    public void undoneSubtask(int id) {
        undoneSubTask.add(doneSubTask.get(id));
        doneSubTask.removeIndex(id);
    }
}
