package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.models.task.MotherTask;
import blaplafla.todolist.models.task.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class TaskListCell extends ListCell<Task> {
    DictionaryController d = MainController.getInstance().dictionaryController();
    @FXML
    private Label title;
    @FXML
    private Label desc;
    @FXML
    private Label timeLeft;
    @FXML
    private Label incomplete;
    @FXML
    private AnchorPane anchorPane;

    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);
        if (task == null || empty) {
            setText(null);
            setGraphic(null);
        } else if (task != null) {
            URL fxm = getClass().getResource("TaskListCell.fxml");
            FXMLLoader loader = new FXMLLoader(fxm);
            try {
                anchorPane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            title = (Label) anchorPane.getChildren().get(0);
            timeLeft = (Label) anchorPane.getChildren().get(1);
            desc = (Label) anchorPane.getChildren().get(2);
            incomplete = (Label) anchorPane.getChildren().get(3);
            title.setText(task.getTitle());
            timeLeft.setText(d.prettyTime(task.prettyTimer()));
            desc.setText(task.getDescription());
            incomplete.setText(d.label("subtask-incomplete") + ((MotherTask) task).undoneSubTaskSize());
            setGraphic(anchorPane);
            setText(null);
        }
    }
}
