package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.*;
import blaplafla.todolist.models.task.*;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;

import java.util.Date;

public class Create implements View {
    DictionaryController d = MainController.getInstance().dictionaryController();
    TaskController t = MainController.getInstance().taskController();
    RequestValidation r = MainController.getInstance().input();

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
        String title = r.input();
        System.out.println(d.label("placeholder-desc"));
        String desc = r.input();
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