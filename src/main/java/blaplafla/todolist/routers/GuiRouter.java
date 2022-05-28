package blaplafla.todolist.routers;

import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.requests.TerminalInputValidation;
import blaplafla.todolist.views.gui.*;

import java.io.IOException;

public class GuiRouter extends Router {


    public GuiRouter() {
        input = new TerminalInputValidation();
    }

    @Override
    public void setupView() {
        this.index = new Index();
        this.setLanguage = new SetLanguage();
        this.openFile=new OpenFile();
        this.saveFile=new SaveFile();
    }

    @Override
    public void pause() {
        System.out.println(MainController.getInstance().dictionaryController().label("pause"));
        try {
            System.in.read();
        } catch (IOException ignored) {
        }
    }

    @Override
    public void returnCode(int error) {
        if (error != 100) {
            MainController.getInstance().dictionaryController().errorExplain(error);
            pause();
        }
    }

    @Override
    public void refresh() {
        if (index instanceof ViewGui index && index.isShow()){
            index.close();
            index.run();
        }
        if (setLanguage instanceof ViewGui setLanguage && setLanguage.isShow()){
            setLanguage.close();
            setLanguage.run();
        }if (openFile instanceof ViewGui openFile && openFile.isShow()){
             openFile.close();
             openFile.run();
        }if (saveFile instanceof ViewGui saveFile && saveFile.isShow()){
             saveFile.close();
             saveFile.run();
        }
    }
}
