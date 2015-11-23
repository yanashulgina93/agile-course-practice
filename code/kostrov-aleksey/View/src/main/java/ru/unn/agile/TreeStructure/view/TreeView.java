package ru.unn.agile.TreeStructure.view;
import ru.unn.agile.TreeStructure.viewmodel.TreeViewModel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TreeView {
    private JPanel mainPanel;
    private JRadioButton insertRadioButton;
    private JRadioButton searchRadioButton;
    private JRadioButton truncateRadioButton;
    private JButton buttonDo;
    private JTextArea textError;
    private JTextField textKey;
    private JTextField textData;

    TreeViewModel viewModel = new TreeViewModel();

    public TreeView() {
        insertRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBind();
                bind();
            }
        });
        backBind();
    }

    private void bind() {

    }

    private void backBind() {
        buttonDo.setEnabled(viewModel.isDoButtonEnabled());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("TreeView");
        frame.setContentPane(new TreeView().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
