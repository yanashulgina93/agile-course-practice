package ru.unn.agile.LeftistHeap.view;

import javax.swing.*;

public class HeapForm {
    private JPanel rootPanel;
    private JTextField textNumberToInsert;
    private JTextField textNumberToDelete;
    private JButton buttonInsert;
    private JButton buttonDelete;
    private JTextArea textError;
    private JCheckBox checkSortedView;
    private JTextArea textHeapContent;

    public static void main(String[] args) {
        JFrame frame = new JFrame("HeapForm");
        frame.setContentPane(new HeapForm().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
