package ru.unn.agile.BitArray.view;

import ru.unn.agile.BitArray.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
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
    private JComboBox<ViewModel.Operation> operationCombobox;
    private JButton doOperationBtn;
    private JTextPane logText;

    private BitArrayForm() { }

    private BitArrayForm(final ViewModel viewModel) {
        this.viewModel = viewModel;
        loadListOfOperations();
        backBind();

        initArrayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                viewModel.initArray();
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
                bind();
                backBind();
            }

            @Override
            public void removeUpdate(final DocumentEvent event) {
                bind();
                backBind();
            }

            @Override
            public void changedUpdate(final DocumentEvent event) {
                bind();
                backBind();
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
        viewModel.setOperation((ViewModel.Operation) operationCombobox.getSelectedItem());
    }

    private void loadListOfOperations() {
        ViewModel.Operation[] operations = ViewModel.Operation.values();
        operationCombobox.setModel(new JComboBox<>(operations).getModel());
    }

    private void backBind() {
        doOperationBtn.setEnabled(viewModel.isDoOperationEnabled());
        initArrayBtn.setEnabled(viewModel.isInitArrayEnabled());
        if (viewModel.gitFirstBitArray() != null) {
            Object[][] values = new Boolean[1][Array.getLength(viewModel.gitFirstBitArray().getBitArray())];
            Object[][] header = new String[1][Array.getLength(viewModel.gitFirstBitArray().getBitArray())];
            int counter = 0;
            for (Object item : viewModel.gitFirstBitArray().getBitArray()) {
                header[0][counter] = Integer.toString(counter);
                values[0][counter++] = item;
            }
            firstBitArrayTable = new JTable(values, header);
        }
    }
}
