package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.routers.GuiRouter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class Error extends ViewGui implements Initializable {
    private static int errorCode;
    final DictionaryController d = MainController.getInstance().dictionaryController();
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
            close();
        } else {
            errorCode = (Integer) params[0];
            jframe.setTitle(d.label("error") + errorCode);
            jframe.setSize(600, 200);
            fixedJFrame();
            int error = initFX("Error.fxml");
            if (error != 100)
                System.out.println(d.errorExplain(error));
            jframe.add(jfxPanel);
            jframe.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        error.setText(d.errorExplain(errorCode));
        ok.setText(d.label("ok"));
    }

    public void cancel() {
        close();
        ((GuiRouter) MainController.getInstance().router()).getError().close();
    }
}
