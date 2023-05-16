package main.UI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import main.body.GameManager;

public abstract class Window {
    private JFrame frame;
    private final GameManager manager;

    /**
     * Create the application.
     */
    public Window(GameManager manager, String title) {
        this.manager = manager;
        initialize(title);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize(final String title) {
        this.frame = new JFrame();
        this.frame.setTitle(title);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                manager.onWindowClose();
            }
        });
    }

    
}
