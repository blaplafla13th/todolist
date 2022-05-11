package blaplafla.todolist.views;

public interface View {
    default void run(){

    }

    default void run(String... params){
        run();
    }
}
