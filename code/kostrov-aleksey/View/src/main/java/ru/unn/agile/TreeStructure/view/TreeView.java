package ru.unn.agile.TreeStructure.view;
import ru.unn.agile.TreeStructure.viewmodel.TreeViewModel;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public final class TreeView {
    private JPanel mainPanel;
    private JRadioButton insertRadioButton;
    private JRadioButton searchRadioButton;
    private JRadioButton truncateRadioButton;
    private JButton buttonDo;
    private JTextArea textError;
    private JTextField textKey;
    private JTextField textData;
    private TreeViewModel viewModel;
    private TreeViewModel.Operation operation = TreeViewModel.Operation.INSERT;

    private TreeView() { }
    private TreeView(final TreeViewModel viewModel) {
        this.viewModel = viewModel;

        buttonDo.addActionListener(e -> {
            bind();
            viewModel.doOperation();
            backBind();
        });
        insertRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                insertRadioButton.setSelected(true);
                searchRadioButton.setSelected(false);
                truncateRadioButton.setSelected(false);
                operation = TreeViewModel.Operation.INSERT;
                bind();
                backBind();
            }
        });

        searchRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                insertRadioButton.setSelected(false);
                searchRadioButton.setSelected(true);
                truncateRadioButton.setSelected(false);
                operation = TreeViewModel.Operation.SEARCH;
                bind();
                backBind();
            }
        });

        truncateRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                insertRadioButton.setSelected(false);
                searchRadioButton.setSelected(false);
                truncateRadioButton.setSelected(true);
                operation = TreeViewModel.Operation.TRUNCATE;
                bind();
                backBind();
            }
        });

        textKey.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                bind();
                backBind();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                bind();
                backBind();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                bind();
                backBind();
            }
        });
        bind();
        backBind();
    }

    private void bind() {
        viewModel.setKey(textKey.getText());
        viewModel.setData(textData.getText());
        viewModel.setOperation(operation);
    }

    private void backBind() {
        buttonDo.setEnabled(viewModel.isDoButtonEnabled());
        textData.setEnabled(viewModel.isDataTextFieldEnabled());
        textData.setText(viewModel.getSearchedData());
        textError.setText(viewModel.getErrorMessage());
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("TreeView");
        frame.setContentPane(new TreeView(new TreeViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
