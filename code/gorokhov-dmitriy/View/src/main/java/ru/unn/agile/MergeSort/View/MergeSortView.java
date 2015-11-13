package ru.unn.agile.MergeSort.View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ru.unn.agile.MergeSort.ViewModel.MergeSortViewModel;

public final class MergeSortView {
    private MergeSortViewModel viewModel;

    private JPanel mainPanel;
    private JTextField sourceArray;
    private JTextField sortedArray;
    private JButton buttonSort;
    private JTextField errorStatus;
    private JComboBox sortingOrderComboBox;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("MergeSortView");
        frame.setContentPane(new MergeSortView(new MergeSortViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private MergeSortView(final MergeSortViewModel viewModel) {
        this.viewModel = new MergeSortViewModel();

        buttonSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                backBind();
            }
        });

        sourceArray.getDocument().addDocumentListener(new DocumentListener() {
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

    private void backBind() {
        viewModel.setSortingArray(sourceArray.getText());
    }

    private void bind() {
        buttonSort.setEnabled(viewModel.isSortButtonEnabled());

        sortedArray.setText(viewModel.getSortingArray());
    }

}
