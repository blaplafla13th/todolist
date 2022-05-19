package blaplafla.todolist.controllers;

import blaplafla.todolist.models.datastructures.SimpleArrayList;
import blaplafla.todolist.models.datastructures.SimpleStructure;
import blaplafla.todolist.models.task.MotherTask;
import blaplafla.todolist.models.task.Task;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("rawtypes")
public class TaskController {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-yyyy HH:mm:ss");

    public TaskController() {
    }

    public void index() {
        MainController.getInstance().indexView().run();
    }

    public void create() {
        MainController.getInstance().createView().run(MainController.getInstance().listTask);
    }

    public void create(String title, String description, Date deadline, int priority) {
        MainController.getInstance().listTask.addTask(title, description, deadline, priority);
    }

    public void create(MotherTask task) {
        MainController.getInstance().createView().run(task);
    }

    public void create(MotherTask task, String title, String description, Date deadline, int priority) {
        task.addSubTask(title, description, deadline, priority);
    }

    public void edit(Task task) {
        MainController.getInstance().editView().run(task);
    }

    public void edit(Task task, String title, String description, Date deadline, int priority) {
        MainController.getInstance().listTask.editTask(task, title, description, deadline, priority);
    }

    public void listDone() {
        MainController.getInstance().doneView().run(MainController.getInstance().listTask);
    }

    public void listUndone() {
        MainController.getInstance().undoneView().run(MainController.getInstance().listTask);
    }

    public void toggleMotherTask(MotherTask task) {
        MainController.getInstance().listTask.toggleTask(task);
    }

    public void toggleSubTask(MotherTask task, Task subTask) {
        task.toggleTask(subTask);
    }

    public void deleteMotherTask(MotherTask task) {
        int from = 0;
        int id = 0;
        if (MainController.getInstance().listTask.getUndone().indexOf(task) != -1) {
            from = 1;
            id = MainController.getInstance().listTask.getUndone().indexOf(task);
        } else if (MainController.getInstance().listTask.getDone().indexOf(task) != -1) {
            from = 2;
            id = MainController.getInstance().listTask.getDone().indexOf(task);
        }
        MainController.getInstance().listTask.deleteTaskById(from, id);
    }

    public void deleteSubTask(MotherTask task, Task subTask) {
        int from = 0;
        int id = 0;
        if (task.getUndoneSubTask().indexOf(task) != -1) {
            id = 1;
            from = MainController.getInstance().listTask.getUndone().indexOf(task);
        } else if (task.getDoneSubTask().indexOf(task) != -1) {
            id = 2;
            from = MainController.getInstance().listTask.getDone().indexOf(task);
        }
        MainController.getInstance().listTask.deleteTaskById(from, id);
    }

    public void detailMotherTask(MotherTask task) {
        MainController.getInstance().detailView().run(task);
    }

    public void detailSubTask(MotherTask motherTask, Task task) {
        MainController.getInstance().detailSubTaskView().run(motherTask, task);
    }

    public void listDoneSub(MotherTask task) {
        MainController.getInstance().doneSubTaskView().run(task);
    }

    public void listUndoneSub(MotherTask task) {
        MainController.getInstance().undoneSubTaskView().run(task);
    }

    public int paginateSize(SimpleStructure tasks, int numPerPage) {
        return tasks.size() / numPerPage + 1;
    }

    public SimpleArrayList<Task> paginate(SimpleStructure tasks, int numPerPage, int page) {
        if (tasks == null || numPerPage * (page - 1) >= tasks.size()) {
            return new SimpleArrayList<>();
        }
        int start = numPerPage * (page - 1);
        int end = start + numPerPage;
        SimpleArrayList<Task> tasksArrayList = new SimpleArrayList<>();
        for (int i = start; i < end && i < tasks.size(); i++) {
            tasksArrayList.add((Task) tasks.get(i));
        }
        return tasksArrayList;
    }

    public String date(Date date) {
        return simpleDateFormat.format(date);
    }

}
