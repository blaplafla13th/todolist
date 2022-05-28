package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.dictionary.Dictionary;
import blaplafla.todolist.models.task.ListTask;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SetLanguage extends ViewGui implements Initializable {
    DictionaryController d = MainController.getInstance().dictionaryController();

    public SetLanguage() {
        super();
    }

    @Override
    public void run() {
        show = true;
        jframe.setTitle(d.label("set-lang"));
        jframe.setSize(300, 350);
        fixedJFrame();
        jframe.add(jfxPanel);
        jframe.setVisible(true);
    }

    protected void initFX() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SetLanguage.fxml"));
            Scene scene = new Scene(root);
            jfxPanel.setScene(scene);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }


    @FXML
    private javafx.scene.control.Label curlang;
    @FXML private javafx.scene.control.Label changelang;
    @FXML private javafx.scene.control.Button cancel;
    @FXML private Button ok;
    @FXML private ListView<Dictionary> langlist;
    private ObservableList<Dictionary> dictionaryObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        curlang.setText(d.label("current-lang") + d.getDictionary());
        changelang.setText(d.label("set-lang"));
        dictionaryObservableList.addAll(d.getLanglist());
        langlist.setItems(dictionaryObservableList);
        langlist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        langlist.getSelectionModel().selectFirst();
        ok.setText(d.label("ok"));
        cancel.setText(d.label("cancel"));
    }
    public void accept(){
        if (!langlist.getSelectionModel().isEmpty()){
            d.setDictionary(langlist.getSelectionModel().getSelectedIndex());
            cancel();
            MainController.getInstance().router().refresh();
        }
    }
    public void cancel(){
        this.close();
        ((ViewGui) MainController.getInstance().router().getSetLanguage()).close();
    }
}
