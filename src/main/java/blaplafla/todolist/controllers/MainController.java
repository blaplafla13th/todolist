package blaplafla.todolist.controllers;

import blaplafla.todolist.models.task.ListTask;
import blaplafla.todolist.request.RequestValidation;
import blaplafla.todolist.request.TerminalInputValidation;
import blaplafla.todolist.views.View;
import blaplafla.todolist.views.cli.*;

import java.io.IOException;

public class MainController {
    private static MainController instance;
    protected final DictionaryController dictionaryController;
    protected final FileController fileController;
    protected final TaskController taskController;
    protected View setLanguage;
    protected View saveFile;
    protected View openFile;
    protected View index;
    protected View done;
    protected View undone;
    protected View create;
    protected View edit;
    protected View detail;
    protected View doneSubTask;
    protected View undoneSubTask;
    protected View createSubTask;
    protected View detailSubTask;
    protected RequestValidation input;
    protected ListTask listTask;

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
        input = new TerminalInputValidation();
        index = new IndexCli();
        setLanguage = new SetLanguageCli();
        openFile = new OpenFileCLI();
        saveFile = new SaveFileCLI();
        done = new DoneCli();

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
        return input;
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

    public View undoneView() {
        return undone;
    }

    public View createView() {
        return create;
    }

    public View editView() {
        return edit;
    }

    public View detailView() {
        return detail;
    }

    public View doneSubTaskView() {
        return doneSubTask;
    }

    public View undoneSubTaskView() {
        return undoneSubTask;
    }

    public View createSubTaskView() {
        return createSubTask;
    }

    public View detailSubTaskView() {
        return detailSubTask;
    }

    public void pause() {
        System.out.println(dictionaryController.label("pause"));
        try {
            System.in.read();
        } catch (IOException e) {
        }
    }

    public void returnCode(int error) {
        if (error != 100) {
            dictionaryController.errorExplain(error);
            MainController.getInstance().pause();
        }
    }
}
