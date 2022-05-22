package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.*;
import blaplafla.todolist.models.datastructures.SimpleArrayList;
import blaplafla.todolist.models.task.*;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;

public class DoneCli implements View {


    DictionaryController d = MainController.getInstance().dictionaryController();
    TaskController t = MainController.getInstance().taskController();
    RequestValidation r = MainController.getInstance().input();
    SimpleArrayList<Task> tasks;
    boolean using = true;
    ListTask listTask;
    int page = 1;
    int max_page;

    @Override
    public void run() {
        listTask = MainController.getInstance().listTask();
        page = 1;
        max_page = t.paginateSize(listTask.getDone(), 3);
        while (using) {
            if (page > max_page) {
                page = 1;
            }
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println(d.label("done-list-name") + listTask.getUsername());
            tasks = t.paginate(listTask.getDone(), 3, page);
            int i = 0;
            if (!tasks.isEmpty())
                for (Task task : tasks) {
                    i++;
                    System.out.println(d.label("-"));
                    System.out.println(i + ". " + d.label("title") + task.getTitle());
                    System.out.println(d.label("desc") + task.getDescription());
                    System.out.println(d.label("subtask-total") + ((MotherTask) task).subTaskSize());
                    System.out.println(d.label("-"));
                }
            else
                System.out.println(d.label("-"));

            System.out.println(d.label("input-command"));
            execute(r.input());
        }
        using = true;
    }

    private void commandlist() {
        System.out.println(d.label("list command"));
        System.out.println("next :" + d.label("next-button"));//
        System.out.println("prev :" + d.label("prev-button"));//
        System.out.println("toggle :" + d.label("toggle-button"));//
        System.out.println("delete :" + d.label("delete-button"));//
        System.out.println("detail :" + d.label("detail-button"));//
        System.out.println("back :" + d.label("back-button"));//
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
            case "detail" -> {
                System.out.print(d.label("index") + ":");
                t.detailMotherTask((MotherTask) tasks.get(r.inputPositiveInteger(r.input(), tasks.size()) - 1));
            }
            case "delete" -> {
                System.out.print(d.label("index") + ":");
                t.deleteMotherTask((MotherTask) tasks.get(r.inputPositiveInteger(r.input(), tasks.size()) - 1));
                max_page = t.paginateSize(listTask.getUndone(), 3);
            }
            case "toggle" -> {
                System.out.print(d.label("index") + ":");
                t.toggleMotherTask((MotherTask) tasks.get(r.inputPositiveInteger(r.input(), tasks.size()) - 1));
                max_page = t.paginateSize(listTask.getUndone(), 3);
            }

            case "back" -> using = false;
            default -> {
                System.out.println(d.label("unknown-command"));
                MainController.getInstance().pause();
            }
        }
    }


}
