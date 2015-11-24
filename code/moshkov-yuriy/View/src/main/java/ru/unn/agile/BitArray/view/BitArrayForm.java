package ru.unn.agile.BitArray.view;

import ru.unn.agile.BitArray.viewmodel.ViewModel;

import javax.swing.*;

/**
 * Created by ruymoshkov on 11/20/2015.
 */
public class BitArrayForm {
    private ViewModel viewModel;

    private JPanel mainPanel;
    private JTextField sizeArrayTxt;
    private JButton initArrayBtn;
    private JTable firstBitArrayTable;
    private JTable secondBitArrayTable;
    private JTable resultBitArrayTable;
    private JComboBox operationCombobox;
    private JButton doOperationBtn;
    private JTextPane logText;

    private BitArrayForm() { }

    private BitArrayForm(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("BitArrayForm");

        frame.setContentPane(new BitArrayForm(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
    }

    private void backBind() {
    }
}
