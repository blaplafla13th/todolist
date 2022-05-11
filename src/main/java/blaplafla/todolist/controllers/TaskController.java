package blaplafla.todolist.controllers;

import blaplafla.todolist.models.task.ListTask;

import java.util.Date;

public class TaskController {

    public TaskController() {
    }

    public void index(){
            MainController.getInstance().index.run(MainController.getInstance().listTask);
    }

    public void create(){
        MainController.getInstance().index.run(MainController.getInstance().listTask);
    }
    public void create(String title, String description, Date deadline, int priority){
        MainController.getInstance().listTask.addTask(title,description,deadline,priority);
    }

}
