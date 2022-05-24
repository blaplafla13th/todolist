package blaplafla.todolist;

import blaplafla.todolist.controllers.MainController;

public class Starter {
    public static void main(String[] args) {
        String mode = "null";
        String file = "null";
        if (args != null) {
            if (args.length >=1)
                mode = args[0];
            if (args.length >=2)
                file = args[1];
        }
        if (!file.equals("null"))
            MainController.getInstance().fileController().setFile(file);
        setMode(mode);
        MainController.getInstance().indexView().run();
    }

    private static void setMode(String mode) {
        switch (mode){
            case "gui" -> MainController.getInstance().setModeGui();
            case "cli" -> MainController.getInstance().setModeCli();
            default -> {
                if (System.console() != null){
                    MainController.getInstance().setModeCli();
                } else MainController.getInstance().setModeGui();
            }
        }
    }
}