package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.task.ListTask;
import blaplafla.todolist.models.task.MotherTask;
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

public class Create extends ViewGui implements Initializable {
    private static Object param0;
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
    }

    @Override
    public void run(Object... params) {
        if (params[0] instanceof MotherTask) {
            jframe.setTitle(d.label("create-sub"));
            param0 = params[0];
        } else if (params[0] instanceof ListTask) {
            jframe.setTitle(d.label("create-task"));
            param0 = params[0];
        } else {
            MainController.getInstance().returnCode(402);
            return;
        }
        jframe.setSize(1000, 400);
        fixedJFrame();
        int error = initFX("Create.fxml");
        if (error != 100)
            MainController.getInstance().returnCode(error);
        jframe.add(jfxPanel);
        jframe.setVisible(true);
    }

    public void cancel() {
        close();
        ((ViewGui) MainController.getInstance().router().getCreate()).close();
    }

    public void apply() {
        LocalDate localDate = endDay.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        if (param0 instanceof ListTask)
            t.create(r.inputString(titleField.getText()), r.inputString(descField.getText()),
                    r.inputTime(date, (endTimeHour.getValue() + ":" + endTimeMinute.getValue() + ":" + endTimeSecond.getValue())),
                    r.inputPositiveInteger(r.inputString(priorityField.getText())));
        else if (param0 instanceof MotherTask motherTask) {
            t.create(motherTask, r.inputString(titleField.getText()), r.inputString(descField.getText()),
                    r.inputTime(date, (endTimeHour.getValue() + ":" + endTimeMinute.getValue() + ":" + endTimeSecond.getValue())),
                    r.inputPositiveInteger(r.inputString(priorityField.getText())));
        }
        close();
        ((ViewGui) MainController.getInstance().router().getCreate()).close();
        MainController.getInstance().router().refresh();
    }
}
