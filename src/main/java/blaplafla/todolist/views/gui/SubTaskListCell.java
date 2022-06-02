package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.models.task.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class SubTaskListCell
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
            URL fxm = getClass().getResource("SubTaskListCell.fxml");
            FXMLLoader loader = new FXMLLoader(fxm);
            AnchorPane anchorPane;
            try {
                anchorPane = loader.load();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            Label title = (Label) anchorPane.getChildren().get(0);
            Label timeLeft = (Label) anchorPane.getChildren().get(1);
            title.setText(motherTask.getTitle());
            timeLeft.setText(d.prettyTime(motherTask.prettyTimer()));
            setGraphic(anchorPane);
            setText(null);
        }
    }
}
