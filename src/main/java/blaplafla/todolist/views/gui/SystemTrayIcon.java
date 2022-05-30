package blaplafla.todolist.views.gui;

import blaplafla.todolist.controllers.DictionaryController;
import blaplafla.todolist.controllers.MainController;
import blaplafla.todolist.views.View;

import java.awt.*;

public class SystemTrayIcon implements View {
    DictionaryController d = MainController.getInstance().dictionaryController();
    final SystemTray tray = SystemTray.getSystemTray();
    final PopupMenu popupMenu = new PopupMenu();
    final Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png"));
    final TrayIcon trayIcon = new TrayIcon(image,"To-do List",popupMenu);
    final MenuItem start = new MenuItem();
    final MenuItem setLang = new MenuItem();
    final MenuItem exit = new MenuItem();
    boolean started;
    public void refresh(){
        start.setLabel(d.label("start-button"));
        setLang.setLabel(d.label("set-lang-button"));
        exit.setLabel(d.label("exit-button"));
    }
    @Override
    public void run() {
        if (SystemTray.isSupported() && !started) {
            started=true;
            refresh();
            start.addActionListener(actionEvent -> MainController.getInstance().indexView().run());
            setLang.addActionListener(actionEvent -> MainController.getInstance().setLanguageView().run());
            exit.addActionListener(actionEvent -> System.exit(100));
            popupMenu.add(start);
            popupMenu.add(setLang);
            popupMenu.addSeparator();
            popupMenu.add(exit);
            trayIcon.setImageAutoSize(true);
            try {
                tray.add(trayIcon);
            } catch (AWTException e) {
                MainController.getInstance().returnCode(303);
            }
        }
        else refresh();
    }

    @Override
    public void run(Object... params) {
        trayIcon.displayMessage("Notification","blabla",TrayIcon.MessageType.WARNING); // for arlam
    }
}
