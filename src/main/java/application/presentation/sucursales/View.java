package application.presentation.sucursales;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel panel;
    private JTextField referenciaFld;
    private JLabel referenciaLbl;
    private JButton buscarFld;
    private JButton agregarButton;
    private JButton borrarButton;
    private JButton imprimirButton;
    private JTable sucursalesFld;
    private JPanel mapaFld;

    Controller controller;
    Model model;


    public View() {
        //TODO todos los listeners restantes
        //TODO for de pines de sucursales
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.preAgregar();
            }
        });
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = sucursalesFld.getSelectedRow();
                controller.borrar(row);
            }
        });
        sucursalesFld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = sucursalesFld.getSelectedRow();
                    controller.editar(row);
                }
            }
        });
        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buscar(referenciaFld.getText());
            }
        });
        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.imprimir();
                    if (Desktop.isDesktopSupported()){
                        File myFile = new File("sucursales.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) { }
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
        int[] cols = {TableModel.CODIGO, TableModel.REFERENCIA, TableModel.DIRECCION, TableModel.ZONAJE};
        sucursalesFld.setModel(new TableModel(cols, model.getSucursales()));
        sucursalesFld.setRowHeight(30);
        this.panel.revalidate();
    }
}
