package ru.unn.agile.TreeStructure.view;

import javax.swing.*;


public class TreeView {
    public static void main(String[] args) {
        JFrame frame = new JFrame("TreeView");
        frame.setContentPane(new TreeView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel mainPanel;
    private JRadioButton insertRadioButton;
    private JRadioButton search_by_keyRadioButton;
    private JRadioButton truncateRadioButton;
    private JButton DOButton;
    private JTextArea textArea1;
    private JTextField inputKeyTextField;
    private JTextField inputDataTextField;
}
