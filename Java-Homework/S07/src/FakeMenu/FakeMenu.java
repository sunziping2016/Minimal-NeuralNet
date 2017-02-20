package FakeMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Created by sun on 4/13/16.
 *
 * Fake menu.
 */
public class FakeMenu extends JFrame {
    public FakeMenu() {
        super("Fake menu");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JTextField textField = new JTextField();
        textField.setEditable(false);
        add(textField);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menuItem = new JMenuItem("New");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(e -> {
            textField.setText("Create a new file.");
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Open");
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(e -> {
            textField.setText("Open an existed file.");
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Save");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(e -> {
            textField.setText("Save the file.");
        });
        menu.add(menuItem);
        menuBar.add(menu);
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        menuItem = new JMenuItem("Copy");
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(e -> {
            textField.setText("Copy the selected text.");
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Cut");
        menuItem.setMnemonic(KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(e -> {
            textField.setText("Cut the selected text.");
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Paste");
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        menuItem.addActionListener(e -> {
            textField.setText("Paste the text.");
        });
        menu.add(menuItem);
        menuBar.add(menu);
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menuItem = new JMenuItem("About");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.addActionListener(e -> {
            textField.setText("About.");
        });
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        setSize(260, 160);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height/2 - getSize().height/2);
    }

    public static void main(String[] args) {
        JFrame app = new FakeMenu();
        app.setVisible(true);
    }
}
