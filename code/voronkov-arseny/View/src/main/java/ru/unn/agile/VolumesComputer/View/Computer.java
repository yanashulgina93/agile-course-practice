package ru.unn.agile.VolumesComputer.View;

import ru.unn.agile.VolumesComputer.ViewModel.ComputerViewModel;
import ru.unn.agile.VolumesComputer.ViewModel.FigureName;

import javax.swing.*;
import java.awt.event.*;

public final class Computer {
    private JComboBox<FigureName> comboBoxFigures;
    private JTextField textFieldIn1;
    private JTextField textFieldIn2;
    private JTextField textFieldIn3;
    private JTextField textFieldOut;
    private JPanel panelMain;
    private JLabel labelIn1;
    private JLabel labelIn2;
    private JLabel labelIn3;
    private JLabel labelOut;
    private JButton buttonSolve;

    private final ComputerViewModel viewModel;

    public static void main(final String[] agrs) {
        JFrame frame = new JFrame("VolumesComputer");
        frame.setContentPane(
                new Computer(new ComputerViewModel()).panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Computer(final ComputerViewModel viewModel) {
        this.viewModel = viewModel;
        textFieldIn1initialize();
        textFieldIn2initialize();
        textFieldIn3initialize();
        textFieldOutInitialize();
        comboBoxFiguresInitialize();
        buttonSolveInitialize();
    }
    private void comboBoxFiguresInitialize() {
        comboBoxFigures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                viewModel.setFigure(
                        (FigureName) comboBoxFigures.getSelectedItem());
                labelIn1.setText(viewModel.getParameter1name() + ":");
                labelIn2.setText(viewModel.getParameter2name() + ":");
                labelIn3.setText(viewModel.getParameter3name() + ":");
                textFieldIn1.setEnabled(viewModel.isParameter1enabled());
                textFieldIn2.setEnabled(viewModel.isParameter2enabled());
                textFieldIn3.setEnabled(viewModel.isParameter3enabled());
                buttonSolve.setEnabled(viewModel.isInputCorrect());
            }
        });
        final FigureName[] figures = FigureName.values();
        comboBoxFigures.setModel(new JComboBox<>(figures).getModel());
        comboBoxFigures.setSelectedIndex(0);
    }
    private void textFieldIn1initialize() {
        textFieldIn1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                viewModel.setParameter1(textFieldIn1.getText());
                buttonSolve.setEnabled(viewModel.isInputCorrect());
            }
        });
    }
    private void textFieldIn2initialize() {
        textFieldIn2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                viewModel.setParameter2(textFieldIn2.getText());
                buttonSolve.setEnabled(viewModel.isInputCorrect());
            }
        });
    }
    private void textFieldIn3initialize() {
        textFieldIn3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                viewModel.setParameter3(textFieldIn3.getText());
                buttonSolve.setEnabled(viewModel.isInputCorrect());
            }
        });
    }
    private void textFieldOutInitialize() {
        textFieldOut.setEditable(false);
        textFieldOut.setText(viewModel.getVolume());
    }
    private void buttonSolveInitialize() {
        buttonSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                viewModel.setParameter1(textFieldIn1.getText());
                viewModel.setParameter2(textFieldIn2.getText());
                viewModel.setParameter3(textFieldIn3.getText());
                viewModel.solve();
                textFieldOut.setText(viewModel.getVolume());
            }
        });
    }
}
