package blaplafla.todolist.requests;

import java.util.Date;
import java.util.Scanner;

@SuppressWarnings({"deprecation", "MagicConstant"})
public class TerminalInputValidation
        extends RequestValidation {
    private final Scanner input;

    public TerminalInputValidation() {
        input = new Scanner(System.in);
    }

    public String input() {
        return reformat(input.nextLine());
    }

    @Override
    public Integer inputInteger(String in) {
        try {
            return Integer.parseInt(in);
        }
        catch (NumberFormatException e) {
            System.out.println(dictionaryController.errorExplain(500));
            System.out.println(dictionaryController.label("def") + 0);
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
            System.out.println(dictionaryController.errorExplain(500));
            System.out.println(dictionaryController.label("def") + 1);
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
            System.out.println(dictionaryController.errorExplain(500));
            System.out.println(dictionaryController.label("def") + 1);
            return 1;
        }
    }

    public String inputString(String temp) {
        temp = reformat(temp);
        if (temp.equals("") || temp.equals(" ")) {
            System.out.println(dictionaryController.errorExplain(500));
            System.out.println(dictionaryController.label("def"));
            return "Lorem Ipsum";
        }
        else
            return temp;
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
                return new Date(year - 1900, month - 1, day);
            else
                throw new NumberFormatException();
        }
        catch (NumberFormatException e) {
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
