package ru.unn.agile.Deque.view;

import javax.swing.*;

public final class DequeForm {
    private JPanel mainPanel;
    private JTextField inputNumberText;
    private JButton pushFrontButton;
    private JButton pushBackButton;

    private DequeForm() {
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("DequeForm");
        frame.setContentPane(new DequeForm().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
