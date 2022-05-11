package blaplafla.todolist;

import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.views.View;
import blaplafla.todolist.views.cli.SetLanguage;

public class CliStarter {
    public static void main(String[] args) {
        MainController mainController = MainController.getInstance();
        mainController.setModeCli();

    }
}
