package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.task.Task;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;

import java.util.Date;

public class Edit implements View {
    DictionaryController d = MainController.getInstance().dictionaryController();
    TaskController t = MainController.getInstance().taskController();
    RequestValidation r = MainController.getInstance().input();

    @Override
    public void run(Object... params) {
        if (params[0] instanceof Task task) {
            System.out.println(d.label("edit-task"));
            System.out.println(d.label("placeholder-title"));
            System.out.println(d.label("old-value") + ":" + task.getTitle());
            String title = r.input();
            System.out.println(d.label("placeholder-desc"));
            System.out.println(d.label("old-value") + ":" + task.getDescription());
            String desc = r.input();
            System.out.println(d.label("placeholder-deadline"));
            System.out.println(d.label("old-value") + ":" + task.getDeadlineTime());
            System.out.println(d.prettyTime(task.prettyTimer()));
            System.out.println(d.label("input-date"));
            Date deadline = r.inputDate(r.input());
            System.out.println(d.label("input-time"));
            deadline = r.inputTime(deadline, r.input());
            System.out.println(d.label("placeholder-priority"));
            System.out.println(d.label("old-value") + ":" + task.getPriority());
            int priority = r.inputPositiveInteger(r.input());
            t.edit(task, title, desc, deadline, priority);
        } else MainController.getInstance().returnCode(402);
    }
}
