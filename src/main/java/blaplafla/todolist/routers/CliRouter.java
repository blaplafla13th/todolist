package blaplafla.todolist.routers;

import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.requests.TerminalInputValidation;
import blaplafla.todolist.views.View;
import blaplafla.todolist.views.cli.*;

import java.io.IOException;

public class CliRouter extends Router {

    public CliRouter() {
        index = new IndexCli();
        setLanguage = new SetLanguageCli();
        openFile = new OpenFileCli();
        saveFile = new SaveFileCli();
        done = new DoneCli();
        undone = new UndoneCli();
        create = new CreateCli();
        edit = new EditCli();
        detail = new DetailCli();
        undoneSubTask = new UndoneSubCli();
        doneSubTask = new DoneSubCli();
        detailSubTask = new DetailSubCli();
        input = new TerminalInputValidation();
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
}
