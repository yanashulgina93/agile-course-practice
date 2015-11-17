package ru.unn.agile.IntegrationMethods.view;

import ru.unn.agile.IntegrationMethods.viewmodel.ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public final class Integrator {
    private JComboBox cmbFunction;
    private JTextField txtLowerLimit;
    private JTextField txtUpperLimit;
    private JComboBox cmbMethod;
    private JButton btnIntegrate;
    private JTextField txtResult;
    private JPanel mainPanel;
    private JLabel lbStatus;
    private ViewModel viewModel;

    private Integrator() { }

    private Integrator(final ViewModel viewModel) {
        this.viewModel = viewModel;
        backBind();

        loadListOfFunctions();
        loadListOfMethods();

        btnIntegrate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                Integrator.this.viewModel.integrate();
                backBind();
            }
        });

        cmbFunction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        cmbMethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                bind();
                backBind();
            }
        });

        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                Integrator.this.viewModel.processKeyInTextField(e.getKeyCode());
                backBind();
            }
        };
        txtLowerLimit.addKeyListener(keyListener);
        txtUpperLimit.addKeyListener(keyListener);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Integrator");
        frame.setContentPane(new Integrator(new ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void loadListOfFunctions() {
        ViewModel.Function[] functions = ViewModel.Function.values();
        cmbFunction.setModel(new JComboBox<>(functions).getModel());
    }

    private void loadListOfMethods() {
        ViewModel.IntegrationMethod[] methods = ViewModel.IntegrationMethod.values();
        cmbMethod.setModel(new JComboBox<>(methods).getModel());
    }

    private void bind() {
        viewModel.setLowerLimit(txtLowerLimit.getText());
        viewModel.setUpperLimit(txtUpperLimit.getText());

        viewModel.setFunction((ViewModel.Function) cmbFunction.getSelectedItem());
        viewModel.setIntegrationMethod((ViewModel.IntegrationMethod) cmbMethod.getSelectedItem());
    }

    private void backBind() {
        btnIntegrate.setEnabled(viewModel.isIntegrateButtonEnabled());

        txtResult.setText(viewModel.getResult());
        lbStatus.setText(viewModel.getStatus());
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
