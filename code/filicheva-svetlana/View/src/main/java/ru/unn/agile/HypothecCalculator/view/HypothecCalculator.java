package ru.unn.agile.HypothecCalculator.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public final class HypothecCalculator {

    private JPanel mainPanel;
    private JTable graphicOfPayments;
    private JTextField houseCost;
    private JTextField interestRate;
    private JTextField downPayment;
    private JTextField countOfPeriods;
    private JComboBox currencyType;
    private JComboBox periodType;
    private JComboBox interestRateType;
    private JTextField flatFee;
    private JTextField monthlyFee;
    private JComboBox flatFeeType;
    private JComboBox monthlyFeeType;
    private JComboBox creditType;
    private JComboBox startMonth;
    private JComboBox startYear;
    private JLabel monthlyPayment;
    private JLabel overpaymentWithFees;
    private JLabel overpayment;

    private HypothecCalculator() { }

    private void createUIComponents() {
        mainPanel = new DollarPanel();
        }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Ипотечный калькулятор");
        frame.setContentPane(new HypothecCalculator().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}

class DollarPanel extends JPanel {
    public void paintComponent(final Graphics g) {
        Image im = null;
        try {
            im = ImageIO.read(new File("code/filicheva-svetlana/View/img/money.jpg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        g.drawImage(im, 0, 0, null);
    }
}


