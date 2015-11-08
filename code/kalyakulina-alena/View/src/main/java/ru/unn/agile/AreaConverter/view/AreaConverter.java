package ru.unn.agile.AreaConverter.view;

import javax.swing.*;

public class AreaConverter {

    private JPanel mainPanel;
    private JTextField inputArea;
    private JComboBox from;
    private JButton convertButton;
    private JComboBox to;
    private JTextField resultArea;
    private JTextField status;

    public static void main(String[] args) {
        JFrame frame = new JFrame("AreaConverter");
        frame.setContentPane(new AreaConverter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
