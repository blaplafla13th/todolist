package blaplafla.todolist.views.cli;

import blaplafla.todolist.controllers.DictionaryController;

import java.util.Scanner;

public class SetLanguage {
    public static void main(String[] args) {
        DictionaryController d = DictionaryController.getInstance();
        System.out.println(d.label("current-lang")+d.getDictionary());
        System.out.println(d.label("set-lang"));
        int code = DictionaryController.getInstance().getLanguageList();
        if (code != 100){
            System.out.println(d.errorExplain(code));
        }
        System.out.println(d.label("set-lang-term"));
        Scanner input = new Scanner(System.in);
        int i = 0;
        try{
            i = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e){
            System.out.println(d.errorExplain(500));
        }
        code = d.setDictionary(i);
        if (code != 100)
            System.out.println(d.errorExplain(code));
        System.out.println(d.label("current-lang")+d.getDictionary());

    }
}
