package blaplafla.todolist;

import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.routers.CliRouter;
import blaplafla.todolist.routers.GuiRouter;

import javax.swing.*;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Starter {
    public static void main(String[] args) {
        String mode = "null";
        String file = "null";
        if (args != null) {
            if (args.length >= 1)
                mode = args[0];
            if (args.length >= 2)
                file = args[1];
        }
        if (!file.equals("null"))
            MainController.getInstance().fileController().setFile(file);
        setMode(mode);
        if (MainController.getInstance().router() instanceof GuiRouter)
            SwingUtilities.invokeLater(() -> {
                ((GuiRouter) MainController.getInstance().router()).getSystemTrayIcon().run();
                MainController.getInstance().router().getIndex().run();
            });
        else if (MainController.getInstance().router() instanceof CliRouter) {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            MainController.getInstance().router().getIndex().run();
        }
    }

    private static void setMode(String mode) {
        switch (mode) {
            case "gui" -> MainController.getInstance().setModeGui();
            case "cli" -> MainController.getInstance().setModeCli();
            default -> {
                if (System.console() != null) {
                    MainController.getInstance().setModeCli();
                }
                else
                    MainController.getInstance().setModeGui();
            }
        }
    }
}
