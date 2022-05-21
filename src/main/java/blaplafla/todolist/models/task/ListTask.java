package blaplafla.todolist.models.task;

import blaplafla.todolist.models.datastructures.SimpleArrayList;
import blaplafla.todolist.models.datastructures.SimpleStack;

import java.io.Serializable;
import java.util.Date;

public class ListTask implements Serializable {
    private final SimpleArrayList<MotherTask> undone;
    private final SimpleStack<MotherTask> done;
    private String username;

    public ListTask() {
        username = "user";
        undone = new SimpleArrayList<>();
        done = new SimpleStack<>();
    }

    public SimpleArrayList<MotherTask> getUndone() {
        undone.sort();
        return undone;
    }

    public SimpleStack<MotherTask> getDone() {
        return done;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int doneTask(MotherTask task) {
        if (undone.isContain(task)) {
            done.add(task);
            undone.remove(task);
            task.toggle();
            return 100;
        } else return 501;
    }

    public int undoneTask(MotherTask task) {
        if (done.isContain(task)) {
            undone.add(task);
            done.remove(task);
            task.toggle();
            return 100;
        } else return 501;
    }

    public Task getTaskById(int from, int id) {
        if (from == 1) {
            return undone.get(id);
        } else if (from == 2) {
            return done.get(id);
        } else return null;
    }

    public int deleteTaskById(int from, int id) {
        if (from == 1) {
            undone.removeIndex(id);
            return 100;
        } else if (from == 2) {
            done.removeIndex(id);
            return 100;
        } else return 501;
    }

    public Task getLastDoneTask() {
        return done.peek();
    }

    public void addTask(String title, String description, Date deadline, int priority) {
        MotherTask newTask = new MotherTask(title, description, deadline, priority);
        undone.add(newTask);
    }

    public int editTask(Task task, String title, String description, Date deadline, int priority) {
        if (task == null)
            return 501;
        task.setDeadline(deadline);
        task.setDescription(description);
        task.setTitle(title);
        task.setPriority(priority);
        return 100;
    }

    public int toggleTask(MotherTask task) {
        if (!undone.isContain(task) && !done.isContain(task))
            return 501;
        if (task.isStatus()) {
            return undoneTask(task);
        } else
            return doneTask(task);
    }

    public boolean isEmpty() {
        return done.isEmpty() &&
            undone.isEmpty();
    }
}
