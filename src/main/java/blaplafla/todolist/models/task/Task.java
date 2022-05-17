package blaplafla.todolist.models.task;

import blaplafla.todolist.controllers.DictionaryController;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable, Comparable<Task> {
    private String title;
    private String description;
    private Date deadline;
    private int priority;

    private final String  created_at;


    public Task(String title, String description, Date deadline, int priority) {
        this.title = title;
        this.deadline = deadline;
        this.description = description;
        this.priority = priority;
        created_at=System.currentTimeMillis()+"/"+System.nanoTime();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long leftTime() {
        return deadline.getTime() - System.currentTimeMillis(); //millisecond
    }

    @Override
    public int compareTo(Task task) {
        int temp = Long.compare(this.leftTime() / priority, task.leftTime() / priority);
        return temp == 0 ? Long.compare(this.leftTime(), task.leftTime()) : temp;
    }

    public long[] prettyTimer() {
        long leftTime = leftTime();
        leftTime = leftTime / 1000; //convert to second
        long day = leftTime / 24 / 60 / 60;
        leftTime = leftTime - day * 24 * 60 * 60;
        long hour = leftTime / 60 / 60;
        leftTime = leftTime - hour * 60 * 60;
        long minute = leftTime / 60;
        long second = leftTime - minute * 60;
        return new long[]{day, hour, minute, second};
    }

    public boolean equals(Task task) {
        return created_at.equals(task.created_at);
    }
}
