package blaplafla.todolist.controllers;

import blaplafla.todolist.models.dictionary.Dictionary;
import blaplafla.todolist.models.dictionary.Vietnamese;

public class DictionaryController {
    private static DictionaryController instance;
    private Dictionary dictionary;

    private DictionaryController() {
        dictionary = new Vietnamese();
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public static DictionaryController getInstance() {
        if (instance == null) {
            instance = new DictionaryController();
        }
        return instance;
    }

    public String errorExplain(int errorCode) {
        return dictionary.errorExplain(errorCode);
    }

    public String prettyTime(long day, long hour, long minute, long second) {
        return dictionary.prettyTime(day, hour, minute, second);
    }

    public String label(String label) {
        return dictionary.label(label);
    }
}
