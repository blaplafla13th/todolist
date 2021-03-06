package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.FileController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.datastructures.SimpleArrayList;
import blaplafla.todolist.models.task.ListTask;
import blaplafla.todolist.models.task.Task;
import blaplafla.todolist.requests.RequestValidation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Index
        extends ViewGui
        implements Initializable {
    final DictionaryController d = MainController.getInstance().dictionaryController();
    final TaskController t = MainController.getInstance().taskController();
    final FileController f = MainController.getInstance().fileController();
    ListTask listTask = MainController.getInstance().listTask();
    RequestValidation r;
    int page = 1;
    int max_page = t.paginateSize(listTask.getUndone(), 5);
    ObservableList<Task> listtask = FXCollections.observableArrayList();
    @FXML
    private Menu file;
    @FXML
    private Label undone;
    @FXML
    private MenuItem openfile;
    @FXML
    private MenuItem savefile;
    @FXML
    private Menu task;
    @FXML
    private MenuItem undonelist;
    @FXML
    private MenuItem donelist;
    @FXML
    private MenuItem addtask;
    @FXML
    private ImageView refresh;
    @FXML
    private MenuItem username;
    @FXML
    private MenuItem language;
    @FXML
    private ListView<Task> listViewTask;
    @FXML
    private Label lastDone;
    @FXML
    private Label next;
    @FXML
    private Label prev;
    @FXML
    private Label cat;
    @FXML
    private TextField usernameField;
    @FXML
    private Label pageLabel;
    @FXML
    private ImageView plus;
    @FXML
    private ImageView plus1;

    @Override
    public void run() {
        show = true;
        jframe.setTitle(d.label("todolist-name") + listTask.getUsername());
        jframe.setSize(400, 800);
        fixedJFrame();
        int error = initFX("Index.fxml");
        if (error != 100)
            MainController.getInstance().returnCode(error);
        jframe.add(jfxPanel);
        jframe.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        r = MainController.getInstance().input();
        task.setText(d.label("tasks"));
        undone.setText(d.label("undone list-button"));
        openfile.setText(d.label("open-button"));
        savefile.setText(d.label("save-button"));
        undonelist.setText(d.label("undone list-button"));
        donelist.setText(d.label("done list-button"));
        addtask.setText(d.label("add-button"));
        username.setText(d.label("set-username-button"));
        language.setText(d.label("set-lang-button"));
        cat.setText(d.label("name"));
        usernameField.setText(listTask.getUsername());
        Tooltip.install(refresh, new Tooltip(d.label("refresh")));
        Tooltip.install(plus, new Tooltip(d.label("add-button")));
        Tooltip.install(plus1, new Tooltip(d.label("add-button")));
        listViewTask.setItems(listtask);
        listViewTask.setCellFactory(param -> new IndexMotherTaskListCell());
        next.setText(d.label("next-button") + ">");
        prev.setText("<" + d.label("prev-button"));
        addData();
    }

    public void addData() {
        max_page = t.paginateSize(listTask.getUndone(), 5);
        if (page > max_page) {
            page = 1;
        }
        listViewTask.getItems().clear();
        if (!listTask.getDone().isEmpty())
            lastDone.setText(d.label("last-done") + listTask.getLastDoneTask().getTitle());
        else
            lastDone.setVisible(false);
        SimpleArrayList<Task> tasks = t.paginate(listTask.getUndone(), 5, page);
        for (Task task : tasks) {
            listtask.add(task);
        }
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

    public void setLanguage() {
        d.changeLanguage();
    }

    public void openFile() {
        f.openFile();
        listTask = MainController.getInstance().listTask();
        max_page = t.paginateSize(listTask.getUndone(), 5);
    }

    public void saveFile() {
        f.saveFile();
    }

    public void add() {
        t.create();
        max_page = t.paginateSize(listTask.getUndone(), 5);
    }

    public void doneList() {
        t.listDone();
        max_page = t.paginateSize(listTask.getUndone(), 5);
    }

    public void undoneList() {
        t.listUndone();
        max_page = t.paginateSize(listTask.getUndone(), 5);
    }

    public void setUsername() {
        usernameField.setEditable(true);
    }

    public void doneSetUsername() {
        usernameField.setEditable(false);
        MainController.getInstance().listTask().setUsername(r.reformat(usernameField.getText()));
        MainController.getInstance().indexView().run();
    }
}
