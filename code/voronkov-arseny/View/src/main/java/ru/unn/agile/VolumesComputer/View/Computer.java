package ru.unn.agile.VolumesComputer.View;

import ru.unn.agile.VolumesComputer.ViewModel.ComputerViewModel;
import ru.unn.agile.VolumesComputer.ViewModel.FigureName;

import javax.swing.*;
import java.awt.event.*;

public class Computer {
    private JComboBox m_comboBoxFigures;
    private JTextField m_textFieldIn1;
    private JTextField m_textFieldIn2;
    private JTextField m_textFieldIn3;
    private JTextField m_textFieldOut;
    private JPanel m_panelMain;
    private JLabel m_labelIn1;
    private JLabel m_labelIn2;
    private JLabel m_labelIn3;
    private JLabel m_labelOut;
    private JButton m_buttonSolve;

    private ComputerViewModel m_viewModel;

    public static void main(final String[] agrs) {
        JFrame frame = new JFrame("VolumesComputer");
        frame.setContentPane(
                new Computer(new ComputerViewModel()).m_panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Computer(final ComputerViewModel viewModel) {
        m_viewModel = viewModel;
        textFieldIn1initialize();
        textFieldIn2initialize();
        textFieldIn3initialize();
        textFieldOutInitialize();
        comboBoxFiguresInitialize();
        buttonSolveInitialize();
    }
    private void comboBoxFiguresInitialize() {
        m_comboBoxFigures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                m_viewModel.setFigure(
                        (FigureName)m_comboBoxFigures.getSelectedItem());
                m_labelIn1.setText(m_viewModel.getParameter1name() + ":");
                m_labelIn2.setText(m_viewModel.getParameter2name() + ":");
                m_labelIn3.setText(m_viewModel.getParameter3name() + ":");
                m_textFieldIn1.setEnabled(m_viewModel.isParameter1enabled());
                m_textFieldIn2.setEnabled(m_viewModel.isParameter2enabled());
                m_textFieldIn3.setEnabled(m_viewModel.isParameter3enabled());
                m_buttonSolve.setEnabled(m_viewModel.isInputCorrect());
            }
        });
        final FigureName[] figures = FigureName.values();
        m_comboBoxFigures.setModel(new JComboBox<>(figures).getModel());
        m_comboBoxFigures.setSelectedIndex(0);
    }
    private void textFieldIn1initialize() {
        m_textFieldIn1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                m_viewModel.setParameter1(m_textFieldIn1.getText());
                m_buttonSolve.setEnabled(m_viewModel.isInputCorrect());
            }
        });
    }
    private void textFieldIn2initialize() {
        m_textFieldIn2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                m_viewModel.setParameter2(m_textFieldIn2.getText());
                m_buttonSolve.setEnabled(m_viewModel.isInputCorrect());
            }
        });
    }
    private void textFieldIn3initialize() {
        m_textFieldIn3.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent keyEvent) {
                m_viewModel.setParameter3(m_textFieldIn3.getText());
                m_buttonSolve.setEnabled(m_viewModel.isInputCorrect());
            }
        });
    }
    private void textFieldOutInitialize() {
        m_textFieldOut.setEditable(false);
        m_textFieldOut.setText(m_viewModel.getVolume());
    }
    private void buttonSolveInitialize() {
        m_buttonSolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m_viewModel.setParameter1(m_textFieldIn1.getText());
                m_viewModel.setParameter2(m_textFieldIn2.getText());
                m_viewModel.setParameter3(m_textFieldIn3.getText());
                m_viewModel.solve();
                m_textFieldOut.setText(m_viewModel.getVolume());
            }
        });
    }
}
