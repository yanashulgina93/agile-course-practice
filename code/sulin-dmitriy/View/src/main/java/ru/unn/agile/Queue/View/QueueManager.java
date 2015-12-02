package ru.unn.agile.Queue.View;

import ru.unn.agile.Queue.ViewModel.LabQueueViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class QueueManager {
    private JPanel mainPanel;
    private JButton pushElement;
    private JButton findElement;
    private JButton popElement;
    private JFormattedTextField dataOutputTextField;
    private JFormattedTextField dataInputTextField;
    private JLabel dataOutputLabel;
    private JLabel dataInputLabel;
    private JLabel sizeLabel;
    private JTextField sizeTextField;
    private JLabel headElementLabel;
    private JTextField headElementTextField;
    private LabQueueViewModel viewModel;

    private QueueManager() { }

    private QueueManager(final LabQueueViewModel newViewModel) {
        this.viewModel = newViewModel;

        pushElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                backBindToViewModel();
                viewModel.pushElement();
                bindFromViewModel();
            }
        });

        findElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                backBindToViewModel();
                viewModel.findElement();
                bindFromViewModel();
            }
        });

        popElement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent actionEvent) {
                backBindToViewModel();
                viewModel.popElement();
                bindFromViewModel();
            }
        });
        bindFromViewModel();
    }

    private void bindFromViewModel() {
        dataInputTextField.setText(viewModel.getDataInputField());
        dataOutputTextField.setText(viewModel.getDataOutputField());
        headElementTextField.setText(viewModel.getHeadElementField());
        sizeTextField.setText(Integer.toString(viewModel.getSizeField()));
        findElement.setEnabled(viewModel.isFindButtonEnabled());
        pushElement.setEnabled(viewModel.isPushButtonEnabled());
        popElement.setEnabled(viewModel.isPopButtonEnabled());
    }

    private void backBindToViewModel() {
        viewModel.setDataInputField(dataInputTextField.getText());
        viewModel.setDataOutputField(dataOutputTextField.getText());
     }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("QueueManager");
        frame.setContentPane(new QueueManager(new LabQueueViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
