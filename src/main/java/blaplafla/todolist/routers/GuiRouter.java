package blaplafla.todolist.routers;

import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.requests.JavaFXValidation;
import blaplafla.todolist.views.View;
import blaplafla.todolist.views.gui.Error;
import blaplafla.todolist.views.gui.*;

public class GuiRouter extends Router {
    ViewGui error;
    View systemTrayIcon;

    public View getSystemTrayIcon() {
        return systemTrayIcon;
    }

    public GuiRouter() {
        input = new JavaFXValidation();
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
    }

    @Override
    public void pause() {

    }

    @Override
    public void returnCode(int error) {
        this.error.run(error);
    }

    public ViewGui getError() {
        return error;
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
        if (error.isShow()) {
            error.close();
            error.run();
        }
    }
}
