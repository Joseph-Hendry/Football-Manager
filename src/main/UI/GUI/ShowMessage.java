package main.UI.GUI;

import javax.swing.JOptionPane;

/**
 * A class to show a message to the user.
 */
public class ShowMessage {
    
    /**
     * Shows a message to the user.
     * 
     * @param message The message to show.
     */
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}