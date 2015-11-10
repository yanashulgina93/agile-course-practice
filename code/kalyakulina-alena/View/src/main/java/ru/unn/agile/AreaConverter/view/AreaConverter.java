package ru.unn.agile.AreaConverter.view;

import javax.swing.*;

public final class AreaConverter {

    private JPanel mainPanel;
    private JTextField inputArea;
    private JComboBox from;
    private JButton convertButton;
    private JComboBox to;
    private JTextField resultArea;
    private JTextField status;

    private AreaConverter() { }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("AreaConverter");
        frame.setContentPane(new AreaConverter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
