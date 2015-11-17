package ru.unn.agile.arabicroman.view;

import javax.swing.*;

public final class ArabicRomanConverterForm {
    private JTextField inputNumber;
    private JTextField outputNumber;
    private JButton convertButton;
    private JButton reverseButton;
    private JPanel mainPanel;
    private JLabel inputNumberFormat;
    private JLabel outputNumberFormat;

    private ArabicRomanConverterForm() {
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("ArabicRomanConverterForm");
        frame.setContentPane(new ArabicRomanConverterForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

