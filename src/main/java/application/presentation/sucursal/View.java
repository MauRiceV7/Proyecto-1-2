package application.presentation.sucursal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import application.Application;
import application.logic.Sucursal;

public class View implements Observer {

    Controller controller;
    Model model;
    int x;
    int y;
    private JPanel panel;
    private JLabel mapaLbl;
    private JTextField codigoFld;
    private JTextField referenciaFld;
    private JTextField direccionFld;
    private JTextField zonajeFld;
    private JLabel codigoLbl;
    private JLabel referenciaLbl;
    private JLabel direccionLbl;
    private JLabel zonajeLbl;
    private JButton guardarButton;
    private JButton cancelarButton;
    //TODO implementar listeners restantes
    public View() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Sucursal n = take();
                    try {
                        controller.guardar(n);
                    } catch (Exception ex) {
                        System.out.println(ex.getCause());
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
        mapaLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        Sucursal current = model.getCurrent();
        String zonaje = String.valueOf(current.getZonaje());
        this.codigoFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        this.codigoFld.setText(current.getCodigo());
        this.referenciaFld.setText(current.getReferencia());
        this.direccionFld.setText(current.getDireccion());
        this.zonajeFld.setText(zonaje);
        this.panel.validate();
    }

    public Sucursal take() {
        int zonaje = Integer.parseInt(zonajeFld.getText());
        Sucursal s = new Sucursal();
        s.setCodigo(codigoFld.getText());
        s.setReferencia(referenciaFld.getText());
        s.setDireccion(direccionFld.getText());
        s.setZonaje(zonaje);
        s.setX(1);
        s.setY(1);
        return s;
    }


    private boolean validate() {
        boolean valid = true;
        if (codigoFld.getText().isEmpty()){
            valid = false;
            codigoLbl.setBorder(Application.BORDER_ERROR);
            codigoLbl.setToolTipText("Código Requerido");
        } else {
            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
        }

        if (referenciaFld.getText().isEmpty()){
            valid = false;
            referenciaLbl.setBorder(Application.BORDER_ERROR);
            referenciaLbl.setToolTipText("Referencia Requerida");
        } else {
            referenciaLbl.setBorder(null);
            referenciaLbl.setToolTipText(null);
        }

        if (direccionFld.getText().isEmpty()){
            valid = false;
            direccionLbl.setBorder(Application.BORDER_ERROR);
            direccionLbl.setToolTipText("Dirección Requerida");
        } else {
            direccionLbl.setBorder(null);
            direccionLbl.setToolTipText(null);
        }

        if (zonajeFld.getText().isEmpty()){
            valid = false;
            zonajeLbl.setBorder(Application.BORDER_ERROR);
            zonajeLbl.setToolTipText("Zonaje Requerido");
        } else {
            zonajeLbl.setBorder(null);
            zonajeLbl.setToolTipText(null);
        }

        return valid;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
