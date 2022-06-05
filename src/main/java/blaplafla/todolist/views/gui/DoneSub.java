package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.datastructures.SimpleArrayList;
import blaplafla.todolist.models.task.MotherTask;
import blaplafla.todolist.models.task.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class DoneSub
        extends ViewGui
        implements Initializable {
    static MotherTask motherTask;
    DictionaryController d = MainController.getInstance().dictionaryController();
    TaskController t = MainController.getInstance().taskController();
    ObservableList<Task> listtask = FXCollections.observableArrayList();
    int page = 1;
    int max_page;

    @FXML
    private Menu task;
    @FXML
    private MenuItem delete1;
    @FXML
    private MenuItem toggle1;
    @FXML
    private MenuItem detail1;
    @FXML
    private ListView<Task> listViewTask;
    @FXML
    private ImageView refresh;
    @FXML
    private ImageView delete;
    @FXML
    private ImageView toggle;
    @FXML
    private ImageView detail;
    @FXML
    private ImageView back;
    @FXML
    private Label undone;
    @FXML
    private Label mainTaskName;
    @FXML
    private Label next;
    @FXML
    private Label prev;
    @FXML
    private Label pageLabel;

    public static MotherTask getMotherTask() {
        return motherTask;
    }

    @Override
    public void run(Object... params) {
        if (params[0] instanceof MotherTask)
            motherTask = (MotherTask) params[0];
        else {
            MainController.getInstance().returnCode(402);
            return;
        }
        show = true;
        jframe.setTitle(d.label("subtask-completed"));
        jframe.setSize(400, 800);
        fixedJFrame();
        int error = initFX("DoneSub.fxml");
        if (error != 100)
            MainController.getInstance().returnCode(error);
        jframe.add(jfxPanel);
        jframe.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        task.setText(d.label("tasks"));
        mainTaskName.setText(d.label("task-name") + motherTask.getTitle());
        undone.setText(d.label("done list-button") + ": ");
        Tooltip.install(refresh, new Tooltip(d.label("refresh")));
        Tooltip.install(delete, new Tooltip(d.label("delete-button")));
        Tooltip.install(toggle, new Tooltip(d.label("undone-button")));
        Tooltip.install(detail, new Tooltip(d.label("detail-button")));
        Tooltip.install(back, new Tooltip(d.label("back")));
        delete1.setText(d.label("delete-button"));
        toggle1.setText(d.label("undone-button"));
        detail1.setText(d.label("detail-button"));
        next.setText(d.label("next-button") + ">");
        prev.setText("<" + d.label("prev-button"));
        listViewTask.setItems(listtask);
        listViewTask.setCellFactory(param -> new DoneSubTaskListCell());
        listViewTask.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        addData();
    }

    public void addData() {
        max_page = t.paginateSize(motherTask.getDoneSubTask(), 5);
        if (page > max_page) {
            page = 1;
        }
        listViewTask.getItems().clear();
        SimpleArrayList<Task> tasks = t.paginate(motherTask.getDoneSubTask(), 5, page);
        for (Task task : tasks) {
            listtask.add(task);
        }
        pageLabel.setText(d.label("page") + page);
    }

    public void back() {
        this.close();
        ((ViewGui) MainController.getInstance().router().getDoneSubTask()).close();
    }

    public void delete() {
        if (listViewTask.getSelectionModel().getSelectedIndex() != -1) {
            t.deleteSubTask(motherTask,
                    listtask.get(listViewTask.getSelectionModel().getSelectedIndex()));
            addData();
            max_page = t.paginateSize(motherTask.getDoneSubTask(), 5);
            MainController.getInstance().router().refresh();
        }
    }

    public void toggle() {
        if (listViewTask.getSelectionModel().getSelectedIndex() != -1) {
            t.toggleSubTask(motherTask,
                    listtask.get(listViewTask.getSelectionModel().getSelectedIndex()));
            max_page = t.paginateSize(motherTask.getDoneSubTask(), 5);
            addData();
            MainController.getInstance().router().refresh();
        }
    }

    public void detail() {
        if (listViewTask.getSelectionModel().getSelectedIndex() != -1)
            t.detailSubTask(motherTask,
                    listtask.get(listViewTask.getSelectionModel().getSelectedIndex()));
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
