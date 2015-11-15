package ru.unn.agile.NewtonMethod.view;

import javax.swing.*;

public final class RootCalculator {
    private JPanel mainPanel;
    private JTextField textFieldLeftPoint;
    private JTextField textFieldRightPoint;
    private JTextField textFieldFunction;
    private JButton calculateButton;
    private JTextField textFieldResult;

    private RootCalculator() { }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("RootCalculator");
        frame.setContentPane(new RootCalculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
