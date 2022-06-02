package blaplafla.todolist.routers;

import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.requests.JavaFXValidation;
import blaplafla.todolist.views.View;
import blaplafla.todolist.views.gui.Error;
import blaplafla.todolist.views.gui.*;

public class GuiRouter
        extends Router {
    ViewGui error;
    View systemTrayIcon;

    public GuiRouter() {
        input = new JavaFXValidation();
    }

    public View getSystemTrayIcon() {
        return systemTrayIcon;
    }

    @Override
    public void setupView() {
        systemTrayIcon = new SystemTrayIcon();
        index = new Index();
        setLanguage = new SetLanguage();
        openFile = new OpenFile();
        saveFile = new SaveFile();
        error = new Error();
        create = new Create();
        undone = new Undone();
        done = new Done();
        detail = new Detail();
        edit = new Edit();
        undoneSubTask = new UndoneSub();
        doneSubTask = new DoneSub();
        detailSubTask = new DetailSub();
    }

    @Override
    public void pause() {

    }

    @Override
    public void returnCode(int error) {
        this.error.run(error);
    }

    @Override
    public void refresh() {
        if (index instanceof ViewGui index && index.isShow()) {
            index.close();
            index.run();
        }
        if (setLanguage instanceof ViewGui setLanguage && setLanguage.isShow()) {
            setLanguage.close();
            setLanguage.run();
        }
        if (openFile instanceof ViewGui openFile && openFile.isShow()) {
            openFile.close();
            openFile.run();
        }
        if (saveFile instanceof ViewGui saveFile && saveFile.isShow()) {
            saveFile.close();
            saveFile.run();
        }
        if (undone instanceof ViewGui undone && undone.isShow()) {
            undone.close();
            undone.run(MainController.getInstance().listTask());
        }
        if (done instanceof ViewGui done && done.isShow()) {
            done.close();
            done.run(MainController.getInstance().listTask());
        }
        if (detail instanceof Detail detail && detail.isShow()) {
            detail.close();
            detail.run(Detail.getMotherTask());
        }
        if (edit instanceof Edit edit && edit.isShow()) {
            edit.close();
            edit.run(Edit.getTask());
        }
        if (undoneSubTask instanceof UndoneSub undoneSubTask && undoneSubTask.isShow()) {
            undoneSubTask.close();
            undoneSubTask.run(UndoneSub.getMotherTask());
        }
        if (doneSubTask instanceof DoneSub doneSubTask && doneSubTask.isShow()) {
            doneSubTask.close();
            doneSubTask.run(DoneSub.getMotherTask());
        }
        if (detailSubTask instanceof DetailSub detailSubTask && detailSubTask.isShow()) {
            detailSubTask.close();
            detailSubTask.run(DetailSub.getMotherTask(), DetailSub.getTask());
        }
        if (error.isShow()) {
            error.close();
            error.run();
        }
    }

    public ViewGui getError() {
        return error;
    }
}
