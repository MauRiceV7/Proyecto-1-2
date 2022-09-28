package application.presentation.empleados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer{
    private JPanel panel;
    private JLabel nombreLbl;
    private JTextField nombreFld;
    private JButton buscarFld;
    private JButton agregarFld;
    private JButton borrarFld;
    private JButton imprimirFld;
    private JTable empleadosFld;
    Controller controller;
    Model model;


    public View() {
        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buscar(nombreFld.getText());
            }
        });
        agregarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.preAgregar();
            }
        });
        borrarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = empleadosFld.getSelectedRow();
                controller.borrar(row);
            }
        });
        imprimirFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.imprimir();
                    if (Desktop.isDesktopSupported()) {
                        File myFile = new File("empleados.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) { }
            }
        });
        empleadosFld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2){
                    int row = empleadosFld.getSelectedRow();
                    controller.editar(row);
                }
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
