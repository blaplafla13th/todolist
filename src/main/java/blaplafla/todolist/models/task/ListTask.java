package blaplafla.todolist.models.task;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.models.datastructure.*;

import java.io.Serializable;
import java.util.Date;

public class ListTask implements Serializable {
    private SimpleArrayList<MotherTask> undone;
    private SimpleVector<MotherTask> done;
    private String username;

    public ListTask() {
        username = "user";
        undone = new SimpleArrayList<>();
        done = new SimpleVector<>();
    }

    public SimpleArrayList<MotherTask> getUndone() {
        return undone;
    }

    public SimpleVector<MotherTask> getDone() {
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
            return 100;
        } else return 501;
    }

    public int undoneTask(MotherTask task) {
        if (done.isContain(task)) {
            undone.add(task);
            done.remove(task);
            return 100;
        } else return 501;
    }

    public Task getTaskById(int from, int id) {
        Task task;
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
        if (undone.isContain(task)){
            return doneTask(task);
        }else if (done.isContain(task)){
            return undoneTask(task);
        }
        else return 501;
    }

}
