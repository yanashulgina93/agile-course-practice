package ru.unn.agile.arabicroman.view;

import ru.unn.agile.arabicroman.viewmodel.ArabicRomanConverterViewModel;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class ArabicRomanConverterForm {
    private JTextField inputNumber;
    private JTextField outputNumber;
    private JButton convertButton;
    private JButton reverseButton;
    private JPanel mainPanel;
    private JLabel inputNumberFormat;
    private JLabel outputNumberFormat;
    private JLabel errorText;

    private ArabicRomanConverterViewModel viewModel = new ArabicRomanConverterViewModel();

    private ArabicRomanConverterForm() {
        inputNumber.getDocument().addDocumentListener(new DocumentListener() {
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

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.convert();
                bind();
            }
        });

        reverseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                viewModel.reverseConvertingDirection();
                if (viewModel.isConvertedNumberArabic()) {
                    inputNumberFormat.setText("Arabic Number");
                    outputNumberFormat.setText("Roman Number");
                } else {
                    inputNumberFormat.setText("Roman Number");
                    outputNumberFormat.setText("Arabic Number");
                }
                backBind();
                bind();
            }
        });

        bind();
    }

    private void bind() {
        convertButton.setEnabled(viewModel.isConvertButtonEnabled());
        outputNumber.setText(viewModel.getOutputNumber());
        if (viewModel.hasErrorOccurred()) {
            errorText.setText("Illegal input number!");
        } else {
            errorText.setText("");
        }
    }

    private void backBind() {
        viewModel.setInputNumber(inputNumber.getText());
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("ArabicRomanConverterForm");
        frame.setContentPane(new ArabicRomanConverterForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

