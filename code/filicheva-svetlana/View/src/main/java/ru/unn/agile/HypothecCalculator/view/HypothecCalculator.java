package ru.unn.agile.HypothecCalculator.view;

import ru.unn.agile.HypothecCalculator.viewmodel.ViewModel;

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
    private JComboBox<ViewModel.CurrencyType> currencyType;
    private JComboBox<ViewModel.PeriodType> periodType;
    private JComboBox<ViewModel.InterestRateType> interestRateType;
    private JTextField flatFee;
    private JTextField monthlyFee;
    private JComboBox<ViewModel.FlatFeeType> flatFeeType;
    private JComboBox<ViewModel.MonthlyFeeType> monthlyFeeType;
    private JComboBox<ViewModel.CreditType> creditType;
    private JLabel monthlyPayment;
    private JLabel overpaymentWithFees;
    private JLabel overpayment;
    private JTextField month;
    private JTextField year;
    private JTextField day;
    private JButton compute;
    private JLabel status;

    private final ViewModel viewModel = new ViewModel();

    private HypothecCalculator() {
        loadAllLists();
        backBind();
    }

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

    private void bind() {
        viewModel.setStatus(status.getText());
    }

    private void backBind() {
        status.setText(viewModel.getStatus());
    }

    private void loadListOfCurrencies() {
        ViewModel.CurrencyType[] currencies = ViewModel.CurrencyType.values();
        currencyType.setModel(new JComboBox<>(currencies).getModel());
    }

    private void loadListOfPeriods() {
        ViewModel.PeriodType[] periods = ViewModel.PeriodType.values();
        periodType.setModel(new JComboBox<>(periods).getModel());
    }

    private void loadListOfInterestRateTypes() {
        ViewModel.InterestRateType[] types = ViewModel.InterestRateType.values();
        interestRateType.setModel(new JComboBox<>(types).getModel());
    }

    private void loadListOfFlatFeeTypes() {
        ViewModel.FlatFeeType[] types = ViewModel.FlatFeeType.values();
        flatFeeType.setModel(new JComboBox<>(types).getModel());
    }

    private void loadListOfMonthlyFeeTypes() {
        ViewModel.MonthlyFeeType[] types = ViewModel.MonthlyFeeType.values();
        monthlyFeeType.setModel(new JComboBox<>(types).getModel());
    }

    private void loadListOfCreditTypes() {
        ViewModel.CreditType[] types = ViewModel.CreditType.values();
        creditType.setModel(new JComboBox<>(types).getModel());
    }

    private void loadAllLists() {
        loadListOfCurrencies();
        loadListOfPeriods();
        loadListOfInterestRateTypes();
        loadListOfFlatFeeTypes();
        loadListOfMonthlyFeeTypes();
        loadListOfCreditTypes();
    }
}

class DollarPanel extends JPanel {

    public DollarPanel() { }

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


