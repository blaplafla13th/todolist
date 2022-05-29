package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.models.dictionary.Dictionary;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.ResourceBundle;

public class SetLanguage extends ViewGui implements Initializable {
    private final ObservableList<Dictionary> dictionaryObservableList = FXCollections.observableArrayList();
    DictionaryController d = MainController.getInstance().dictionaryController();
    @FXML
    private javafx.scene.control.Label curlang;
    @FXML
    private javafx.scene.control.Label changelang;
    @FXML
    private javafx.scene.control.Button cancel;
    @FXML
    private Button ok;
    @FXML
    private ListView<Dictionary> langlist;
    public SetLanguage() {
        super();
    }

    @Override
    public void run() {
        show = true;
        jframe.setTitle(d.label("set-lang"));
        jframe.setSize(300, 350);
        fixedJFrame();
        int error = initFX("SetLanguage.fxml");
        if (error!=100)
            MainController.getInstance().returnCode(error);
        jframe.add(jfxPanel);
        jframe.setVisible(true);
    }

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

    public void accept() {
        if (!langlist.getSelectionModel().isEmpty()) {
            d.setDictionary(langlist.getSelectionModel().getSelectedIndex());
            cancel();
            MainController.getInstance().router().refresh();
        }
    }

    public void cancel() {
        this.close();
        ((ViewGui) MainController.getInstance().router().getSetLanguage()).close();
    }
}
