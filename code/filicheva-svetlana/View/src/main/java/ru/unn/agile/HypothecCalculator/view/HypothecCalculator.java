package ru.unn.agile.HypothecCalculator.view;

import ru.unn.agile.HypothecCalculator.viewmodel.ViewModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;


public final class HypothecCalculator {

    private JPanel mainPanel;
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
    private JTextField month;
    private JTextField year;
    private JButton compute;
    private JLabel status;

    private JTable graphicOfPayments;
    private JLabel monthlyPayment;
    private JLabel overpaymentWithFees;
    private JLabel overpayment;

    private final ViewModel viewModel = new ViewModel();

    private HypothecCalculator() {
        loadAllLists();
        backBind();


        KeyAdapter keyListener = new KeyAdapter() {
            public void keyReleased(final KeyEvent e) {
                bind();
                viewModel.parseInput();
                backBind();
            }
        };
        houseCost.addKeyListener(keyListener);
        interestRate.addKeyListener(keyListener);
        downPayment.addKeyListener(keyListener);
        countOfPeriods.addKeyListener(keyListener);
        flatFee.addKeyListener(keyListener);
        monthlyFee.addKeyListener(keyListener);
        month.addKeyListener(keyListener);
        year.addKeyListener(keyListener);

        
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
        viewModel.setHouseCost(houseCost.getText());
        viewModel.setDownPayment(downPayment.getText());
        viewModel.setCountOfPeriods(countOfPeriods.getText());
        viewModel.setInterestRate(interestRate.getText());
        viewModel.setFlatFee(flatFee.getText());
        viewModel.setMonthlyFee(monthlyFee.getText());

        viewModel.setCurrencyType((ViewModel.CurrencyType) currencyType.getSelectedItem());
        viewModel.setPeriodType((ViewModel.PeriodType) periodType.getSelectedItem());
        viewModel.setInterestRateType(
                (ViewModel.InterestRateType) interestRateType.getSelectedItem());
        viewModel.setFlatFeeType((ViewModel.FlatFeeType) flatFeeType.getSelectedItem());
        viewModel.setMonthlyFeeType((ViewModel.MonthlyFeeType) monthlyFeeType.getSelectedItem());
        viewModel.setCreditType((ViewModel.CreditType) creditType.getSelectedItem());

        viewModel.setStartMonth(month.getText());
        viewModel.setStartYear(year.getText());
    }

    private void backBind() {
        status.setText(viewModel.getStatus());
        compute.setEnabled(viewModel.isButtonEnabled());
        houseCost.setText(viewModel.getHouseCost());
        downPayment.setText(viewModel.getDownPayment());
        countOfPeriods.setText(viewModel.getCountOfPeriods());
        interestRate.setText(viewModel.getInterestRate());
        flatFee.setText(viewModel.getFlatFee());
        monthlyFee.setText(viewModel.getMonthlyFee());

        currencyType.setSelectedItem(viewModel.getCurrencyType());
        periodType.setSelectedItem(viewModel.getPeriodType());
        interestRateType.setSelectedItem(viewModel.getInterestRateType());
        flatFeeType.setSelectedItem(viewModel.getFlatFeeType());
        monthlyFeeType.setSelectedItem(viewModel.getMonthlyFeeType());
        creditType.setSelectedItem(viewModel.getCreditType());

        month.setText(viewModel.getStartMonth());
        year.setText(viewModel.getStartYear());
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


