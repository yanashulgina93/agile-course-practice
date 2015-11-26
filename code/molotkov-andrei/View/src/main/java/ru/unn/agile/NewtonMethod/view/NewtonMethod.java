package ru.unn.agile.NewtonMethod.view;

import ru.unn.NewtonMethod.viewModel.NewtonMethodViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class NewtonMethod {
    private final NewtonMethodViewModel viewModel;
    private JPanel mainPanel;
    private JTextField txtLeftPont;
    private JTextField txtRightPoint;
    private JTextField txtFunction;
    private JButton calculateButton;
    private JTextField txtRoot;
    private JLabel labelStatus;
    private JTextField txtDerivative;

    private NewtonMethod(final NewtonMethodViewModel viewModel) {
        this.viewModel = viewModel;
        newtonMethodBind();

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                newtonMethodBackBind();
                viewModel.processKeyInTextField(NewtonMethodViewModel.KeyboardKeys.ENTER.getKey());
                newtonMethodBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent e) {
                newtonMethodBackBind();
                viewModel.processKeyInTextField(e.getKeyCode());
                newtonMethodBind();
            }
        };

        txtLeftPont.addKeyListener(keyListener);
        txtRightPoint.addKeyListener(keyListener);
        txtFunction.addKeyListener(keyListener);
        txtDerivative.addKeyListener(keyListener);
    }

    private void newtonMethodBind() {
        calculateButton.setEnabled(viewModel.isCalculateButtonEnabled());
        txtRoot.setText(String.valueOf(viewModel.getRoot()));
        labelStatus.setText(viewModel.getStatus());
        txtLeftPont.setText(viewModel.getLeftPoint());
        txtRightPoint.setText(viewModel.getRightPoint());
    }

    private void newtonMethodBackBind() {
        viewModel.setFunction(txtFunction.getText());
        viewModel.setDerivative(txtDerivative.getText());
        viewModel.setLeftPointOfRange(txtLeftPont.getText());
        viewModel.setRightPointOfRange(txtRightPoint.getText());
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Newton method");
        frame.setContentPane(new NewtonMethod(new NewtonMethodViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
