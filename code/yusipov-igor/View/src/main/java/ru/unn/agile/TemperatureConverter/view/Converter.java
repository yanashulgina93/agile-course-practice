package ru.unn.agile.TemperatureConverter.view;

import javax.swing.*;

public class Converter {
    private JComboBox comboBoxScales;
    private JTextField textFieldInput;
    private JTextField textFieldResult;
    private JButton buttonConvert;
    private JTextField textFieldStatus;
    private JPanel mainPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Converter");
        frame.setContentPane(new Converter().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
