package ru.unn.agile.QuadraticEquationSolver.view;

import javax.swing.*;

public class QuadraticEquationSolver {
    private JPanel mainPanel;
    private JTextField textCoefA;
    private JLabel b;
    private JTextField textCoefB;
    private JLabel c;
    private JTextField textCoefC;
    private JLabel a;
    private JLabel inputMessage;
    private JButton buttonSolve;
    private JLabel answerMessage;
    private JTextField textAnswer;
    private JLabel errorMessage;

    public static void main(String[] args) {
        JFrame frame = new JFrame("QuadraticEquationSolver");
        frame.setContentPane(new QuadraticEquationSolver().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
