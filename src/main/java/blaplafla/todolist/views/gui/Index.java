package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.task.ListTask;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Index extends ViewGui {
    DictionaryController d = MainController.getInstance().dictionaryController();
    TaskController t = MainController.getInstance().taskController();
    ListTask listTask = MainController.getInstance().listTask();

    @Override
    public void run() {
        show = true;
        jframe.setTitle(d.label("todolist-name") + listTask.getUsername());
        jframe.setSize(400, 700);
        fixedJFrame();
        jframe.add(jfxPanel);
        jframe.addWindowListener(new WindowAdapter() {
                                     @Override
                                     public void windowClosed(WindowEvent e) {
                                         super.windowClosed(e);
                                         show = false;
                                     }
                                 }
        );
        Platform.runLater(this::initFX);
        jframe.setVisible(true);
    }

    @Override
    protected void initFX() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
            Scene scene = new Scene(root);
            jfxPanel.setScene(scene);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

}
