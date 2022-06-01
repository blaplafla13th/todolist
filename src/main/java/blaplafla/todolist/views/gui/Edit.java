package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.task.Task;
import blaplafla.todolist.requests.RequestValidation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

@SuppressWarnings("deprecation")
public class Edit
        extends ViewGui
        implements Initializable {
    private static Task task;
    final DictionaryController d = MainController.getInstance().dictionaryController();
    final TaskController t = MainController.getInstance().taskController();
    final RequestValidation r = MainController.getInstance().input();
    @FXML
    private Label title;
    @FXML
    private Label desc;
    @FXML
    private Label deadline;
    @FXML
    private Label priority;
    @FXML
    private Label hour;
    @FXML
    private Label minute;
    @FXML
    private Label second;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea descField;
    @FXML
    private DatePicker endDay;
    @FXML
    private Spinner<Integer> endTimeHour;
    @FXML
    private Spinner<Integer> endTimeMinute;
    @FXML
    private Spinner<Integer> endTimeSecond;
    @FXML
    private TextField priorityField;
    @FXML
    private Button cancel;
    @FXML
    private Button ok;

    public static Task getTask() {
        return task;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        title.setText(d.label("placeholder-title"));
        desc.setText(d.label("placeholder-desc"));
        deadline.setText(d.label("placeholder-deadline"));
        priority.setText(d.label("placeholder-priority"));
        hour.setText(d.label("hour"));
        SpinnerValueFactory<Integer> hourFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
        SpinnerValueFactory<Integer> minuteFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        SpinnerValueFactory<Integer> secondFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        endTimeHour.setValueFactory(hourFactory);
        endTimeMinute.setValueFactory(minuteFactory);
        endTimeSecond.setValueFactory(secondFactory);
        minute.setText(d.label("minute"));
        second.setText(d.label("second"));
        ok.setText(d.label("ok"));
        cancel.setText(d.label("cancel"));
        titleField.setText(task.getTitle());
        descField.setText(task.getDescription());
        priorityField.setText(task.getPriority() + "");
        hourFactory.setValue(task.getDeadline().getHours());
        minuteFactory.setValue(task.getDeadline().getMinutes());
        secondFactory.setValue(task.getDeadline().getSeconds());
        endDay.setValue(
                task.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    @Override
    public void run(Object... params) {
        if (params[0] instanceof Task) {
            jframe.setTitle(d.label("edit-task"));
            task = (Task) params[0];
            show = true;
        }
        else {
            MainController.getInstance().returnCode(402);
            return;
        }
        jframe.setSize(1000, 400);
        fixedJFrame();
        int error = initFX("Edit.fxml");
        if (error != 100)
            MainController.getInstance().returnCode(error);
        jframe.add(jfxPanel);
        jframe.setVisible(true);
    }

    public void cancel() {
        close();
        ((ViewGui) MainController.getInstance().router().getEdit()).close();
    }

    public void apply() {
        LocalDate localDate = endDay.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        t.edit(task, r.inputString(titleField.getText()), r.inputString(descField.getText()),
                r.inputTime(date, (endTimeHour.getValue() + ":" + endTimeMinute.getValue() + ":" +
                                   endTimeSecond.getValue())),
                r.inputPositiveInteger(r.inputString(priorityField.getText())));

        close();
        ((ViewGui) MainController.getInstance().router().getEdit()).close();
        MainController.getInstance().router().refresh();
    }
}
