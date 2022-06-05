package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.models.task.MotherTask;
import blaplafla.todolist.models.task.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;

public class IndexMotherTaskListCell
        extends ListCell<Task> {
    DictionaryController d = MainController.getInstance().dictionaryController();

    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);
        if (task == null || empty) {
            setText(null);
            setGraphic(null);
        }
        else {
            URL fxm = getClass().getResource("TaskListCell.fxml");
            FXMLLoader loader = new FXMLLoader(fxm);
            AnchorPane anchorPane;
            try {
                anchorPane = loader.load();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            GridPane gridPane = (GridPane) anchorPane.getChildren().get(0);
            Label title = (Label) gridPane.getChildren().get(0);
            Label timeLeft = (Label) gridPane.getChildren().get(1);
            Label incomplete = (Label) gridPane.getChildren().get(2);
            title.setText(task.getTitle());
            timeLeft.setText(d.prettyTime(task.prettyTimer()));
            incomplete.setText(
                    d.label("subtask-incomplete") + ((MotherTask) task).undoneSubTaskSize());
            setGraphic(anchorPane);
            setText(null);
        }
    }
}
