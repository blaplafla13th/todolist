package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.FileController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.request.RequestValidation;
import blaplafla.todolist.views.View;

public class SaveFileCli implements View {
    DictionaryController d = MainController.getInstance().dictionaryController();
    RequestValidation i = MainController.getInstance().input();
    FileController f = MainController.getInstance().fileController();

    @Override
    public void run() {
        if (!f.isNullFile()) {
            System.out.println(d.label("warning-overwrite-data"));
            System.out.println(d.label("type-yes-to-continue"));
            if (i.input().equals("yes"))
                MainController.getInstance().returnCode(f.exportListTask());
        } else MainController.getInstance().returnCode(f.exportListTask());
        System.out.println(d.label("process-done"));
    }
}
