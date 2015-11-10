package ru.unn.agile.Vec3.View;

import ru.unn.agile.Vec3.ViewModel.Vector3ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class Vector3Viewer {
    private JPanel mainPanel;
    private JTextField txtCoordZ0;
    private JTextField txtCoordY0;
    private JTextField txtCoordX0;
    private JLabel lblCoordX0;
    private JLabel lblCoordY0;
    private JLabel lblCoordZ0;
    private JTextField txtCoordZ1;
    private JTextField txtCoordY1;
    private JTextField txtCoordX1;
    private JLabel lblCoordX1;
    private JLabel lblCoordY1;
    private JLabel lblCoordZ1;
    private JButton btnGetNorm0;
    private JButton btnNormalize0;
    private JButton btnGetNorm1;
    private JButton btnNormalize1;
    private JTextField txtResult;
    private JLabel lblResult;
    private JButton btnDotProduct;
    private JButton btnCrossProduct;

    private Vector3ViewModel viewModel;

    private Vector3Viewer() { }

    private Vector3Viewer(final Vector3ViewModel viewModel) {
        this.viewModel = viewModel;

        createUIActions();

        bind();
    }

    public static void main(final String[] args) {
        JFrame frame = new JFrame("Vector3dViewer");

        frame.setContentPane(new Vector3Viewer(new Vector3ViewModel()).mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIActions() {
        btnGetNorm0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.getNormOfFirstVector();
                bind();
            }
        });

        btnGetNorm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.getNormOfSecondVector();
                bind();
            }
        });

        btnNormalize0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.normalizeFirstVector();
                bind();
            }
        });

        btnNormalize1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.normalizeSecondVector();
                bind();
            }
        });

        btnDotProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.getDotProduct();
                bind();
            }
        });

        btnCrossProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                backBind();
                viewModel.getCrossProduct();
                bind();
            }
        });
    }

    private  void backBind() {
        viewModel.setCoordX0(txtCoordX0.getText());
        viewModel.setCoordY0(txtCoordY0.getText());
        viewModel.setCoordZ0(txtCoordZ0.getText());

        viewModel.setCoordX1(txtCoordX1.getText());
        viewModel.setCoordY1(txtCoordY1.getText());
        viewModel.setCoordZ1(txtCoordZ1.getText());
    }

    private void bind() {
        txtCoordX0.setText(viewModel.getCoordX0());
        txtCoordY0.setText(viewModel.getCoordY0());
        txtCoordZ0.setText(viewModel.getCoordZ0());

        txtCoordX1.setText(viewModel.getCoordX1());
        txtCoordY1.setText(viewModel.getCoordY1());
        txtCoordZ1.setText(viewModel.getCoordZ1());

        txtResult.setText(viewModel.getResultOfLastAction());
    }
}
