module blaplafla.todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;

    opens blaplafla.todolist.views.gui to javafx.fxml;
    exports blaplafla.todolist.views.gui;
    opens blaplafla.todolist to javafx.fxml;
    exports blaplafla.todolist;
    opens blaplafla.todolist.models.task to javafx.fxml;
    exports blaplafla.todolist.models.task;
}
