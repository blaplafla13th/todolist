package blaplafla.todolist.views;

public interface View {
    default void run(Object... params) {
        run();
    }

    default void run() {

    }
}
