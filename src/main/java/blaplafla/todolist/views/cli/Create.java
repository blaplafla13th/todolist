package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.task.ListTask;
import blaplafla.todolist.models.task.MotherTask;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;

import java.util.Date;

public class Create implements View {
    final DictionaryController d = MainController.getInstance().dictionaryController();
    final TaskController t = MainController.getInstance().taskController();
    final RequestValidation r = MainController.getInstance().input();

    @Override
    public void run(Object... params) {
        if (params[0] instanceof MotherTask)
            System.out.println(d.label("create-sub"));
        else if (params[0] instanceof ListTask)
            System.out.println(d.label("create-task"));
        else {
            MainController.getInstance().returnCode(402);
            return;
        }
        System.out.println(d.label("placeholder-title"));
        String title = r.inputString(r.input());
        System.out.println(d.label("placeholder-desc"));
        String desc = r.inputString(r.input());
        System.out.println(d.label("placeholder-deadline"));
        System.out.println(d.label("input-date"));
        Date deadline = r.inputDate(r.input());
        System.out.println(d.label("input-time"));
        deadline = r.inputTime(deadline, r.input());
        System.out.println(d.label("placeholder-priority"));
        int priority = r.inputPositiveInteger(r.input());
        if (params[0] instanceof ListTask)
            t.create(title, desc, deadline, priority);
        else if (params[0] instanceof MotherTask motherTask)
            t.create(motherTask, title, desc, deadline, priority);
    }
}
