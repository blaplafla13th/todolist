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

public class Detail
        extends ViewGui
        implements Initializable {
    private static MotherTask motherTask;
    final DictionaryController d = MainController.getInstance().dictionaryController();
    final TaskController t = MainController.getInstance().taskController();
    int page = 1;
    int max_page;
    ObservableList<Task> listtask = FXCollections.observableArrayList();
    @FXML
    private Menu task;
    @FXML
    private MenuItem delete;
    @FXML
    private MenuItem toggle;
    @FXML
    private MenuItem edit;
    @FXML
    private Menu subtask;
    @FXML
    private MenuItem undoneList;
    @FXML
    private MenuItem doneList;
    @FXML
    private MenuItem addTask;
    @FXML
    private Label taskName;
    @FXML
    private ImageView refresh;
    @FXML
    private Label undone;
    @FXML
    private ListView<Task> listViewTask;
    @FXML
    private Label lastDone;
    @FXML
    private Label next;
    @FXML
    private Label prev;
    @FXML
    private Label detail;
    @FXML
    private Label deadline;
    @FXML
    private Label timeLeft;
    @FXML
    private Label priority;
    @FXML
    private Label desc;
    @FXML
    private Label pageLabel;
    @FXML
    private ImageView plus;
    @FXML
    private ImageView plus1;
    @FXML
    private ImageView editIcon;
    @FXML
    private ImageView toggleIcon;
    @FXML
    private ImageView deleteIcon;
    @FXML
    private ImageView back;

    public static MotherTask getMotherTask() {
        return motherTask;
    }

    @Override
    public void run(Object... params) {
        if (params[0] instanceof MotherTask task) {
            motherTask = task;
            show = true;
            jframe.setTitle(d.label("detail-button"));
            jframe.setSize(400, 800);
            fixedJFrame();
            int error = initFX("Detail.fxml");
            if (error != 100)
                MainController.getInstance().returnCode(error);
            jframe.add(jfxPanel);
            jframe.setVisible(true);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        task.setText(d.label("tasks"));
        delete.setText(d.label("delete-this-button"));
        toggle.setText(d.label("toggle-this-button"));
        edit.setText(d.label("edit-this-button"));
        subtask.setText(d.label("subtasks"));
        undoneList.setText(d.label("undone list-button"));
        doneList.setText(d.label("done list-button"));
        addTask.setText(d.label("add-subtask-button"));
        taskName.setText(d.label("task-name") + motherTask.getTitle());
        Tooltip.install(refresh, new Tooltip(d.label("refresh")));
        Tooltip.install(plus, new Tooltip(d.label("add-button")));
        Tooltip.install(plus1, new Tooltip(d.label("add-button")));
        Tooltip.install(deleteIcon, new Tooltip(d.label("delete-this-button")));
        Tooltip.install(toggleIcon, new Tooltip(d.label("toggle-this-button")));
        Tooltip.install(editIcon, new Tooltip(d.label("edit-this-button")));
        Tooltip.install(back, new Tooltip(d.label("back")));
        undone.setText(d.label("undone-sub-list") + " (" + motherTask.undoneSubTaskSize() + "): ");
        listViewTask.setItems(listtask);
        listViewTask.setCellFactory(param -> new IndexSubTaskListCell());
        next.setText(d.label("next-button") + ">");
        prev.setText("<" + d.label("prev-button"));
        detail.setText(d.label("detail-button"));
        deadline.setText(d.label("deadline-time") + motherTask.getDeadlineTime());
        if (!motherTask.isStatus())
            timeLeft.setText(d.prettyTime(motherTask.prettyTimer()));
        else
            timeLeft.setText("done-button");
        priority.setText(d.label("priority") + motherTask.getPriority());
        desc.setText(d.label("desc") + motherTask.getDescription());
        addData();
    }

    public void addData() {
        max_page = t.paginateSize(motherTask.getUndoneSubTask(), 5);
        if (page > max_page) {
            page = 1;
        }
        listViewTask.getItems().clear();
        if (!motherTask.getDoneSubTask().isEmpty())
            lastDone.setText(d.label("last-done") + motherTask.getLastDoneTask().getTitle());
        else
            lastDone.setVisible(false);
        SimpleArrayList<Task> tasks = t.paginate(motherTask.getUndoneSubTask(), 5, page);
        for (Task task : tasks) {
            listtask.add(task);
        }
        if (!motherTask.isStatus())
            timeLeft.setText(d.prettyTime(motherTask.prettyTimer()));
        else
            timeLeft.setText(d.label("done-button"));
        plus.setVisible(tasks.isEmpty());
        pageLabel.setText(d.label("page") + page);
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

    public void delete() {
        t.deleteMotherTask(motherTask);
        back();
        MainController.getInstance().router().refresh();
    }

    public void back() {
        this.close();
        ((ViewGui) MainController.getInstance().router().getDetail()).close();
    }

    public void toggle() {
        t.toggleMotherTask(motherTask);
        MainController.getInstance().router().refresh();
    }

    public void edit() {
        t.edit(motherTask);
    }

    public void add() {
        t.create(motherTask);
        max_page = t.paginateSize(motherTask.getUndoneSubTask(), 5);
    }

    public void doneList() {
        t.listDoneSub(motherTask);
    }

    public void undoneList() {
        t.listUndoneSub(motherTask);
    }

}
