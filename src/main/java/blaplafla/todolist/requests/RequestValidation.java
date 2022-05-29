package blaplafla.todolist.requests;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;

import java.util.Date;

public abstract class RequestValidation {

    protected DictionaryController dictionaryController = MainController.getInstance().dictionaryController();

    public abstract String input();

    public String reformat(String s){
        return s.replaceAll("\\s+", " ").trim();
    }

    public abstract Integer inputInteger(String in);
    public void reset() {
        dictionaryController = MainController.getInstance().dictionaryController();
    }

    public abstract Integer inputPositiveInteger(String in);

    public abstract Integer inputPositiveInteger(String in, int max);

    public abstract String inputString(String temp);

    public abstract Date inputDate(String in);

    public abstract Date inputTime(Date date, String in);

    protected boolean isValidTime(int hour, int minute, int second) {
        return !(hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59);
    }

    protected boolean isLeap(int year) {
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }

    protected boolean isValidDate(int d, int m, int y) {
        if (m < 1 || m > 12)
            return false;
        if (d < 1 || d > 31)
            return false;
        if (m == 2) {
            if (isLeap(y))
                return (d <= 29);
            else
                return (d <= 28);
        }
        if (m == 4 || m == 6 || m == 9 || m == 11)
            return (d <= 30);
        return true;
    }
}
