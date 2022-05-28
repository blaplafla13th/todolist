package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.models.dictionary.Dictionary;
import blaplafla.todolist.requests.RequestValidation;
import blaplafla.todolist.views.View;


public class SetLanguage implements View {

    public void run() {
        DictionaryController d = MainController.getInstance().dictionaryController();
        RequestValidation r = MainController.getInstance().input();

        System.out.println(d.label("current-lang") + d.getDictionary());
        System.out.println(d.label("set-lang"));
        int code = d.loadLanguage();
        if (code != 100) {
            MainController.getInstance().returnCode(code);
        }
        int i = 1;
        for (Dictionary dic : d.getLanglist()) {
            System.out.println(i++ + ". " + dic);
        }
        System.out.println(d.label("input-num-option"));
        code = d.setDictionary(r.inputInteger(r.input()) - 1);
        if (code != 100)
            MainController.getInstance().returnCode(code);
        System.out.println(d.label("current-lang") + d.getDictionary());
        r.reset();
        MainController.getInstance().pause();
    }
}
