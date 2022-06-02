package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.models.task.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;

public class DoneSubTaskListCell
        extends ListCell<Task> {
    DictionaryController d = MainController.getInstance().dictionaryController();

    @Override
    protected void updateItem(Task motherTask, boolean empty) {
        super.updateItem(motherTask, empty);
        if (motherTask == null || empty) {
            setText(null);
            setGraphic(null);
        }
        else {
            URL fxm = getClass().getResource("SubTaskListCellFull.fxml");
            FXMLLoader loader = new FXMLLoader(fxm);
            AnchorPane anchorPane;
            try {
                anchorPane = loader.load();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            GridPane gridPane = (GridPane) anchorPane.getChildren().get(0);
            GridPane gridPane1 = (GridPane) gridPane.getChildren().get(0);
            Label title = (Label) gridPane1.getChildren().get(0);
            Label timeLeft = (Label) gridPane1.getChildren().get(1);
            Label desc = (Label) gridPane.getChildren().get(1);
            title.setText(motherTask.getTitle());
            timeLeft.setVisible(false);
            desc.setText(d.label("desc") + motherTask.getDescription());
            setGraphic(anchorPane);
            setText(null);
        }
    }
}
