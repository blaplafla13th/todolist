package blaplafla.todolist.views.gui;

import blaplafla.todolist.views.View;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

public abstract class ViewGui implements View {
    protected JFrame jframe = new JFrame();
    protected boolean show = false;
    protected JFXPanel jfxPanel = new JFXPanel();

    @Override
    public void run(){}

    @Override
    public void run(Object... params){}

    abstract protected void initFX();

    public boolean isShow() {
        return show;
    }

    public void close() {
        jframe.remove(jfxPanel);
        jframe.setVisible(false);
        show = false;
    }

    public void fixedJFrame() {
        jframe.setLayout(new BorderLayout());
        jframe.setResizable(false);
        jframe.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
}
