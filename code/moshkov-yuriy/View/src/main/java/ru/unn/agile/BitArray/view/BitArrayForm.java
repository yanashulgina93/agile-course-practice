package ru.unn.agile.BitArray.view;

import ru.unn.agile.BitArray.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

        initArrayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        doOperationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        operationCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        sizeArrayTxt.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent event) {
                backBind();
                bind();
            }

            @Override
            public void removeUpdate(final DocumentEvent event) {
                backBind();
                bind();
            }

            @Override
            public void changedUpdate(final DocumentEvent event) {
                backBind();
                bind();
            }
        });
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("BitArrayForm");

        frame.setContentPane(new BitArrayForm(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        viewModel.setSizeArray(sizeArrayTxt.getText());
    }

    private void backBind() {
        doOperationBtn.setEnabled(viewModel.isDoOperationEnabled());
        initArrayBtn.setEnabled(viewModel.isInitArrayEnabled());
    }
}
