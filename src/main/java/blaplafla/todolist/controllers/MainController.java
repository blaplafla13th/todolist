package blaplafla.todolist.controllers;

import blaplafla.todolist.models.task.ListTask;
import blaplafla.todolist.request.RequestValidation;
import blaplafla.todolist.views.View;
import blaplafla.todolist.views.cli.SetLanguage;
import blaplafla.todolist.request.TerminalInputValidation;

public class MainController {
    private static MainController instance;
    protected View setLanguage;
    protected View saveFile;
    protected View openFile;
    protected View index;
    protected View done;
    protected View create;
    protected View edit;
    protected View makeSubTask;
    protected View detail;
    protected RequestValidation input;
    protected final DictionaryController dictionaryController;
    protected final FileController fileController;
    protected final TaskController taskController;
    protected ListTask listTask;

    public MainController() {
        dictionaryController = new DictionaryController();
        fileController = new FileController();
        taskController = new TaskController();
        listTask = new ListTask();
    }

    public void setModeCli() {
        setLanguage = new SetLanguage();
        input = new TerminalInputValidation();

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
    public RequestValidation getInput() {
        return input;
    }

    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    public View setLanguageView() {
        return setLanguage;
    }

    public View saveFileView() {
        return saveFile;
    }

    public View openFileView() {
        return openFile;
    }

    public View indexView() {
        return index;
    }

    public View doneView() {
        return done;
    }

    public View createView() {
        return create;
    }

    public View editView() {
        return edit;
    }

    public View makeSubTaskView() {
        return makeSubTask;
    }

    public View detailView() {
        return detail;
    }


}
