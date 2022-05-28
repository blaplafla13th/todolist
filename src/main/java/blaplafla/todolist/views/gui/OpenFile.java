package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.FileController;
import blaplafla.todolist.controllers.MainController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OpenFile extends ViewGui implements Initializable {
    DictionaryController d = MainController.getInstance().dictionaryController();
    FileController f = MainController.getInstance().fileController();
    boolean loadfile = false;

    public void run() {
        show = true;
        jframe.setTitle(d.label("open-button"));
        jframe.setSize(500, 200);
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

    protected void initFX() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FileChooser.fxml"));
            Scene scene = new Scene(root);
            jfxPanel.setScene(scene);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    private Label filepath;
    @FXML private Label overwrite;
    @FXML
    private Button cancel;
    @FXML
    private Button ok;
    private JFileChooser fileChooser = new JFileChooser();
    @FXML
    private javafx.scene.control.TextField name;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filepath.setText(d.label("path-to-file"));
        ok.setText(d.label("ok"));
        cancel.setText(d.label("cancel"));
        name.setPromptText(d.label("path-to-file"));
        overwrite.setVisible(false);
    }

    public void accept() {
        MainController.getInstance().returnCode(f.importListTask());
        MainController.getInstance().router().refresh();
        this.close();
        ((ViewGui) MainController.getInstance().router().getOpenFile()).close();
    }

    public void cancel() {
        this.close();
        ((ViewGui) MainController.getInstance().router().getOpenFile()).close();
    }

    File file;

    public void handle() {
        overwrite.setVisible(false);
        file = new File(name.getText());
        if (name.getText() != null && file.isFile())
            fileChooser.setCurrentDirectory(file.getParentFile());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int status = fileChooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            name.setText(file.getAbsolutePath());
            check();
        } else if (status == JFileChooser.CANCEL_OPTION) {
            name.clear();
        }
    }

    public void check(){
        f.setFile(name.getText());
        if (!f.isNullFile() && !MainController.getInstance().listTask().isEmpty()) {
            overwrite.setText(d.label("warning-load-data"));
            overwrite.setVisible(true);
        }
    }
}
