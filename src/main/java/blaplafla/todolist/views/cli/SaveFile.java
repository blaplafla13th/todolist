package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.FileController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;

public class SaveFile implements View {
    final DictionaryController d = MainController.getInstance().dictionaryController();
    final RequestValidation i = MainController.getInstance().input();
    final FileController f = MainController.getInstance().fileController();

    @Override
    public void run() {
        if (!f.hasFile()) {
            System.out.println(d.label("path-to-file"));
            String path = i.input();
            if (f.isFolder(path)) {
                System.out.println(d.label("not-file"));
                MainController.getInstance().pause();
                return;
            } else {
                int error = f.setFile(path);
                if (error != 100) {
                    MainController.getInstance().returnCode(error);
                }
            }
        }
        if (!f.isNullFile()) {
            System.out.println(d.label("warning-overwrite-data"));
            System.out.println(d.label("type-yes-to-continue"));
            if (i.input().equals("yes"))
                MainController.getInstance().returnCode(f.exportListTask());
        } else MainController.getInstance().returnCode(f.exportListTask());
        System.out.println(d.label("process-done"));
    }
}
