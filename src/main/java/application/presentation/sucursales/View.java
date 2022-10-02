package application.presentation.sucursales;

import application.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
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
    private JLabel mapaLbl;

    Controller controller;
    Model model;


    public View() {
        //TODO todos los listeners restantes
        //TODO for de pines de sucursales
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.preAgregar();
                loadSucursales();
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
                int row = sucursalesFld.getSelectedRow();
                if(e.getClickCount() == 1){
                    loadSucursales(row);
                }
                if (e.getClickCount() == 2) {
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
    public void loadSucursales(int selected){
        int posVec = 0;
        JLabel suc;
        Image n;

        mapaLbl.removeAll();

        if(!model.getSucursales().isEmpty() || model != null) {
            for (Sucursal s : model.getSucursales()) {
                suc = new JLabel();
                if (posVec == selected) {
                    try {
                        BufferedImage m = ImageIO.read(getClass().getResource("../../../icons/SucursalSel.png"));
                        suc.setIcon(new ImageIcon(m));
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                } else {
                    try {
                        BufferedImage m = ImageIO.read(getClass().getResource("../../../icons/Sucursal.png"));
                        suc.setIcon(new ImageIcon(m));
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                }
                suc.putClientProperty("indice", posVec);
                suc.setLocation(s.getX(), s.getY());
                suc.setText("hola");
                posVec++;

                mapaLbl.add(suc);
            }
        }
        mapaLbl.repaint();
    }
    public void loadSucursales(){
        int posVec = 0;
        JLabel suc;
        Image m;

        mapaLbl.removeAll();

        if(model != null || !model.getSucursales().isEmpty()  ){
            for(Sucursal s: model.getSucursales()){
                suc = new JLabel();

                try {
                    m = ImageIO.read(getClass().getResource("../../../icons/Sucursal.png"));
                    suc.setIcon(new ImageIcon(m));
                } catch (IOException e) {
                    System.out.println(e);
                }

                suc.setVisible(false);
                suc.putClientProperty("indice", posVec);
                suc.setLocation(s.getX(), s.getY());
                suc.setToolTipText(s.getCodigo() + ", " + s.getReferencia() + ", " + s.getDireccion());

                posVec++;

                mapaLbl.add(suc);
            }
        }
        mapaLbl.repaint();
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
