package blaplafla.todolist.models.task;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Task
        implements Serializable, Comparable<Task> {
    private final String created_at;
    private String title;
    private String description;
    private Date deadline;
    private int priority;
    private boolean status;

    public Task(String title, String description, Date deadline, int priority) {
        this.title = title;
        this.deadline = deadline;
        this.description = description;
        this.priority = priority;
        created_at = 100 * Math.random() + "/" + System.nanoTime();
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

    @Override
    public int compareTo(Task task) {
        int temp = Long.compare(priorityPoint(), task.priorityPoint());
        return temp == 0 ? Long.compare(leftTime(), task.leftTime()) : temp;
    }

    public long priorityPoint() {
        return leftTime() > 0 ? leftTime() / priority : leftTime() * priority;
    }

    public long leftTime() {
        return (deadline.getTime() - System.currentTimeMillis()) / 1000; //second
    }

    public long[] prettyTimer() {
        long leftTime = leftTime();
        long day = leftTime / 24 / 60 / 60;
        leftTime = leftTime - day * 24 * 60 * 60;
        long hour = leftTime / 60 / 60;
        leftTime = leftTime - hour * 60 * 60;
        long minute = leftTime / 60;
        long second = leftTime - minute * 60;
        return new long[]{day, hour, minute, second};
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(created_at, title, description, deadline, priority, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Task task = (Task) o;
        return priority == task.priority && status == task.status &&
               Objects.equals(created_at, task.created_at) && Objects.equals(title, task.title) &&
               Objects.equals(description, task.description) &&
               Objects.equals(deadline, task.deadline);
    }

    public void toggle() {
        status = !status;
    }

    @SuppressWarnings("deprecation")
    public String getDeadlineTime() {
        return String.format("%d/%d/%d %d:%d:%d", deadline.getDate(), deadline.getMonth() + 1,
                deadline.getYear() + 1900, deadline.getHours(), deadline.getMinutes(),
                deadline.getMinutes());
    }
}
