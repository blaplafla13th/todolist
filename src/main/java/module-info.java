module blaplafla.todolist {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens blaplafla.todolist to javafx.fxml;
    exports blaplafla.todolist;
}
