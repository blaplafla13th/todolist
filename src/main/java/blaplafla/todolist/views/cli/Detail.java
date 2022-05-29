package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.controllers.TaskController;
import blaplafla.todolist.models.datastructures.SimpleArrayList;
import blaplafla.todolist.models.task.MotherTask;
import blaplafla.todolist.models.task.Task;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;

public class Detail implements View {
    final DictionaryController d = MainController.getInstance().dictionaryController();
    final RequestValidation r = MainController.getInstance().input();
    final TaskController t = MainController.getInstance().taskController();
    int page = 1;
    int max_page;
    boolean using = true;
    MotherTask task;

    @Override
    public void run(Object... params) {
        if (params[0] instanceof MotherTask task) {
            this.task = task;
            max_page = t.paginateSize(task.getUndoneSubTask(), 3);
            while (using) {
                if (page > max_page) {
                    page = 1;
                }
                SimpleArrayList<Task> subtasks = t.paginate(task.getUndoneSubTask(), 3, page);
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(d.label("task-name") + task.getTitle());
                System.out.println(d.label("-"));
                System.out.println(d.label("desc") + task.getDescription());
                System.out.println(d.label("deadline-time") + ":" + task.getDeadlineTime());
                if (!task.isStatus())
                    System.out.println(d.prettyTime(task.prettyTimer()));
                System.out.println(d.label("priority") + task.getPriority());
                System.out.println(d.label("subtask-incomplete") + task.undoneSubTaskSize());
                System.out.println(d.label("-"));
                int i = 0;
                if (!subtasks.isEmpty())
                    for (Task subtask : subtasks) {
                        i++;
                        System.out.println(d.label("-"));
                        System.out.println((3 * (page - 1) + i) + ". " + d.label("title") + subtask.getTitle());
                        System.out.println(d.prettyTime(subtask.prettyTimer()));
                        System.out.println(d.label("desc") + subtask.getDescription());
                        System.out.println(d.label("-"));
                    }
                else
                    System.out.println(d.label("-"));
                if (!task.getDoneSubTask().isEmpty()) {
                    System.out.println(d.label("last-done"));
                    System.out.println(task.getLastDoneTask().getTitle());
                }
                System.out.println(d.label("input-command"));
                execute(r.input());
            }
            using = true;
        } else
            MainController.getInstance().returnCode(402);
    }

    private void commandlist() {
        System.out.println(d.label("list command"));
        System.out.println("next :" + d.label("next-button"));//
        System.out.println("prev :" + d.label("prev-button"));//
        System.out.println("add :" + d.label("add-subtask-button"));
        System.out.println("toggle :" + d.label("toggle-this-button"));//
        System.out.println("delete :" + d.label("delete-this-button"));//
        System.out.println("edit :" + d.label("edit-this-button"));//
        System.out.println("back :" + d.label("back-button"));//
        System.out.println("done list :" + d.label("done list-button"));
        System.out.println("undone list :" + d.label("undone list-button"));
    }

    private void execute(String command) {
        switch (command) {
            case "help" -> {
                commandlist();
                MainController.getInstance().pause();
            }
            case "next" -> {
                if (page < max_page)
                    page++;
            }
            case "prev" -> {
                if (page > 1)
                    page--;
            }
            case "delete" -> {
                t.deleteMotherTask(task);
                using = false;
            }
            case "toggle" -> t.toggleMotherTask(task);

            case "edit" -> t.edit(task);

            case "back" -> using = false;
            case "add" -> {
                t.create(task);
                max_page = t.paginateSize(task.getUndoneSubTask(), 3);
            }
            case "done list" -> {
                t.listDoneSub(task);
                max_page = t.paginateSize(task.getUndoneSubTask(), 3);
            }
            case "undone list" -> {
                t.listUndoneSub(task);
                max_page = t.paginateSize(task.getUndoneSubTask(), 3);
            }
            default -> {
                System.out.println(d.label("unknown-command"));
                MainController.getInstance().pause();
            }
        }
    }
}
