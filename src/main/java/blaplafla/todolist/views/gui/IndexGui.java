package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.FileController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.datastructures.SimpleArrayList;
import blaplafla.todolist.models.task.ListTask;
import blaplafla.todolist.models.task.Task;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexGui extends Application implements View, Initializable {
    DictionaryController d = MainController.getInstance().dictionaryController();
    TaskController t = MainController.getInstance().taskController();
    FileController f = MainController.getInstance().fileController();
    RequestValidation r;
    ListTask listTask = MainController.getInstance().listTask();

    int page = 1;
    int max_page = t.paginateSize(listTask.getUndone(), 3);

    @FXML
    private Label undone;
    @FXML
    private MenuItem openfile;
    @FXML
    private MenuItem savefile;
    @FXML
    private MenuItem undonelist;
    @FXML
    private MenuItem donelist;
    @FXML
    private MenuItem addtask;
    @FXML
    private Menu task;
    @FXML
    private Button refresh;
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
    private TextField usernameField;

    ObservableList<Task> listtask = FXCollections.observableArrayList();


    @Override
    public void run() {
        r = MainController.getInstance().input();
        launch();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxm = getClass().getResource("IndexGui.fxml");
        FXMLLoader loader = new FXMLLoader(fxm);
        AnchorPane page = loader.load();
        Scene scene = new Scene(page);
        primaryStage.setTitle(d.label("todolist-name") + listTask.getUsername());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        task.setText(d.label("tasks"));
        undone.setText(d.label("undone list-button"));
        openfile.setText(d.label("open-button"));
        savefile.setText(d.label("save-button"));
        undonelist.setText(d.label("undone list-button"));
        donelist.setText(d.label("done list-button"));
        addtask.setText(d.label("add-button"));
        username.setText(d.label("set-username-button"));
        language.setText(d.label("set-lang-button"));
        refresh.setText(d.label("refresh"));
        listViewTask.setItems(listtask);
        listViewTask.setCellFactory(param -> new TaskListCell());
        next.setText(d.label("next-button"));
        prev.setText(d.label("prev-button"));
        usernameField.setVisible(false);
        addData();

    }

    public void addData() {
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
    }

    public void next() {
        if (page < max_page - 1)
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
        usernameField.setVisible(true);
        usernameField.setPromptText(d.label("set-name") + " " + d.label("old-value") + listTask.getUsername());
    }

    public void doneSetUsername() {
        usernameField.setVisible(false);
        MainController.getInstance().listTask().setUsername(usernameField.getText());
        Stage primStage = (Stage) usernameField.getScene().getWindow();
        primStage.setTitle(d.label("todolist-name") + listTask.getUsername());
    }
}
