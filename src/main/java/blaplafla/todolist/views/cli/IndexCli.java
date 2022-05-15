package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.*;
import blaplafla.todolist.models.datastructure.SimpleArrayList;
import blaplafla.todolist.models.task.*;
import blaplafla.todolist.request.RequestValidation;
import blaplafla.todolist.views.View;


public class IndexCli implements View {
    int page = 1;
    int max_page = 0;
    DictionaryController d = MainController.getInstance().dictionaryController();
    TaskController t = MainController.getInstance().taskController();
    FileController f = MainController.getInstance().fileController();
    RequestValidation r = MainController.getInstance().input();

    @Override
    public void run(Object... params) {
        if (params[0] instanceof ListTask listTask) {
            max_page = t.paginateSize(listTask.getUndone(), 3);
            while (true) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println(d.label("todolist-name") + listTask.getUsername());
                SimpleArrayList<Task> tasks = t.paginate(listTask.getUndone(), 3, page);
                int i = 0;
                if (!tasks.isEmpty())
                    for (Task task : tasks) {
                        i++;
                        System.out.println(d.label("-"));
                        System.out.println((3 * (page - 1) + i) + ". " + d.label("title") + task.getTitle());
                        System.out.println(d.prettyTime(task.prettyTimer()));
                        System.out.println(d.label("desc") + task.getDescription());
                        System.out.println(d.label("-"));
                    }
                else
                    System.out.println(d.label("-"));

                if (!listTask.getDone().isEmpty()) {
                    System.out.println(d.label("last-done"));
                    System.out.println(listTask.getDone());
                }

                System.out.println(d.label("input-command"));
                execute(r.input());
            }
        } else d.errorExplain(402);
    }

    private void commandlist() {
        System.out.println(d.label("list command"));
        System.out.println("next :" + d.label("next-button"));//
        System.out.println("set username :" + d.label("set-username-button"));//
        System.out.println("set lang :" + d.label("set-lang-button"));//
        System.out.println("open file :" + d.label("open-button"));//
        System.out.println("save file :" + d.label("save-button"));//
        System.out.println("prev :" + d.label("prev-button"));//
        System.out.println("add :" + d.label("add-button"));
        System.out.println("delete :" + d.label("delete-button"));
        System.out.println("detail :" + d.label("detail-button"));
        System.out.println("toggle :" + d.label("toggle-button"));
        System.out.println("done list :" + d.label("done list-button"));
        System.out.println("undone list :" + d.label("undone list-button"));
        System.out.println("exit :" + d.label("exit-button"));
    }

    private void execute(String command) {
        switch (command) {
            case "help" -> {commandlist();
            MainController.getInstance().pause();}
            case "next" -> {
                if (page < max_page)
                    page++;
            }

            case "prev" -> {
                if (page > 1)
                    page--;
            }
            case "set username" -> {
                System.out.print(d.label("set-name") + ":");
                MainController.getInstance().listTask().setUsername(r.input());
            }
            case "set lang" -> d.changeLanguage();
            case "open file" -> f.openFile();
            case "save file" -> f.saveFile();
            default -> {
                System.out.println(d.label("unknown-command"));
                MainController.getInstance().pause();
            }
        }

    }
}
