package main.UI.GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class StringListDemo {

    private JFrame frame;
    private JLabel lblString;
    private String[] stringList = {"String 1", "String 2", "String 3", "String 4"};
    private int currentIndex = 0;

    public StringListDemo() {
        initialize();
        startTimer();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        lblString = new JLabel("");
        lblString.setHorizontalAlignment(SwingConstants.CENTER);
        lblString.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblString.setBounds(10, 80, 414, 44);
        frame.getContentPane().add(lblString);
    }

    private void startTimer() {
        Timer timer = new Timer(500, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < stringList.length) {
                    lblString.setText(stringList[currentIndex]);
                    currentIndex++;
                } else {
                    ((Timer)e.getSource()).stop(); // Stop the timer when all strings are displayed
                }
            }
        });
        timer.start();
    }

    public void show() {
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        StringListDemo demo = new StringListDemo();
        demo.show();
    }
}
