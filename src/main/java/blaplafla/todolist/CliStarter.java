package blaplafla.todolist;

import blaplafla.todolist.controllers.MainController;

public class CliStarter {
    public static void main(String[] args) {
        MainController mainController = MainController.getInstance();
        mainController.setModeCli();
        mainController.taskController().index();

    }
}
