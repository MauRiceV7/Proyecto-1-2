package application.presentation.empleados;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer{
    private JPanel panel;
    private JLabel nombreLbl;
    private JTextField nombreFld;
    private JButton buscarFld;
    private JButton agregarFld;
    private JButton borrarFld;
    private JButton imprimierFld;
    private JTable empleadosFld;
    Controller controller;
    Model model;


    public View() {
        //TODO todos los listeners restantes y el icono de imprimirFld
        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buscar(nombreFld.getText());
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }
    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        int[] cols = {TableModel.CEDULA, TableModel.NOMBRE, TableModel.TELEFONO, TableModel.SALARIO, TableModel.SUCURSAL, TableModel.ZONAJE, TableModel.SALARIOTOTAL};
        empleadosFld.setModel(new TableModel(cols, model.getEmpleados()));
        empleadosFld.setRowHeight(30);
        this.panel.revalidate();
    }
}
