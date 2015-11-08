package ru.unn.agile.Vec3.View;

import javax.swing.*;

public class Vector3dViewer {
    private JPanel mainPanel;
    private JTextField txtCoordZ0;
    private JTextField txtCoordY0;
    private JTextField txtCoordX0;
    private JLabel lblCoordX0;
    private JLabel lblCoordY0;
    private JLabel lblCoordZ0;
    private JTextField txtCoordZ1;
    private JTextField txtCoordY1;
    private JTextField txtCoordX1;
    private JLabel lblCoordX1;
    private JLabel lblCoordY1;
    private JLabel lblCoordZ1;
    private JButton btnGetNorm0;
    private JButton btnNormlize0;
    private JButton btnGetNorm1;
    private JButton btnNormalize1;
    private JTextField textField1;
    private JLabel lblResult;
    private JButton btnDotProduct;
    private JButton btnCrossProduct;

    private Vector3dViewer() { }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Vector3dViewer");

        frame.setContentPane(new Vector3dViewer().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
