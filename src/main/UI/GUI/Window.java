package main.UI.GUI;

import main.body.GameManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Abstract class for all the menu windows.
 */
public abstract class Window {

    // The frame for this window
    private JFrame frame;

    // The manager for this window
    private final GameManager manager;

    /**
     * Creates this window.
     *
     * @param title The title for the window
     * @param manager The manager for the windows {@link GameManager}
     */
    protected Window(final String title, final GameManager manager) {
        this.manager = manager;

        initialise(title);
    }

    /**
     * Initialises the window.
     */
    private void initialise(final String title) {
        frame = new JFrame();
        frame.setTitle(title);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                manager.quit();
            }
        });

        initialise(frame);

        // Centre the window in middle of screen
        frame.setLocationRelativeTo(null);
    }

    /**
     * Creates the abstract method initialise for setting up the window.
     *
     * @param fame The frame to add the content to
     */
    protected abstract void initialise(JFrame frame);

    /**
     * Gets the windows {@link GameManager}.
     *
     * @return The game manager
     */
    protected GameManager getManager() {
        return manager;
    }

    /**
     * Shows the window.
     */
    protected void show() {
        frame.setVisible(true);
    }

    /**
     * Confirms if the user wants to quit this screen.
     *
     * @return true to quit, false otherwise
     */
    protected boolean confirmQuit() {
        int selection = JOptionPane.showConfirmDialog(frame, "Are you sure you want to quit?",
                "Quit?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (selection == JOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }

    /**
     * Quits this instance of the window.
     */
    void quit() {
        frame.dispose();
    }
}
