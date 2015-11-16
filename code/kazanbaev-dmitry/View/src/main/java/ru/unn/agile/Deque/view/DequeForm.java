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
    private JTextField inputNumberText;
    private JButton pushFrontButton;
    private JButton pushBackButton;
    private JTable dequeTable;
    private JScrollPane mainScrollPane;
    private JButton popFrontButton;
    private JButton popBackButton;
    private JTextField outputText;
    private JButton clearButton;

    private DequeForm() {
        viewModel = new DequeViewModel();

        inputNumberText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                backBind();
                bind();
            }
        });

        pushFrontButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.pushFront();
                bind();
            }
        });

        pushBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.pushBack();
                bind();
            }
        });

        popFrontButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.popFront();
                bind();
            }
        });

        popBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.popBack();
                bind();
            }
        });

        dequeTable = new JTable(new Object[][]{}, new Object[]{"DEQUE"});

        bind();
    }

    private void backBind() {
        viewModel.setInputNumber(inputNumberText.getText());
    }

    private void bind() {
        pushFrontButton.setEnabled(viewModel.isPushFrontButtonEnabled());
        pushBackButton.setEnabled(viewModel.isPushBackButtonEnabled());
        popFrontButton.setEnabled(viewModel.isPopFrontButtonEnabled());
        popBackButton.setEnabled(viewModel.isPopBackButtonEnabled());
        outputText.setText(viewModel.getOutputNumber());

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
