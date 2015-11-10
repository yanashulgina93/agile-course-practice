package ru.unn.agile.IntegrationMethods.view;

import javax.swing.*;

/**
 * Created by USER on 09.11.2015.
 */
public final class Integrator {
    private JComboBox cmbFunction;
    private JTextField txtLowerLimit;
    private JTextField txtUpperLimit;
    private JComboBox cmbMethod;
    private JButton btnIntegrate;
    private JTextField txtResult;
    private JPanel mainPanel;
    private JLabel lbStatus;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Integrator");
        frame.setContentPane(new Integrator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
