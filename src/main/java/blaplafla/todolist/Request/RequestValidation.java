package blaplafla.todolist.Request;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;

import java.util.Date;

@SuppressWarnings("deprecation")
public abstract class RequestValidation {

    protected DictionaryController dictionaryController = MainController.getInstance().getDictionaryController();
    public abstract String input();
    public Integer inputInteger(String in) {
        try {
            return Integer.parseInt(in);
        } catch (NumberFormatException e) {
            System.out.println(dictionaryController.errorExplain(500));
            System.out.println(dictionaryController.label("def") + 0);
            return 0;
        }
    }

    public void reset() {
        this.dictionaryController = MainController.getInstance().getDictionaryController();
    }

    public Integer inputPositiveInteger(String in) {
        try {
            if (Integer.parseInt(in) >= 0)
                return Integer.parseInt(in);
            throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println(dictionaryController.errorExplain(500));
            System.out.println(dictionaryController.label("def") + 1);
            return 1;
        }
    }

    public String inputString(String temp) {
        if (temp.equals("") || temp.equals(" ")) {
            System.out.println(dictionaryController.errorExplain(500));
            return "Lorem Ipsum";
        } else return temp;
    }

    public Date inputDate(String in) {
        try {
            String[] splitted = in.split("-");
            if (splitted.length != 3)
                throw new NumberFormatException();
            int year = Integer.parseInt(splitted[0]);
            int month = Integer.parseInt(splitted[1]);
            int day = Integer.parseInt(splitted[2]);
            if (isValidDate(day, month, year))
                return new Date(year - 1900, month, day);
            else
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println(dictionaryController.errorExplain(502));
            System.out.println(dictionaryController.label("def") + "2022-01-01");
            return new Date(122, 1, 1);
        }
    }

    public Date inputTime(Date date, String in) {
        if (date == null) {
            date = inputDate(input());
        }
        try {
            String[] splitted = in.split(":");
            if (splitted.length != 3)
                throw new NumberFormatException();
            int hour = Integer.parseInt(splitted[0]);
            int minute = Integer.parseInt(splitted[1]);
            int second = Integer.parseInt(splitted[2]);
            if (isValidTime(hour, minute, second)) {
                date.setHours(hour);
                date.setMinutes(minute);
                date.setSeconds(second);
                return date;
            } else
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            System.out.println(dictionaryController.errorExplain(503));
            System.out.println(dictionaryController.label("def") + "0:0:0");
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            return date;
        }
    }

    private boolean isValidTime(int hour, int minute, int second) {
        return hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59;
    }

    private boolean isLeap(int year) {
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }

    private boolean isValidDate(int d, int m, int y) {
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
