package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.routers.GuiRouter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Error extends ViewGui implements Initializable {
    private static int errorCode;
    DictionaryController d = MainController.getInstance().dictionaryController();
    @FXML
    private javafx.scene.control.Label error;
    @FXML
    private Button ok;

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public void run(Object... params) {
        if (this != ((GuiRouter) MainController.getInstance().router()).getError()) {
            this.close();
        } else {
            errorCode = (Integer) params[0];
            jframe.setTitle(d.label("error") + errorCode);
            jframe.setSize(600, 200);
            fixedJFrame();
            jframe.add(jfxPanel);
            jframe.setVisible(true);
        }
    }

    @Override
    protected void initFX() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Error.fxml"));
            Scene scene = new Scene(root);
            jfxPanel.setScene(scene);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error.setText(d.errorExplain(errorCode));
        ok.setText(d.label("ok"));
    }

    public void cancel() {
        this.close();
        ((GuiRouter) MainController.getInstance().router()).getError().close();
    }
}
