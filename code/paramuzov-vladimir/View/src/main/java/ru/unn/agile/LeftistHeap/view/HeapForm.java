package ru.unn.agile.LeftistHeap.view;

import ru.unn.agile.LeftistHeap.viewmodel.LeftistHeapViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HeapForm {
    private LeftistHeapViewModel viewModel;
    private JPanel rootPanel;
    private JTextField textNumberToInsert;
    private JTextField textNumberToDelete;
    private JButton buttonInsert;
    private JButton buttonDelete;
    private JTextArea textError;
    private JTextArea textHeapContent;

    public HeapForm() {
        viewModel = new LeftistHeapViewModel();
        buttonInsert.addActionListener(e -> {
            backBind();
            viewModel.insertElement();
            bind();
        });

        buttonDelete.addActionListener(e -> {
            backBind();
            viewModel.deleteElement();
            bind();
        });


        textNumberToInsert.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                backBind();
                bind();
            }
        });

        textNumberToDelete.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                backBind();
                bind();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                backBind();
                bind();
            }
        });

        bind();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("HeapForm");
        frame.setContentPane(new HeapForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void backBind() {
        viewModel.setNumberToInsert(textNumberToInsert.getText());
        viewModel.setNumberToDelete(textNumberToDelete.getText());
    }

    private void bind() {
        buttonInsert.setEnabled(viewModel.isInsertButtonEnabled());
        buttonDelete.setEnabled(viewModel.isDeleteButtonEnabled());
        textHeapContent.setText(viewModel.getHeapContent());
        textError.setText(viewModel.getErrorText());
    }
}
