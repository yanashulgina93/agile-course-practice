package ru.unn.agile.MergeSort.View;

import javax.swing.*;

public final class MergeSortView {
    private JPanel mainPanel;
    private JTextField sourceArray;
    private JTextField sortedArray;
    private JButton buttonSort;
    private JTextField errorStatus;

    public static void main(final String[] args) {
        JFrame frame = new JFrame("MergeSortView");
        frame.setContentPane(new MergeSortView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private MergeSortView() { }
}
