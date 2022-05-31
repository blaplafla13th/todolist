package blaplafla.todolist.views.gui;

import blaplafla.todolist.views.View;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;

public abstract class ViewGui implements View {
    protected JFrame jframe;
    protected boolean show;
    protected JFXPanel jfxPanel;

    public ViewGui() {
        jframe = new JFrame();
        show = false;
        jfxPanel = new JFXPanel();
    }

    @Override
    public void run() {
    }

    @Override
    public void run(Object... params) {
    }

    protected int initFX(String fileName) {
        try {
            if (fileName == null)
                return 402;
            URL url = getClass().getResource(fileName);
            if (url == null)
                return 202;
            Parent root = FXMLLoader.load(url);
            Scene scene = new Scene(root);
            jfxPanel.setScene(scene);
            return 100;
        } catch (IOException exc) {
            return 302;
        }
    }

    public boolean isShow() {
        return show;
    }

    public void close() {
        jframe.setVisible(false);
        jframe.remove(jfxPanel);
        show = false;
    }

    public void fixedJFrame() {
        jframe.setLayout(new BorderLayout());
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
//        jframe.setExtendedState(Frame.MAXIMIZED_BOTH);
        jframe.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));
        jframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        jframe.addWindowListener(new WindowAdapter() {
                                     @Override
                                     public void windowClosing(WindowEvent e) {
                                         super.windowClosing(e);
                                         show = false;
                                     }

                                     public void windowClosed(WindowEvent e) {
                                         super.windowClosed(e);
                                         show = false;
                                     }
                                 }
        );
    }
}
