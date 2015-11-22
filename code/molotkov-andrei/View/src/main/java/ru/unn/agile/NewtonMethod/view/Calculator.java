package ru.unn.agile.NewtonMethod.view;

import ru.unn.NewtonMethod.viewModel.NewtonMethodViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class Calculator {

    private NewtonMethodViewModel viewModel;
    private JPanel mainPanel;
    private JTextField txtLeftPont;
    private JTextField txtRightPoint;
    private JTextField txtFunction;
    private JButton calculateButton;
    private JTextField txtRoot;
    private JLabel labelStatus;
    private JTextField txtDerivative;

    private Calculator() {
    }

    private Calculator(final NewtonMethodViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                bind();
                viewModel.calculateRoot();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                bind();
                viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };
        txtLeftPont.addKeyListener(keyListener);
        txtRightPoint.addKeyListener(keyListener);
        txtFunction.addKeyListener(keyListener);
        txtDerivative.addKeyListener(keyListener);
    }

    private void backBind() {
        calculateButton.setEnabled(viewModel.isCalculateButtonEnabled());
        txtRoot.setText(String.valueOf(viewModel.getRoot()));
        labelStatus.setText(viewModel.getStatus());
    }

    private void bind() {
        viewModel.setFunction(txtFunction.getText());
        viewModel.setDerivative(txtDerivative.getText());
        viewModel.setLeftPointOfRange(txtLeftPont.getText());
        viewModel.setRightPointOfRange(txtRightPoint.getText());
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new Calculator(new NewtonMethodViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
