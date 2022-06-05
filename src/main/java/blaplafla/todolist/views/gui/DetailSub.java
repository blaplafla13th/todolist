package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.task.MotherTask;
import blaplafla.todolist.models.task.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailSub
        extends ViewGui
        implements Initializable {
    private static MotherTask motherTask;
    private static Task task;
    final TaskController t = MainController.getInstance().taskController();
    final DictionaryController d = MainController.getInstance().dictionaryController();
    @FXML
    private Label taskName;
    @FXML
    private Label subtask;
    @FXML
    private Label deadline;
    @FXML
    private Label priority;
    @FXML
    private Label timeLeft;
    @FXML
    private Label desc;
    @FXML
    private Label detail;
    @FXML
    private ImageView edit;
    @FXML
    private ImageView toggle;
    @FXML
    private ImageView delete;
    @FXML
    private ImageView refresh;
    @FXML
    private ImageView back;
    @FXML
    private Menu task1;
    @FXML
    private MenuItem delete1;
    @FXML
    private MenuItem toggle1;
    @FXML
    private MenuItem edit1;

    public static MotherTask getMotherTask() {
        return motherTask;
    }

    public static void setMotherTask(MotherTask motherTask) {
        DetailSub.motherTask = motherTask;
    }

    public static Task getTask() {
        return task;
    }

    public static void setTask(Task task) {
        DetailSub.task = task;
    }

    @Override
    public void run(Object... params) {
        if (params[0] instanceof MotherTask motherTask && params[1] instanceof Task subtask) {
            task = subtask;
            DetailSub.motherTask = motherTask;
            jframe.setTitle(d.label("detail-button"));
            jframe.setSize(400, 800);
            fixedJFrame();
            int error = initFX("DetailSub.fxml");
            if (error != 100)
                MainController.getInstance().returnCode(error);
            jframe.add(jfxPanel);
            jframe.setVisible(true);
        }
        else
            MainController.getInstance().returnCode(402);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        task1.setText(d.label("tasks"));
        delete1.setText(d.label("delete-this-button"));
        toggle1.setText(d.label("toggle-this-button"));
        edit1.setText(d.label("edit-this-button"));
        taskName.setText(d.label("mother-task-name") + motherTask.getTitle());
        detail.setText(d.label("detail-button"));
        Tooltip.install(delete, new Tooltip(d.label("delete-button")));
        Tooltip.install(toggle, new Tooltip(d.label("toggle-button")));
        Tooltip.install(edit, new Tooltip(d.label("edit-this-button")));
        Tooltip.install(refresh, new Tooltip(d.label("refresh")));
        Tooltip.install(back, new Tooltip(d.label("back")));
        addData();
    }

    public void addData() {
        subtask.setText(d.label("task-name") + task.getTitle());
        deadline.setText(d.label("deadline-time") + ":" + task.getDeadlineTime());
        priority.setText(d.label("priority") + task.getPriority());
        if (!task.isStatus())
            timeLeft.setText(d.prettyTime(task.prettyTimer()));
        else
            timeLeft.setText(d.label("done-button"));
        desc.setText(d.label("desc") + task.getDescription());
    }

    public void back() {
        this.close();
        ((ViewGui) MainController.getInstance().router().getDetailSubTask()).close();
    }

    public void edit() {
        t.edit(task);
    }

    public void delete() {
        t.deleteSubTask(motherTask, task);
        close();
        ((ViewGui) MainController.getInstance().router().getDetailSubTask()).close();
        MainController.getInstance().router().refresh();
    }

    public void toggle() {
        t.toggleSubTask(motherTask, task);
        addData();
        MainController.getInstance().router().refresh();
    }
}
