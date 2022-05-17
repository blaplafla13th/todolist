package blaplafla.todolist.request;

import java.util.Scanner;

public class TerminalInputValidation extends RequestValidation {
    private final Scanner input;

    public TerminalInputValidation() {
        input = new Scanner(System.in);
    }

    public String input() {
        return input.nextLine().replaceAll("\\s+", " ").trim();
    }

}
