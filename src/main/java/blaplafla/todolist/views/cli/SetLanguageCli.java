package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.*;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;


public class SetLanguageCli implements View {
    public SetLanguageCli() {
    }

    public void run() {
        DictionaryController d = MainController.getInstance().dictionaryController();
        RequestValidation i = MainController.getInstance().input();

        System.out.println(d.label("current-lang") + d.getDictionary());
        System.out.println(d.label("set-lang"));
        int code = d.getLanguageList();
        if (code != 100) {
            MainController.getInstance().returnCode(code);
        }
        System.out.println(d.label("set-lang-term"));
        code = d.setDictionary(i.inputInteger(i.input()));
        if (code != 100)
            MainController.getInstance().returnCode(code);
        System.out.println(d.label("current-lang") + d.getDictionary());
        i.reset();
        MainController.getInstance().pause();
    }
}
