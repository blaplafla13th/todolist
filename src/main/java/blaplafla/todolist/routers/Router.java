package blaplafla.todolist.routers;

import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;

public abstract class Router {
    protected View index;
    protected View setLanguage;
    protected View openFile;
    protected View saveFile;
    protected View done;
    protected View undone;
    protected View create;
    protected View edit;
    protected View detail;
    protected View undoneSubTask;
    protected View doneSubTask;
    protected View detailSubTask;
    protected RequestValidation input;

    public Router() {
    }

    public RequestValidation getInput() {
        return input;
    }

    public View getIndex() {
        return index;
    }

    public View getSetLanguage() {
        return setLanguage;
    }

    public View getOpenFile() {
        return openFile;
    }

    public View getSaveFile() {
        return saveFile;
    }

    public View getDone() {
        return done;
    }

    public View getUndone() {
        return undone;
    }

    public View getCreate() {
        return create;
    }

    public View getEdit() {
        return edit;
    }

    public View getDetail() {
        return detail;
    }

    public View getUndoneSubTask() {
        return undoneSubTask;
    }

    public View getDoneSubTask() {
        return doneSubTask;
    }

    public View getDetailSubTask() {
        return detailSubTask;
    }

    public abstract void pause();

    public abstract void returnCode(int error);
}
