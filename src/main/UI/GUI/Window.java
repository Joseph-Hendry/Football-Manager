package main.UI.GUI;

import main.body.GameManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * Defines common behaviour supported by a gui screen.
 */
public abstract class Window {

    // The frame for this screen
    private JFrame frame;

    // The rocket manager that this screen interacts with
    private final GameManager manager;

    /**
     * Creates this screen.
     *
     * @param title The title for the screen
     * @param manager The {@link RocketManager} that this screen interacts with
     */
    protected Window(final String title, final GameManager manager) {
        this.manager = manager;

        initialise(title);
    }

    /**
     * Initialises this screen's UI.
     */
    private void initialise(final String title) {
        frame = new JFrame();
        frame.setTitle(title);

        // Prevent the frame from closing immediately when the user clicks the close button.
        // Instead we add a WindowListener so we can tell our rocket manager that the user
        // has requested to quit. This allows the rocket manager to perform actions that may
        // be required before quitting E.g. Confirming the user really wants to quit,
        // saving state etc.
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                manager.quit();
            }
        });

        initialise(frame);

        // We set the location of our frame relative to null. This causes the frame to be placed
        // in the centre of the screen.
        frame.setLocationRelativeTo(null);
    }

    /**
     * Creates and adds the required graphical components to the given container.
     *
     * @param fame The container to add content to
     */
    protected abstract void initialise(JFrame frame);

    /**
     * Gets the top level component of this screen.
     *
     * @return The top level component
     */
    protected Component getParentComponent() {
        return frame;
    }

    /**
     * Gets the {@link GameManager} that this window supports.
     *
     * @return The manager for this window
     */
    protected GameManager getManager() {
        return manager;
    }

    /**
     * Shows this screen by making it visible.
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
     * Quits this screen. This should dispose of the screen as necessary.
     */
    void quit() {
        frame.dispose();
    }
}
