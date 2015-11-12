package ru.unn.agile.IntersectionFinder.view.legacy;

import javax.swing.*;

/**
 * Created by Андрей on 12.11.2015.
 */
public class IntersectionFinder {
    public static void main(String[] args) {
        JFrame frame = new JFrame("IntersectionFinder");
        frame.setContentPane(new IntersectionFinder().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JTextField pointLine;
    private JTextField vectorLine;
    private JTextField pointPlane;
    private JTextField normalPlane;
    private JTextField result;
    private JButton findIntersectionButton;
    private JLabel Line;
    private JLabel pointLineLabel;
    private JLabel vectorLineLabel;
    private JLabel Plane;
    private JPanel pointPlaneLabel;
    private JLabel normalPlaneLabel;
    private JLabel errorLabel;
    private JLabel resultLabel;
}
