package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.models.task.MotherTask;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;

public class UndoneMotherTaskListCell extends ListCell<MotherTask> {
    DictionaryController d = MainController.getInstance().dictionaryController();

    @Override
    protected void updateItem(MotherTask motherTask, boolean empty) {
        super.updateItem(motherTask, empty);
        if (motherTask == null || empty) {
            setText(null);
            setGraphic(null);
        } else {
            URL fxm = getClass().getResource("TaskListCell.fxml");
            FXMLLoader loader = new FXMLLoader(fxm);
            AnchorPane anchorPane;
            try {
                anchorPane = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            GridPane gridPane1 = (GridPane) anchorPane.getChildren().get(0);
            GridPane gridPane2 = (GridPane) anchorPane.getChildren().get(1);
            Label title = (Label) gridPane1.getChildren().get(0);
            Label timeLeft = (Label) gridPane1.getChildren().get(1);
            Label desc = (Label) gridPane2.getChildren().get(0);
            Label incomplete = (Label) gridPane2.getChildren().get(1);
            title.setText(motherTask.getTitle());
            timeLeft.setText(d.prettyTime(motherTask.prettyTimer()));
            desc.setText(d.label("desc") + motherTask.getDescription());
            incomplete.setText(d.label("subtask-remaining") + ": " + motherTask.undoneSubTaskSize() +
                    "/" + motherTask.subTaskSize());
            setGraphic(anchorPane);
            setText(null);
        }
    }
}