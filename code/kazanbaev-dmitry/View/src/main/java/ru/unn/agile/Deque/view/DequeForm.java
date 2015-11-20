package ru.unn.agile.Deque.view;

import ru.unn.agile.Deque.viewmodel.DequeViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class DequeForm {
    private final DequeViewModel viewModel;

    private JPanel mainPanel;
    private JTextField inputNumber;
    private JButton pushFrontButton;
    private JButton pushBackButton;
    private JTable dequeTable;
    private JScrollPane mainScrollPane;
    private JButton popFrontButton;
    private JButton popBackButton;
    private JTextField outputText;
    private JButton clearButton;
    private JButton checkButton;

    private DequeForm() {
        viewModel = new DequeViewModel();

        inputNumber.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                backBinding();
                binding();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                backBinding();
                binding();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                backBinding();
                binding();
            }
        });

        pushFrontButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBinding();
                viewModel.pushFront();
                binding();
            }
        });

        pushBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBinding();
                viewModel.pushBack();
                binding();
            }
        });

        popFrontButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBinding();
                viewModel.popFront();
                binding();
            }
        });

        popBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBinding();
                viewModel.popBack();
                binding();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBinding();
                viewModel.clearDeque();
                binding();
            }
        });

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBinding();
                viewModel.doesDequeContain();
                binding();
            }
        });

        dequeTable = new JTable(new Object[][]{}, new Object[]{"DEQUE"});

        binding();
    }

    private void backBinding() {
        viewModel.setInputNumber(inputNumber.getText());
    }

    private void binding() {
        pushFrontButton.setEnabled(viewModel.isPushFrontButtonEnabled());
        pushBackButton.setEnabled(viewModel.isPushBackButtonEnabled());
        popFrontButton.setEnabled(viewModel.isPopFrontButtonEnabled());
        popBackButton.setEnabled(viewModel.isPopBackButtonEnabled());
        clearButton.setEnabled(viewModel.isClearButtonEnabled());
        checkButton.setEnabled(viewModel.isCheckButtonEnabled());
        outputText.setText(viewModel.getOutput());

        Object[][] values = new Integer[viewModel.getDequeSize()][1];
        int counter = 0;
        for (Object item : viewModel.dequeToArray()) {
            values[counter++][0] = item;
        }

        dequeTable = new JTable(values, new Object[]{"DEQUE"});
        dequeTable.setEnabled(false);
        mainScrollPane.setViewportView(dequeTable);
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("DequeForm");
        frame.setContentPane(new DequeForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
