package application.presentation.empleado;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;
import application.Application;
import application.logic.Empleado;
import application.logic.Sucursal;

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
    private JLabel mapaLbl;

    private Image imagenMapa;
    private JLabel sucursalSeleccionadaLbl;
    private Image imagenSucursalSeleccionada;
    private JLabel sucursalNoSeleccionadaLbl;
    private Image imagenSucursalNoSeleccionada;

    public View() {
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clean();
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
        mapaLbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX() + "  "  + e.getY());
            }
        });
    }

    public JPanel getPanel() {
        return panel;
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
        model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        Empleado current = model.getCurrent();
        String salario = String.valueOf(current.getSalario());
        this.cedulaFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        this.cedulaFld.setText(current.getCedula());
        this.nombreFld.setText(current.getNombre());
        this.numeroTelFld.setText(current.getNumeroTel());
        if(current.getSucursal() != null){ sucursalFld.setText(current.getSucursal().getReferencia()); }
        else { sucursalFld.setText(""); }
        this.salarioFld.setText(salario);
        this.panel.validate();

        if(current.getSucursal() != null){ sucursalFld.setText(current.getSucursal().getReferencia()); }
        else { sucursalFld.setText(""); }

        actualizarMapa();

        this.panel.validate();
    }
    public void actualizarMapa(){
        mapaLbl.removeAll();
        fillMap();
        panel.updateUI();
    }

    public Empleado take() {
        Empleado e = new Empleado();
        int salario = Integer.parseInt(salarioFld.getText());
        e.setCedula(cedulaFld.getText());
        e.setNombre(nombreFld.getText());
        e.setNumeroTel(numeroTelFld.getText());
        e.setSalario(salario);
        for (int i = 0; i < model.getSucursales().size() ; i++) {
            if(model.getSucursales().get(i).getReferencia().equals(sucursalFld.getText()))
                e.setSucursal(model.getSucursales().get(i));
        }
        return e;
    }
    private void fillMap() {
        for (int j = 0; j < model.getSucursales().size(); j++) {
            JLabel temp = new JLabel();
            Sucursal s = model.getSucursales().get(j);
            temp.setSize(30, 30);
            temp.setLocation(s.getX() - 15, s.getY() - 30);
            temp.setToolTipText("<html>" + s.getReferencia()  + "<br/>" + s.getDireccion() +"</html>");
            if(sucursalFld.getText().equals(s.getReferencia()))
                temp.setIcon(new ImageIcon(imagenSucursalSeleccionada));
            else
                temp.setIcon(new ImageIcon(imagenSucursalNoSeleccionada));
            temp.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    temp.setIcon(new ImageIcon(imagenSucursalSeleccionada));
                    sucursalFld.setText(s.getReferencia());
                    sucursalFld.setForeground(Color.RED);
                    actualizarMapa();
                }
            });
            temp.setVisible(true);
            mapaLbl.add(temp);
        }
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

    public void clean() {
        cedulaLbl.setBorder(null);
        nombreLbl.setBorder(null);
        numeroTelLbl.setBorder(null);
        salarioLbl.setBorder(null);
        sucursalLbl.setBorder(null);
        mapaLbl.setBorder(null);
    }


    private void createUIComponents() throws IOException {
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

        sucursalSeleccionadaLbl.setToolTipText("Sucursal Seleccionada");
        sucursalNoSeleccionadaLbl.setToolTipText("Sucursal Seleccionada");

        sucursalSeleccionadaLbl.setSize(30, 30);
        sucursalNoSeleccionadaLbl.setSize(30, 30);
    }
}
