package ColorWord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sun on 4/13/16.
 *
 * Changeable text color.
 */
public class ColorWord extends JFrame {
    public ColorWord() {
        super("Changeable static ");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.setText("Hello, world.");
        add(textField, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton blueButton = new JButton("Blue"), redButton = new JButton("Red");
        blueButton.addActionListener(e -> {
            textField.setForeground(Color.BLUE);
        });
        redButton.addActionListener(e -> {
            textField.setForeground(Color.RED);
        });
        buttonPanel.add(blueButton);
        buttonPanel.add(redButton);
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(260, 100);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height/2 - getSize().height/2);
    }

    public static void main(String[] args) {
        ColorWord app = new ColorWord();
        app.setVisible(true);
    }
}
