package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.FileController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;

public class OpenFile
        implements View {
    final DictionaryController d = MainController.getInstance().dictionaryController();
    final RequestValidation i = MainController.getInstance().input();
    final FileController f = MainController.getInstance().fileController();

    @Override
    public void run() {
        System.out.println(d.label("path-to-file"));
        String path = i.input();
        if (f.isFolder(path)) {
            System.out.println(d.label("not-file"));
            MainController.getInstance().pause();
            return;
        }
        else {
            int error = f.setFile(path);
            if (error != 100) {
                d.errorExplain(error);
                MainController.getInstance().pause();
                return;
            }
        }
        if (!f.isNullFile() && MainController.getInstance().listTask().isEmpty()) {
            MainController.getInstance().returnCode(f.importListTask());
        }
        else if (!f.isNullFile() && !MainController.getInstance().listTask().isEmpty()) {
            System.out.println(d.label("warning-load-data"));
            System.out.println(d.label("type-yes-to-continue"));
            if (MainController.getInstance().input().input().equals("yes")) {
                MainController.getInstance().returnCode(f.importListTask());
            }
        }
    }
}
