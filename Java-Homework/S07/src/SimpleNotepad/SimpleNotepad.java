package SimpleNotepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by sun on 4/13/16.
 *
 * Simple notepad.
 */
public class SimpleNotepad extends JFrame {
    private final static String appName = "Simple notepad";
    private JTextArea textArea = new JTextArea("", 0, 0);
    private String filename;

    public SimpleNotepad() {
        super(appName);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JScrollPane pane = new JScrollPane(textArea);
        getContentPane().add(pane);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu;
        JMenuItem menuItem;
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menuItem = new JMenuItem("New");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> {
            filename = null;
            textArea.setText("");
            update();
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Open...");
        menuItem.setMnemonic(KeyEvent.VK_O);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(SimpleNotepad.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    filename = file.getPath();
                    textArea.setText(new String(Files.readAllBytes(Paths.get(file.getPath())), "utf-8"));
                }
                catch(IOException error) {
                    filename = null;
                }
                update();
            }
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Save");
        menuItem.setMnemonic(KeyEvent.VK_S);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> {
            if (filename != null) {
                try {
                    Files.write(Paths.get(filename), textArea.getText().getBytes("utf-8"));
                }
                catch(IOException error) {
                    filename = null;
                }
            }
            else {
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showSaveDialog(SimpleNotepad.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        filename = file.getPath();
                        Files.write(Paths.get(filename), textArea.getText().getBytes("utf-8"));
                    }
                    catch (IOException error) {
                        filename = null;
                    }
                }
            }
            update();
        });
        menu.add(menuItem);
        menuItem = new JMenuItem("Save as...");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
        menuItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showSaveDialog(SimpleNotepad.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                try {
                    filename = file.getPath();
                    Files.write(Paths.get(filename), textArea.getText().getBytes("utf-8"));
                }
                catch (IOException error) {
                    filename = null;
                }
            }
            update();
        });
        menu.add(menuItem);
        menu.addSeparator();
        menuItem = new JMenuItem("Exit");
        menuItem.setMnemonic(KeyEvent.VK_X);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        menuItem.addActionListener(e -> {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        });
        menu.add(menuItem);
        menuBar.add(menu);
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);
        menuItem = new JMenuItem("About");
        menuItem.setMnemonic(KeyEvent.VK_A);
        menuItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "A simple notepad by Sun.");
        });
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        setSize(800, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height/2 - getSize().height/2);

        update();
    }

    public void update() {
        if (filename != null)
            setTitle(appName + " - " + filename.substring(filename.lastIndexOf(File.separatorChar) + 1));
        else
            setTitle(appName + " - " + "Untitled Document");
    }

    public static void main(String[] args) {
        SimpleNotepad app = new SimpleNotepad();
        app.setVisible(true);
    }
}
