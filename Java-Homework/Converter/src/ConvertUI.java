import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ConvertUI extends JFrame {
    private static final String BASE_STRING[] = new String[] {
            "二进制",
            "八进制",
            "十进制",
            "十六进制",
    };
    private static final int BASE_INT[] = new int[] {
            2,
            8,
            10,
            16,
    };
    private static final int DEFAULT_BASE_INDEX = 2;

    private JComboBox inBase = new JComboBox(BASE_STRING);
    private JComboBox outBase = new JComboBox(BASE_STRING);
    private JTextField number = new JTextField();
    private JLabel result = new JLabel();

    public ConvertUI () {
        super("进制转换");
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        cs.fill = GridBagConstraints.HORIZONTAL;
        cs.insets = new Insets(5, 5, 5, 5);
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(new JLabel("整数："), cs);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 4;
        panel.add(number, cs);
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(new JLabel("转换："), cs);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(new JLabel("从"), cs);
        cs.gridx = 2;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(inBase, cs);
        cs.gridx = 3;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(new JLabel("到"), cs);
        cs.gridx = 4;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(outBase, cs);
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(new JLabel("结果："), cs);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 4;
        panel.add(result, cs);
        JButton okay = new JButton("确定");
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    result.setText(Converter.convert(number.getText(),
                            BASE_INT[inBase.getSelectedIndex()],
                            BASE_INT[outBase.getSelectedIndex()]));
                } catch (NumberFormatException error) {
                    result.setText("错误！");
                }
            }
        });
        getRootPane().setDefaultButton(okay);
        JButton Cancel = new JButton("取消");
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConvertUI.this.close();
            }
        });
        JPanel bp = new JPanel();
        bp.add(okay);
        bp.add(Cancel);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
        pack();
        setResizable(false);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) ((dimension.getWidth() - getWidth()) / 2), (int) ((dimension.getHeight() - getHeight()) / 2));
        inBase.setSelectedIndex(DEFAULT_BASE_INDEX);
        outBase.setSelectedIndex(DEFAULT_BASE_INDEX);
    }
    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }
    public static void main(String[] args) {
        ConvertUI convertUI = new ConvertUI();
        convertUI.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        convertUI.setVisible(true);
    }
}
