package ru.unn.agile.TreeStructure.view;
import ru.unn.agile.TreeStructure.viewmodel.TreeViewModel;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;

public final class TreeView {
    private JPanel mainPanel;
    private ButtonGroup operationButtonGroup;
    private JRadioButton insertRadioButton;
    private JRadioButton searchRadioButton;
    private JRadioButton truncateRadioButton;
    private JButton buttonDo;
    private JTextArea textError;
    private JTextField textKey;
    private JTextField textDataFromNode;
    private TreeViewModel viewModel;
    private String operation;

    private TreeView() { }
    private TreeView(final TreeViewModel viewModel) {
        this.viewModel = viewModel;
        operation = "Insert";
        operationButtonGroup = new ButtonGroup();
        operationButtonGroup.add(insertRadioButton);
        operationButtonGroup.add(searchRadioButton);
        operationButtonGroup.add(truncateRadioButton);
        operationButtonGroup.setSelected(insertRadioButton.getModel(), true);
        insertRadioButton.setActionCommand("Insert");
        searchRadioButton.setActionCommand("Search");
        truncateRadioButton.setActionCommand("Truncate");

        buttonDo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                treeViewBind();
                TreeView.this.viewModel.doOperation();
                treeViewBackBind();
            }
        });

        insertRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                treeViewBind();
                treeViewBackBind();
            }
        });

        searchRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                treeViewBind();
                treeViewBackBind();
            }
        });

        truncateRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                treeViewBind();
                treeViewBackBind();
            }
        });

        textKey.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(final DocumentEvent e) {
                treeViewBind();
                treeViewBackBind();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                treeViewBind();
                treeViewBackBind();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                treeViewBind();
                treeViewBackBind();
            }
        });
        treeViewBind();
        treeViewBackBind();
    }

    private void treeViewBind() {
        operation = operationButtonGroup.getSelection().getActionCommand();
        viewModel.setOperation(operation);
        viewModel.setKey(textKey.getText());
        viewModel.setDataFromNode(textDataFromNode.getText());
    }

    private void treeViewBackBind() {
        buttonDo.setEnabled(viewModel.isDoButtonEnabled());
        textDataFromNode.setEnabled(viewModel.isDataTextFieldEnabled());
        textDataFromNode.setText(viewModel.getSearchedData());
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
