package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.models.dictionary.Dictionary;
import blaplafla.todolist.requests.RequestValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SetLanguageGui implements Initializable {
    DictionaryController d = MainController.getInstance().dictionaryController();
    RequestValidation r;

    @FXML private Label curlang;
    @FXML private Label changelang;
    @FXML private Button cancel;
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
            MainController.getInstance().router().refresh();
        }
    }
    public void cancel(){
        ((SetLanguage) MainController.getInstance().setLanguageView()).close();
    }
}
