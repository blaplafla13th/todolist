package blaplafla.todolist;

import blaplafla.todolist.controllers.MainController;

import java.util.Date;

public class GuiStarter {
    public static void main(String[] args) {
        MainController mainController = MainController.getInstance();
        mainController.setModeGui();
        mainController.taskController().index();
    }
}