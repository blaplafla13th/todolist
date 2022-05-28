package blaplafla.todolist.views.gui;

import blaplafla.todolist.views.View;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class ViewGui implements View {
    protected JFrame jframe ;
    protected boolean show;
    protected JFXPanel jfxPanel ;

    public ViewGui() {
        this.jframe= new JFrame();
        this.show = false;
        this.jfxPanel = new JFXPanel();
    }

    @Override
    public void run(){}

    @Override
    public void run(Object... params){}

    abstract protected void initFX();

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
        jframe.setExtendedState(Frame.MAXIMIZED_BOTH);
        jframe.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        jframe.addWindowListener(new WindowAdapter() {
                                     @Override
                                     public void windowClosed(WindowEvent e) {
                                         super.windowClosed(e);
                                         show = false;
                                     }
                                 }
        );
        Platform.runLater(this::initFX);
    }
}
