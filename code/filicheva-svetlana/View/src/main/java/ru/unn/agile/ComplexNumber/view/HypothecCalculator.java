package ru.unn.agile.ComplexNumber.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class HypothecCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ипотечный калькулятор");
        frame.setContentPane(new HypothecCalculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(125, 125, 900, 800);
    }

    private JPanel mainPanel;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JTextField textField5;
    private JTextField textField6;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JComboBox comboBox6;
    private JComboBox comboBox7;
    private JComboBox comboBox8;

    private void createUIComponents() {
        mainPanel = new DollarPanel();
        }
}

class DollarPanel extends JPanel {
    public void paintComponent(Graphics g) {
        Image im = null;
        try {
            im = ImageIO.read(new File("code/filicheva-svetlana/View/img/money.jpg"));
        } catch (IOException e) {
        }
        g.drawImage(im, 0, 0, null);
    }
}