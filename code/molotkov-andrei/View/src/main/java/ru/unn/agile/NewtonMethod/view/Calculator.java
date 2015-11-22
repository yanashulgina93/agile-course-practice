package ru.unn.agile.NewtonMethod.view;

import ru.unn.NewtonMethod.viewModel.NewtonMethodViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RootCalculator {

    private NewtonMethodViewModel viewModel;
    private JPanel mainPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton button1;

    private RootCalculator(NewtonMethodViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bind();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                bind();
                backBind();
            }
        };
        textField1.addKeyListener(keyListener);
        textField2.addKeyListener(keyListener);
        textField3.addKeyListener(keyListener);
    }

    private void backBind() {
        button1.setEnabled(viewModel.isCalculateButtonEnabled());
    }

    private void bind() {
        viewModel.setFunction(textField3.getText());
        viewModel.setLeftPointOfRange(textField1.getText());
        viewModel.setRightPointOfRange(textField2.getText());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new RootCalculator(new NewtonMethodViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
