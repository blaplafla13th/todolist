package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.task.MotherTask;
import blaplafla.todolist.models.task.Task;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;

public class DetailSub implements View {
    DictionaryController d = MainController.getInstance().dictionaryController();
    RequestValidation r = MainController.getInstance().input();
    TaskController t = MainController.getInstance().taskController();
    boolean using = true;
    MotherTask motherTask;
    Task task;

    @Override
    public void run(Object... params) {
        if (params[0] instanceof MotherTask motherTask && params[1] instanceof Task subtask) {
            this.task = subtask;
            this.motherTask = motherTask;
            while (using) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(d.label("mother-task-name") + motherTask.getTitle());
                System.out.println(d.label("task-name") + task.getTitle());
                System.out.println(d.label("-"));
                System.out.println(d.label("desc") + task.getDescription());
                System.out.println(d.label("deadline-time") + ":" + task.getDeadlineTime());
                if (!task.isStatus())
                    System.out.println(d.prettyTime(task.prettyTimer()));
                System.out.println(d.label("priority") + task.getPriority());
                System.out.println(d.label("-"));
                System.out.println(d.label("input-command"));
                execute(r.input());
            }
            using = true;
        } else
            MainController.getInstance().returnCode(402);

    }

    private void commandlist() {
        System.out.println(d.label("list command"));
        System.out.println("toggle :" + d.label("toggle-this-button"));//
        System.out.println("delete :" + d.label("delete-this-button"));//
        System.out.println("edit :" + d.label("edit-this-button"));//
        System.out.println("back :" + d.label("back-button"));//
    }

    private void execute(String command) {
        switch (command) {
            case "help" -> {
                commandlist();
                MainController.getInstance().pause();
            }
            case "delete" -> {
                t.deleteSubTask(motherTask, task);
                using = false;
            }
            case "toggle" -> t.toggleSubTask(motherTask, task);

            case "edit" -> t.edit(task);

            case "back" -> using = false;
            default -> {
                System.out.println(d.label("unknown-command"));
                MainController.getInstance().pause();
            }
        }
    }
}
