package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.views.View;

import java.awt.*;

public class SystemTrayIcon implements View {
    DictionaryController d = MainController.getInstance().dictionaryController();

    @Override
    public void run() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png"));
            PopupMenu popupMenu = new PopupMenu();
            MenuItem menuItem0 = new MenuItem(d.label("start-button"));
            menuItem0.addActionListener(actionEvent -> MainController.getInstance().indexView().run());
            MenuItem menuItem1 = new MenuItem(d.label("set-lang-button"));
            menuItem1.addActionListener(actionEvent -> MainController.getInstance().setLanguageView().run());
            MenuItem menuItem2 = new MenuItem(d.label("exit-button"));
            menuItem2.addActionListener(actionEvent -> System.exit(100));
            popupMenu.add(menuItem0);
            popupMenu.add(menuItem1);
            popupMenu.addSeparator();
            popupMenu.add(menuItem2);
            TrayIcon trayIcon = new TrayIcon(image);
            trayIcon.setToolTip("To-do List");
            trayIcon.setPopupMenu(popupMenu);
            trayIcon.setImage(image);
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                MainController.getInstance().returnCode(303);
            }
        }
    }
}
