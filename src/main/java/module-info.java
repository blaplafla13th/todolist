module blaplafla.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    opens blaplafla.todolist.views.gui to javafx.fxml;
    exports blaplafla.todolist.views.gui;
}
