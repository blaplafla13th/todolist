package blaplafla.todolist.Modals;

import java.io.Serializable;

public class ListTask implements Serializable {
    private SimpleArrayList<MotherTask> undone;
    private SimpleArrayList<MotherTask> done;
    private String username;

    public ListTask(String username) {
        this.username = username;
        undone =new SimpleArrayList<>();
        done=new SimpleArrayList<>();
    }

    public SimpleArrayList<MotherTask> getUndone() {
        return undone;
    }

    public SimpleArrayList<MotherTask> getDone() {
        return done;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void doneTask(int id) {
        done.add(undone.get(id));
        undone.removeIndex(id);
    }

    public void undoneTask(int id) {
        undone.add(done.get(id));
        done.removeIndex(id);
    }

    @Override
    public String toString() {
        return String.format("Danh sách việc cần làm của %s",username);
    }
}
