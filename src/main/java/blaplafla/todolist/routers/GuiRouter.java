package blaplafla.todolist.routers;

import blaplafla.todolist.requests.TerminalInputValidation;
import blaplafla.todolist.views.gui.Error;
import blaplafla.todolist.views.gui.*;

public class GuiRouter extends Router {
    ViewGui error;

    public GuiRouter() {
        input = new TerminalInputValidation();
    }

    @Override
    public void setupView() {
        this.index = new Index();
        this.setLanguage = new SetLanguage();
        this.openFile = new OpenFile();
        this.saveFile = new SaveFile();
        this.error = new Error();
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
        if (error.isShow()) {
            error.close();
            error.run();
        }
    }
}
