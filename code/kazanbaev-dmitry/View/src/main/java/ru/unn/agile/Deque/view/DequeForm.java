package ru.unn.agile.Deque.view;

import javax.swing.*;

public class DequeForm {
    private JPanel mainPanel;
    private JTextField inputNumberText;
    private JButton pushFrontButton;
    private JButton pushBackButton;
    private JTable dequeTable;

    public static void main(String[] args) {
        JFrame frame = new JFrame("DequeForm");
        frame.setContentPane(new DequeForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        dequeTable = new JTable(new String[][]{{"1"},{"2"}}, new String[]{"Front"});
    }
}
