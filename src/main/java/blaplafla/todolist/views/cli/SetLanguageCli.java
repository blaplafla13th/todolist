package blaplafla.todolist.views.cli;

import blaplafla.todolist.request.RequestValidation;
import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.views.View;


public class SetLanguageCli implements View {
    public SetLanguageCli() {
    }

    public void run() {
        DictionaryController d = MainController.getInstance().dictionaryController();
        RequestValidation i = MainController.getInstance().input();

        System.out.println(d.label("current-lang")+d.getDictionary());
        System.out.println(d.label("set-lang"));
        int code = d.getLanguageList();
        if (code != 100){
            System.out.println(d.errorExplain(code));
        }
        System.out.println(d.label("set-lang-term"));
        code = d.setDictionary(i.inputPositiveInteger(i.input()));
        if (code != 100)
            System.out.println(d.errorExplain(code));
        System.out.println(d.label("current-lang")+d.getDictionary());
        i.reset();
    }
}
