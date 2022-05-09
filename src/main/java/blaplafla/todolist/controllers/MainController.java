package blaplafla.todolist.controllers;

import blaplafla.todolist.Request.RequestValidation;
import blaplafla.todolist.views.View;
import blaplafla.todolist.views.cli.SetLanguage;
import blaplafla.todolist.Request.TerminalInputValidation;

public class MainController {
    private static MainController instance;
    protected View setLanguage;
    protected RequestValidation input;
    protected final DictionaryController dictionaryController;
    protected final FileController fileController;

    public MainController() {
        dictionaryController = new DictionaryController();
        fileController = new FileController();
    }

    public void setModeCli() {
        setLanguage = new SetLanguage();
        input = new TerminalInputValidation();

    }

    public DictionaryController getDictionaryController() {
        return dictionaryController;
    }

    public FileController getFileController() {
        return fileController;
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

    public View getSetLanguageView() {
        return setLanguage;
    }
}
