package application.presentation.empleado;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import application.Application;
import application.logic.Empleado;

public class View implements Observer {

    Controller controller;
    Model model;
    private JPanel panel;
    private JLabel cedulaLbl;
    private JTextField cedulaFld;
    private JTextField nombreFld;
    private JLabel nombreLbl;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JLabel numeroTelLbl;
    private JTextField numeroTelFld;
    private JLabel sucursalLbl;
    private JTextField sucursalFld;
    private JTextField salarioFld;
    private JLabel salarioLbl;

    public View() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Empleado n = take();
                    try {
                       controller.guardar(n);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
             }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.hide();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
    //TODO Revisar funcionalidad add Empleado
    //No agrega empleado,
    //Puede ser problable a que falta implementar el add Sucursal
    public Empleado take() {
        Empleado e = new Empleado();
        int salario = Integer.parseInt(salarioFld.getText());
        e.setCedula(cedulaFld.getText());
        e.setNombre(nombreFld.getText());
        e.setNumeroTel(numeroTelFld.getText());
        e.setSucursal(null);
        e.setSalario(salario);
        return e;
    }
    private boolean validate() {
        boolean valid = true;
        if (cedulaFld.getText().isEmpty()){
            valid = false;
            cedulaLbl.setBorder(Application.BORDER_ERROR);
            cedulaLbl.setToolTipText("ID requerido");
        } else {
            cedulaLbl.setBorder(null);
            cedulaLbl.setToolTipText(null);
        }

        if (nombreFld.getText().isEmpty()){
            valid = false;
            nombreLbl.setBorder(Application.BORDER_ERROR);
            nombreLbl.setToolTipText("Nombre requerido");
        } else {
            nombreLbl.setBorder(null);
            nombreLbl.setToolTipText(null);
        }

        if (salarioFld.getText().isEmpty()){
            valid = false;
            salarioLbl.setBorder(Application.BORDER_ERROR);
            salarioLbl.setToolTipText("Salario requerido");
        } else {
            salarioLbl.setBorder(null);
            salarioLbl.setToolTipText(null);
        }

        if (sucursalFld.getText().isEmpty()){
            valid = false;
            sucursalLbl.setBorder(Application.BORDER_ERROR);
            sucursalLbl.setToolTipText("Sucursal requerida");
        } else {
            sucursalLbl.setBorder(null);
            sucursalLbl.setToolTipText(null);
        }

        if (numeroTelFld.getText().isEmpty()){
            valid = false;
            numeroTelLbl.setBorder(Application.BORDER_ERROR);
            numeroTelLbl.setToolTipText("Numero de telefono requerido");
        } else {
            numeroTelLbl.setBorder(null);
            numeroTelLbl.setToolTipText(null);
        }
        return valid;
    }
}
