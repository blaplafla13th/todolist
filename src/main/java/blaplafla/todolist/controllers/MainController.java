package blaplafla.todolist.controllers;

import blaplafla.todolist.models.task.ListTask;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.routers.CliRouter;
import blaplafla.todolist.routers.Router;
import blaplafla.todolist.views.View;

public class MainController {
    private static MainController instance;
    protected final DictionaryController dictionaryController;
    protected final FileController fileController;
    protected final TaskController taskController;
    protected ListTask listTask;
    protected Router route;
    private MainController() {
        dictionaryController = new DictionaryController();
        fileController = new FileController();
        taskController = new TaskController();
        listTask = new ListTask();
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public void setModeCli() {
        route = new CliRouter();
        route.setupView();
    }

    public DictionaryController dictionaryController() {
        return dictionaryController;
    }

    public FileController fileController() {
        return fileController;
    }

    public TaskController taskController() {
        return taskController;
    }

    public ListTask listTask() {
        return listTask;
    }

    public RequestValidation input() {
        return route.getInput();
    }

    public View setLanguageView() {
        return route.getSetLanguage();
    }

    public View saveFileView() {
        return route.getSaveFile();
    }

    public View openFileView() {
        return route.getOpenFile();
    }

    public View indexView() {
        return route.getIndex();
    }

    public View doneView() {
        return route.getDone();
    }

    public View undoneView() {
        return route.getUndone();
    }

    public View createView() {
        return route.getCreate();
    }

    public View editView() {
        return route.getEdit();
    }

    public View detailView() {
        return route.getDetail();
    }

    public View doneSubTaskView() {
        return route.getDoneSubTask();
    }

    public View undoneSubTaskView() {
        return route.getUndoneSubTask();
    }

    public View detailSubTaskView() {
        return route.getDetailSubTask();
    }

    public void pause() {
        route.pause();
    }

    public void returnCode(int error) {
        route.returnCode(error);
    }
}
