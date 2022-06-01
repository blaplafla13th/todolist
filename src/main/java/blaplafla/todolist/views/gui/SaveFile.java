package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.FileController;
import blaplafla.todolist.controllers.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SaveFile
        extends ViewGui
        implements Initializable {
    final DictionaryController d = MainController.getInstance().dictionaryController();
    final FileController f = MainController.getInstance().fileController();
    private final JFileChooser fileChooser = new JFileChooser();
    @FXML
    private Label filepath;
    @FXML
    private Label overwrite;
    @FXML
    private Button cancel;
    @FXML
    private Button ok;
    @FXML
    private Button changePath;

    public void run() {
        show = true;
        jframe.setTitle(d.label("save-button"));
        jframe.setSize(500, 200);
        fixedJFrame();
        int error = initFX("SaveFile.fxml");
        if (error != 100)
            MainController.getInstance().returnCode(error);
        jframe.add(jfxPanel);
        jframe.setVisible(true);
    }

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

    public void changePath() {
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int status = fileChooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            int code =
                    MainController.getInstance().fileController().setFile(file.getAbsolutePath());
            if (code != 100)
                MainController.getInstance().returnCode(code);
        }
        else if (status == JFileChooser.CANCEL_OPTION) {
            close();
            ((ViewGui) MainController.getInstance().router().getSaveFile()).close();

        }
        close();
        MainController.getInstance().router().refresh();
    }

    public void accept() {
        if (f.hasFile()) {
            f.exportListTask();
            close();
            ((ViewGui) MainController.getInstance().router().getSaveFile()).close();
        }
        else
            changePath();
    }

    public void cancel() {
        close();
        ((ViewGui) MainController.getInstance().router().getSaveFile()).close();
    }
}
