package ru.unn.agile.Queue.View;

import ru.unn.agile.Queue.ViewModel.LabQueueViewModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public final class QueueManager {
    private JPanel mainPanel;
    private JButton pushElement;
    private JButton findElement;
    private JButton popElement;
    private JFormattedTextField textFieldForDataOutput;
    private JFormattedTextField textFieldForDataInput;
    private JLabel labelForDataOutput;
    private JLabel labelForDataInput;
    private JLabel labelForSize;
    private JTextField textFieldForSize;
    private JLabel labelForHeadElement;
    private JTextField textFieldForHeadElement;
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
        textFieldForDataInput.setText(viewModel.getFieldForDataInput());
        textFieldForDataOutput.setText(viewModel.getFieldForDataOutput());
        textFieldForHeadElement.setText(viewModel.getFieldForHeadElement());
        textFieldForSize.setText(Integer.toString(viewModel.getFieldForSize()));
        findElement.setEnabled(viewModel.isFindButtonEnabled());
        pushElement.setEnabled(viewModel.isPushButtonEnabled());
        popElement.setEnabled(viewModel.isPopButtonEnabled());
    }

    private void backBindToViewModel() {
        viewModel.setFieldForDataInput(textFieldForDataInput.getText());
        viewModel.setFieldForDataOutput(textFieldForDataOutput.getText());
     }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("QueueManager");

        frame.setContentPane(new QueueManager(new LabQueueViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
