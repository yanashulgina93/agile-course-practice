package ru.unn.agile.PercentAccretion.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import ru.unn.agile.PercentAccretion.viewmodel.PercentAccretionViewModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class PercentAccretionView {
    private final PercentAccretionViewModel viewModel;

    private JTextField initialSumTFld;
    private JTextField percentRateTFld;
    private JTextField countOfYearsTFld;
    private JTextArea errorTArea;
    private JPanel labelPanel;
    private JPanel fieldsPanel;
    private JRadioButton simplePercentRBtn;
    private JRadioButton complexPercentRBtn;
    private JLabel initialSumLbl;
    private JLabel percentRLbl;
    private JLabel timeLbl;
    private JButton calculateSumBtn;
    private JPanel resultPanel;
    private JPanel choosePercentPanel;
    private JPanel calculationPanel;
    private JPanel formPanel;
    private JTextField resultTField;
    private final ButtonGroup radioBtnGroup;

    private PercentAccretionView() {
        viewModel = new PercentAccretionViewModel();

        radioBtnGroup = new ButtonGroup();
        radioBtnGroup.add(simplePercentRBtn);
        radioBtnGroup.add(complexPercentRBtn);

        radioBtnGroup.setSelected(simplePercentRBtn.getModel(), true);
        simplePercentRBtn.setActionCommand("simple");
        complexPercentRBtn.setActionCommand("complex");

        ActionListener radioBtnListener = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                bind();
            }
        };

        simplePercentRBtn.addActionListener(radioBtnListener);
        complexPercentRBtn.addActionListener(radioBtnListener);

        initialSumTFld.getDocument().addDocumentListener(new DocumentListener() {
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

        percentRateTFld.getDocument().addDocumentListener(new DocumentListener() {
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

        countOfYearsTFld.getDocument().addDocumentListener(new DocumentListener() {
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

        calculateSumBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.calculateResultSum();
                bind();
            }
        });

        bind();

    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("PercentAccretionView");
        frame.setContentPane(new PercentAccretionView().formPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void bind() {
        calculateSumBtn.setEnabled(viewModel.isCalculateButtonEnabled());
        errorTArea.setText(viewModel.getErrorMessage());
        resultTField.setText(viewModel.getResultSum());
    }

    private void backBind() {
        viewModel.setInitialSum(initialSumTFld.getText());
        viewModel.setPercentRate(percentRateTFld.getText());
        viewModel.setCountOfYears(countOfYearsTFld.getText());
        viewModel.setPercentType(radioBtnGroup.getSelection().getActionCommand());
    }
}
