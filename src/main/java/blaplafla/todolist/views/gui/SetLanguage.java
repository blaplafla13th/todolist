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

public class SetLanguage implements View {
    DictionaryController d = MainController.getInstance().dictionaryController();
    TaskController t = MainController.getInstance().taskController();
    ListTask listTask = MainController.getInstance().listTask();
    private JFrame jframe = new JFrame();
    private JFXPanel jfxPanel = new JFXPanel();
    private boolean show = false;

    @Override

    public void run() {
        show = true;
        jframe.setTitle(d.label("set-lang"));
        jframe.setSize(446, 350);
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
            Parent root = FXMLLoader.load(getClass().getResource("SetLanguage.fxml"));
            Scene scene = new Scene(root);
            jfxPanel.setScene(scene);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public void close() {
        jframe.remove(jfxPanel);
        jframe.setVisible(false);
        show = false;
    }

    public boolean isShow() {
        return show;
    }

    public JFrame getJframe() {
        return jframe;
    }
}
