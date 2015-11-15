package ru.unn.agile.Deque.view;

import ru.unn.agile.Deque.viewmodel.DequeViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

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

        bind();
    }

    private void backBind() {
        viewModel.setInputNumber(inputNumberText.getText());
    }

    private void bind() {
        //inputNumberText.setText(viewModel.getInputNumber());
        pushFrontButton.setEnabled(viewModel.isPushFrontButtonEnabled());
        pushBackButton.setEnabled(viewModel.isPushBackButtonEnabled());
        popFrontButton.setEnabled(viewModel.isPopFrontButtonEnabled());
        popBackButton.setEnabled(viewModel.isPopBackButtonEnabled());
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("DequeForm");
        frame.setContentPane(new DequeForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
