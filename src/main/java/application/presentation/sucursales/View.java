package application.presentation.sucursales;

import application.logic.Sucursal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
    private Image imagenMapa;
    private JLabel sucursalSeleccionadaLbl;
    private Image imagenSucursalSeleccionada;
    private JLabel sucursalNoSeleccionadaLbl;
    private Image imagenSucursalNoSeleccionada;

    private String tempS;

    Controller controller;
    Model model;

    public View() {
        //TODO todos los listeners restantes
        //TODO for de pines de sucursales
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.preAgregar();
               // loadSucursales();
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
                   // loadSucursales(row);
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
        sucursalesFld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 if (e.getClickCount() == 1) {
                     int row = sucursalesFld.getSelectedRow();
                     tempS = model.getSucursales().get(row).getReferencia();
                     updateMap();
                 }
            }
        });
    }

    public void updateMap(){
        mapaLbl.removeAll();
        fillMap();
        panel.updateUI();
    }

    private void fillMap() {
        for (int j = 0; j < model.getSucursales().size(); j++) {
            JLabel temp = new JLabel();
            Sucursal s = model.getSucursales().get(j);
            temp.setSize(30, 30);
            temp.setLocation(s.getX() - 15, s.getY() - 31);
            temp.setToolTipText("<html>" + s.getReferencia()  + "<br/>" + s.getDireccion() +"</html>");
            if(Objects.equals(tempS, s.getReferencia()))
                temp.setIcon(new ImageIcon(imagenSucursalSeleccionada));
            else
                temp.setIcon(new ImageIcon(imagenSucursalNoSeleccionada));
            temp.setVisible(true);
            mapaLbl.add(temp);
        }
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
        updateMap();
        int[] cols = {TableModel.CODIGO, TableModel.REFERENCIA, TableModel.DIRECCION, TableModel.ZONAJE};
        sucursalesFld.setModel(new TableModel(cols, model.getSucursales()));
        sucursalesFld.setRowHeight(30);
        this.panel.revalidate();
    }

    private void createUIComponents() throws IOException {
        // TODO: place custom component creation code here
        mapaLbl = new JLabel();
        imagenMapa = ImageIO.read(Objects.requireNonNull(getClass().getResource("../../../icons/mapa.png")));
        imagenMapa = imagenMapa.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        mapaLbl.setIcon(new ImageIcon(imagenMapa));

        sucursalSeleccionadaLbl = new JLabel();
        imagenSucursalSeleccionada = ImageIO.read(Objects.requireNonNull(getClass().getResource("../../../icons/SucursalSel.png")));
        imagenSucursalSeleccionada = imagenSucursalSeleccionada.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        sucursalSeleccionadaLbl.setIcon(new ImageIcon(imagenSucursalSeleccionada));

        sucursalNoSeleccionadaLbl = new JLabel();
        imagenSucursalNoSeleccionada = ImageIO.read(Objects.requireNonNull(getClass().getResource("../../../icons/Sucursal.png")));
        imagenSucursalNoSeleccionada = imagenSucursalNoSeleccionada.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        sucursalNoSeleccionadaLbl.setIcon(new ImageIcon(imagenSucursalNoSeleccionada));

        sucursalSeleccionadaLbl.setSize(30, 30);
        sucursalNoSeleccionadaLbl.setSize(30, 30);
        mapaLbl.add(sucursalSeleccionadaLbl);
        mapaLbl.add(sucursalNoSeleccionadaLbl);
    }
}