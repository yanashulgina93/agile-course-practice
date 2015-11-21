package ru.unn.agile.BitArray.view;

import javax.swing.*;

/**
 * Created by ruymoshkov on 11/20/2015.
 */
public class BitArrayForm {
    private ViewModel viewModel;

    private JPanel mainPanel;
    private JTextField sizeArrayTxtFld;
    private JButton initArrayBtn;
    private JTable bitArrayFirstTable;
    private JTable bitArraySecondTable;

    private BitArrayForm() { }

    private BitArrayForm(final ViewModel viewModel) {
        this.viewModel = viewModel;
//        backBind();
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("BitArrayForm");

        frame.setContentPane(new Calculator(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
