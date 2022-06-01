package blaplafla.todolist.requests;

import blaplafla.todolist.controllers.MainController;

import java.util.Date;

@SuppressWarnings("deprecation")
public class JavaFXValidation
        extends RequestValidation {
    @Override
    public String input() {
        return null;
    }

    @Override
    public Integer inputInteger(String in) {
        try {
            return Integer.parseInt(in);
        }
        catch (NumberFormatException e) {
            MainController.getInstance().returnCode(500);
            return 0;
        }
    }

    @Override
    public Integer inputPositiveInteger(String in) {
        try {
            if (Integer.parseInt(in) > 0)
                return Integer.parseInt(in);
            throw new NumberFormatException();
        }
        catch (NumberFormatException e) {
            MainController.getInstance().returnCode(500);
            return 1;
        }
    }

    @Override
    public Integer inputPositiveInteger(String in, int max) {
        try {
            if (Integer.parseInt(in) > 0 && Integer.parseInt(in) <= max)
                return Integer.parseInt(in);
            throw new NumberFormatException();
        }
        catch (NumberFormatException e) {
            MainController.getInstance().returnCode(500);
            return max;
        }
    }

    @Override
    public String inputString(String temp) {
        temp = reformat(temp);
        if (temp.equals("") || temp.equals(" ")) {
            MainController.getInstance().returnCode(500);
            return "Lorem Ipsum";
        }
        else
            return temp;
    }

    @Override
    public Date inputDate(String in) {
        try {
            String[] splitted = in.split("-");
            if (splitted.length != 3)
                throw new NumberFormatException();
            int year = Integer.parseInt(splitted[0]);
            int month = Integer.parseInt(splitted[1]);
            int day = Integer.parseInt(splitted[2]);
            if (isValidDate(day, month, year))
                return new Date(year - 1900, month - 1, day);
            else
                throw new NumberFormatException();
        }
        catch (NumberFormatException e) {
            MainController.getInstance().returnCode(500);
            return new Date(122, 1, 1);
        }
    }

    @Override
    public Date inputTime(Date date, String in) {
        if (date == null) {
            date = new Date();
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
            }
            else
                throw new NumberFormatException();
        }
        catch (NumberFormatException e) {
            System.out.println(dictionaryController.errorExplain(503));
            System.out.println(dictionaryController.label("def") + "0:0:0");
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            return date;
        }
    }
}
