package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.task.ListTask;
import blaplafla.todolist.models.task.MotherTask;
import blaplafla.todolist.request.RequestValidation;
import blaplafla.todolist.views.View;

import java.util.Date;

public class CreateCli implements View {
    DictionaryController d = MainController.getInstance().dictionaryController();
    TaskController t = MainController.getInstance().taskController();
    RequestValidation r = MainController.getInstance().input();

    @Override
    public void run(Object... params) {
        if (params[0] instanceof MotherTask)
            System.out.println(d.label("create-sub"));
        else if (params[0] instanceof ListTask)
            System.out.println(d.label("create-task"));
        System.out.println(d.label("placeholder-title"));
        String title = r.input();
        System.out.println(d.label("placeholder-desc"));
        String desc = r.input();
        System.out.println(d.label("placeholder-deadline"));
        Date deadline = r.inputTime(null, r.input());
        System.out.println(d.label("placeholder-priority"));
        int priority = r.inputPositiveInteger(r.input());
        if (params[0] instanceof ListTask)
            t.create(title, desc, deadline, priority);
        else if (params[0] instanceof MotherTask motherTask)
            t.create(motherTask, title, desc, deadline, priority);
    }
}
