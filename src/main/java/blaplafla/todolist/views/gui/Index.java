package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.task.ListTask;
import blaplafla.todolist.views.View;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Index implements View {
    DictionaryController d = MainController.getInstance().dictionaryController();
    TaskController t = MainController.getInstance().taskController();
    ListTask listTask = MainController.getInstance().listTask();

    int page = 1;
    int max_page = t.paginateSize(listTask.getUndone(), 3);
    private JFrame jframe = new JFrame();
    private boolean show = false;
    private JFXPanel jfxPanel = new JFXPanel();

    @Override
    public void run() {
        show = true;
        jframe.setTitle(d.label("todolist-name") + listTask.getUsername());
        jframe.setSize(600, 700);
        jframe.setLayout(new BorderLayout());
        jframe.add(jfxPanel);
        jframe.addWindowListener(new WindowAdapter() {
                                     @Override
                                     public void windowClosed(WindowEvent e) {
                                         super.windowClosed(e);
                                         show = false;
                                     }
                                 }
        );
        Platform.runLater(() -> initFX());
        jframe.setVisible(true);
    }

    private void initFX() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
            Scene scene = new Scene(root);
            jfxPanel.setScene(scene);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public JFrame getJframe() {
        return jframe;
    }

    public boolean isShow() {
        return show;
    }
    public void close() {
        jframe.remove(jfxPanel);
        jframe.setVisible(false);
        show = false;
    }

}
