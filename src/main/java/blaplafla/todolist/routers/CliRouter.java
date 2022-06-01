package blaplafla.todolist.routers;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.requests.TerminalInputValidation;
import blaplafla.todolist.views.cli.*;

import java.io.IOException;

public class CliRouter
        extends Router {
    DictionaryController dictionaryController = MainController.getInstance().dictionaryController();

    public CliRouter() {
        input = new TerminalInputValidation();
    }

    @Override
    public void setupView() {
        index = new Index();
        setLanguage = new SetLanguage();
        openFile = new OpenFile();
        saveFile = new SaveFile();
        done = new Done();
        undone = new Undone();
        create = new Create();
        edit = new Edit();
        detail = new Detail();
        undoneSubTask = new UndoneSub();
        doneSubTask = new DoneSub();
        detailSubTask = new DetailSub();
    }

    @Override
    public void pause() {
        System.out.println(dictionaryController.label("pause"));
        try {
            System.in.read();
        }
        catch (IOException ignored) {
        }
    }

    @Override
    public void returnCode(int error) {
        if (error != 100) {
            System.out.println(dictionaryController.errorExplain(error));
            pause();
        }
    }
}
