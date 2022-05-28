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

public class SaveFile extends ViewGui implements Initializable {
    DictionaryController d = MainController.getInstance().dictionaryController();
    FileController f = MainController.getInstance().fileController();

    public SaveFile() {
        super();
    }

    public void run() {
        show = true;
        jframe.setTitle(d.label("save-button"));
        jframe.setSize(500, 170);
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
            Parent root = FXMLLoader.load(getClass().getResource("SaveFile.fxml"));
            Scene scene = new Scene(root);
            jfxPanel.setScene(scene);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @FXML
    private Label filepath;
    @FXML
    private Label overwrite;
    @FXML
    private Button cancel;
    @FXML
    private Button ok;

    @FXML private Button changePath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        overwrite.setText(d.label("warning-overwrite-data"));
        filepath.setText(d.label("path"));
        if (f.hasFile())
            filepath.setText(d.label("path") + f.filePath());
        else
            changePath();
        ok.setText(d.label("ok"));
        cancel.setText(d.label("cancel"));
        changePath.setText(d.label("changepath"));
    }

    private JFileChooser fileChooser = new JFileChooser();

    public void changePath() {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int status = fileChooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            int code = MainController.getInstance().fileController().setFile(file.getAbsolutePath());
            if (code != 100)
                MainController.getInstance().returnCode(code);
        }
        else if (status == JFileChooser.CANCEL_OPTION) {
            this.close();
            ((ViewGui) MainController.getInstance().router().getSaveFile()).close();

        }
        this.close();
        MainController.getInstance().router().refresh();
    }

    public void accept() {
        if (f.hasFile()) {
            f.exportListTask();
            this.close();
            ((ViewGui) MainController.getInstance().router().getSaveFile()).close();
        } else changePath();
    }

    public void cancel() {
        this.close();
        ((ViewGui) MainController.getInstance().router().getSaveFile()).close();
    }

}
