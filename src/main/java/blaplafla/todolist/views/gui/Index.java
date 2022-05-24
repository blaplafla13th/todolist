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
import javafx.scene.layout.AnchorPane;

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

    boolean show = false;

    @Override

    public void run() {
        show=true;
        jframe.setTitle(d.label("todolist-name") + listTask.getUsername());
        jframe.setSize(600,700);
        jframe.setLayout(new BorderLayout());
        JFXPanel jfxPanel = new JFXPanel();
        jframe.add(jfxPanel);
        jframe.addWindowListener(new WindowAdapter() {
                                     @Override
                                     public void windowClosed(WindowEvent e) {
                                         super.windowClosed(e);
                                         show = false;
                                     }
                                 }
        );
        Platform.runLater(() -> initFX(jfxPanel));
        jframe.setVisible(true);
    }
    private void initFX(JFXPanel jfxPanel) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Index.fxml"));
            Scene scene = new Scene(root);
            jfxPanel.setScene(scene);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void refresh() {
        jframe.dispose();
        run();
    }

}
