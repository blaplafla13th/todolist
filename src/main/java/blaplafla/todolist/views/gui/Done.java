package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.datastructures.SimpleArrayList;
import blaplafla.todolist.models.task.ListTask;
import blaplafla.todolist.models.task.MotherTask;
import blaplafla.todolist.models.task.Task;
import blaplafla.todolist.requests.RequestValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.net.URL;
import java.util.ResourceBundle;

public class Done
        extends ViewGui
        implements Initializable {
    static ListTask listTask;
    DictionaryController d = MainController.getInstance().dictionaryController();
    TaskController t = MainController.getInstance().taskController();
    RequestValidation r = MainController.getInstance().input();
    ObservableList<MotherTask> listtask = FXCollections.observableArrayList();
    int page = 1;
    int max_page;

    @FXML
    private ListView<MotherTask> listViewTask;
    @FXML
    private Button refresh;
    @FXML
    private Button delete;
    @FXML
    private Button toggle;
    @FXML
    private Button detail;
    @FXML
    private Label undone;
    @FXML
    private Label next;
    @FXML
    private Label prev;
    @FXML
    private Label pageLabel;

    @Override
    public void run(Object... params) {
        if (params[0] instanceof ListTask)
            listTask = (ListTask) params[0];
        else {
            MainController.getInstance().returnCode(402);
            return;
        }
        show = true;
        jframe.setTitle(d.label("done-list-name") + listTask.getUsername());
        jframe.setSize(400, 800);
        fixedJFrame();
        int error = initFX("Done.fxml");
        if (error != 100)
            MainController.getInstance().returnCode(error);
        jframe.add(jfxPanel);
        jframe.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        undone.setText(d.label("done list-button"));
        refresh.setText(d.label("refresh"));
        delete.setText(d.label("delete-button"));
        toggle.setText(d.label("undone-button"));
        detail.setText(d.label("detail-button"));
        next.setText(d.label("next-button") + ">");
        prev.setText("<" + d.label("prev-button"));
        listViewTask.setItems(listtask);
        listViewTask.setCellFactory(param -> new DoneMotherTaskListCell());
        listViewTask.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        addData();
    }

    public void addData() {
        if (page > max_page) {
            page = 1;
        }
        listViewTask.getItems().clear();
        SimpleArrayList<Task> tasks = t.paginate(listTask.getDone(), 5, page);
        for (Task task : tasks) {
            listtask.add((MotherTask) task);
        }
        pageLabel.setText(d.label("page") + page);
    }

    public void delete() {
        if (listViewTask.getSelectionModel().getSelectedIndex() != -1) {
            t.deleteMotherTask(listtask.get(listViewTask.getSelectionModel().getSelectedIndex()));
            addData();
            max_page = t.paginateSize(listTask.getDone(), 5);
            MainController.getInstance().router().refresh();
        }
    }

    public void toggle() {
        if (listViewTask.getSelectionModel().getSelectedIndex() != -1) {
            t.toggleMotherTask(listtask.get(listViewTask.getSelectionModel().getSelectedIndex()));
            max_page = t.paginateSize(listTask.getDone(), 5);
            addData();
            MainController.getInstance().router().refresh();
        }
    }

    public void detail() {
        if (listViewTask.getSelectionModel().getSelectedIndex() != -1)
            t.detailMotherTask(listtask.get(listViewTask.getSelectionModel().getSelectedIndex()));
    }

    public void next() {
        if (page < max_page)
            page++;
        addData();
    }

    public void prev() {
        if (page > 1)
            page--;
        addData();
    }
}
