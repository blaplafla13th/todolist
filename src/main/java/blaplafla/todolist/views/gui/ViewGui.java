package blaplafla.todolist.views.gui;

import blaplafla.todolist.views.View;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public abstract class ViewGui implements View {
    protected JFrame jframe = new JFrame();
    protected boolean show = false;
    protected JFXPanel jfxPanel = new JFXPanel();

    @Override
    abstract public void run();

    abstract protected void initFX();

    public JFrame getJframe() {
        return jframe;
    }

    public boolean isShow() {
        return show;
    }

    public void close() {
        jframe.remove(jfxPanel);
        jframe.setVisible(false);
        show = false;
    }
    public void fixedJFrame(){
        jframe.setLayout(new BorderLayout());
        jframe.setResizable(false);
        jframe.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}
