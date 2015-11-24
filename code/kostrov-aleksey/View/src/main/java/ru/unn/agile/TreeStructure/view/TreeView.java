package ru.unn.agile.TreeStructure.view;
import ru.unn.agile.TreeStructure.viewmodel.TreeViewModel;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public class TreeView {
    private JPanel mainPanel;
    private JRadioButton insertRadioButton;
    private JRadioButton searchRadioButton;
    private JRadioButton truncateRadioButton;
    private JButton buttonDo;
    private JTextArea textError;
    private JTextField textKey;
    private JTextField textData;
    private TreeViewModel viewModel;
    private TreeViewModel.Operation operation;

    public TreeView(final TreeViewModel viewModel) {
        this.viewModel = viewModel;

        buttonDo.addActionListener(e -> {
            bind();
            viewModel.doOperation();
            backBind();
        });
        insertRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                insertRadioButton.setSelected(true);
                searchRadioButton.setSelected(false);
                truncateRadioButton.setSelected(false);
                operation = TreeViewModel.Operation.INSERT;
                backBind();
            }
        });

        searchRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                insertRadioButton.setSelected(false);
                searchRadioButton.setSelected(true);
                truncateRadioButton.setSelected(false);
                operation = TreeViewModel.Operation.SEARCH;
                backBind();
            }
        });

        truncateRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                insertRadioButton.setSelected(false);
                searchRadioButton.setSelected(false);
                truncateRadioButton.setSelected(true);
                operation = TreeViewModel.Operation.TRUNCATE;
                backBind();
            }
        });

        textKey.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                bind();
                backBind();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                bind();
                backBind();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
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
        textError.setText(viewModel.getErrorMessage());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TreeView");
        frame.setContentPane(new TreeView(new TreeViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
