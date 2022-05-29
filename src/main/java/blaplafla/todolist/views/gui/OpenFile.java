package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.FileController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.requests.RequestValidation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class OpenFile extends ViewGui implements Initializable {
    final DictionaryController d = MainController.getInstance().dictionaryController();
    final FileController f = MainController.getInstance().fileController();
    final RequestValidation r = MainController.getInstance().input();
    private final JFileChooser fileChooser = new JFileChooser();
    File file;
    @FXML
    private Label filepath;
    @FXML
    private Label overwrite;
    @FXML
    private Button cancel;
    @FXML
    private Button ok;
    @FXML
    private javafx.scene.control.TextField name;

    public void run() {
        show = true;
        jframe.setTitle(d.label("open-button"));
        jframe.setSize(500, 200);
        fixedJFrame();
        int error = initFX("FileChooser.fxml");
        if (error != 100)
            MainController.getInstance().returnCode(error);
        jframe.add(jfxPanel);
        jframe.setVisible(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        filepath.setText(d.label("path-to-file"));
        ok.setText(d.label("ok"));
        cancel.setText(d.label("cancel"));
        name.setPromptText(d.label("path-to-file"));
        overwrite.setVisible(false);
    }

    public void accept() {
        int code = f.importListTask();
        if (code!=100)
        MainController.getInstance().returnCode(code);
        close();
        ((ViewGui) MainController.getInstance().router().getOpenFile()).close();
        MainController.getInstance().router().refresh();
    }

    public void cancel() {
        close();
        ((ViewGui) MainController.getInstance().router().getOpenFile()).close();
    }

    public void handle() {
        overwrite.setVisible(false);
        file = new File( r.reformat(name.getText()));
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

    public void check() {
        f.setFile( r.reformat(name.getText()) );
        if (!f.isNullFile() && !MainController.getInstance().listTask().isEmpty()) {
            overwrite.setText(d.label("warning-load-data"));
            overwrite.setVisible(true);
        }
    }
}
