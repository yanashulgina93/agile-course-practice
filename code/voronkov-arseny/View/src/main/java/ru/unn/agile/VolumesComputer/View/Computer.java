package ru.unn.agile.VolumesComputer.View;

import ru.unn.agile.VolumesComputer.ViewModel.ComputerViewModel;
import ru.unn.agile.VolumesComputer.ViewModel.FigureName;

import javax.swing.*;
import java.awt.event.*;

public final class Computer {
    private JComboBox<FigureName> mComboBoxFigures;
    private JTextField mTextFieldIn1;
    private JTextField mTextFieldIn2;
    private JTextField mTextFieldIn3;
    private JTextField mTextFieldOut;
    private JPanel mPanelMain;
    private JLabel mLabelIn1;
    private JLabel mLabelIn2;
    private JLabel mLabelIn3;
    private JLabel mLabelOut;
    private JButton mButtonSolve;

    private ComputerViewModel mViewModel;

    public static void main(final String[] agrs) {
        JFrame frame = new JFrame("VolumesComputer");
        frame.setContentPane(
                new Computer(new ComputerViewModel()).mPanelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Computer(final ComputerViewModel viewModel) {
        mViewModel = viewModel;
        textFieldIn1initialize();
        textFieldIn2initialize();
        textFieldIn3initialize();
        textFieldOutInitialize();
        comboBoxFiguresInitialize();
        buttonSolveInitialize();
    }
    private void comboBoxFiguresInitialize() {
        mComboBoxFigures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                mViewModel.setFigure(
                        (FigureName) mComboBoxFigures.getSelectedItem());
                mLabelIn1.setText(mViewModel.getParameter1name() + ":");
                mLabelIn2.setText(mViewModel.getParameter2name() + ":");
                mLabelIn3.setText(mViewModel.getParameter3name() + ":");
                mTextFieldIn1.setEnabled(mViewModel.isParameter1enabled());
                mTextFieldIn2.setEnabled(mViewModel.isParameter2enabled());
                mTextFieldIn3.setEnabled(mViewModel.isParameter3enabled());
                mButtonSolve.setEnabled(mViewModel.isInputCorrect());
            }
        });
        final FigureName[] figures = FigureName.values();
        mComboBoxFigures.setModel(new JComboBox<>(figures).getModel());
        mComboBoxFigures.setSelectedIndex(0);
    }
    private void textFieldIn1initialize() {
        mTextFieldIn1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                mViewModel.setParameter1(mTextFieldIn1.getText());
                mButtonSolve.setEnabled(mViewModel.isInputCorrect());
            }
        });
    }
    private void textFieldIn2initialize() {
        mTextFieldIn2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                mViewModel.setParameter2(mTextFieldIn2.getText());
                mButtonSolve.setEnabled(mViewModel.isInputCorrect());
            }
        });
    }
    private void textFieldIn3initialize() {
        mTextFieldIn3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                mViewModel.setParameter3(mTextFieldIn3.getText());
                mButtonSolve.setEnabled(mViewModel.isInputCorrect());
            }
        });
    }
    private void textFieldOutInitialize() {
        mTextFieldOut.setEditable(false);
        mTextFieldOut.setText(mViewModel.getVolume());
    }
    private void buttonSolveInitialize() {
        mButtonSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                mViewModel.setParameter1(mTextFieldIn1.getText());
                mViewModel.setParameter2(mTextFieldIn2.getText());
                mViewModel.setParameter3(mTextFieldIn3.getText());
                mViewModel.solve();
                mTextFieldOut.setText(mViewModel.getVolume());
            }
        });
    }
}
