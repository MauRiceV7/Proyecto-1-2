package application.presentation.acercaDe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    Controller controller;
    Model model;
    private JLabel siseLbl;
    private JLabel totalSoftLbl;
    private JPanel panel;
    private JLabel logoLbl;

    Image m;

    public void logoLoad(){
        try {
            m = ImageIO.read(getClass().getResourceAsStream("icons/logo.jpg"));
            m = m.getScaledInstance(siseLbl.getWidth(), siseLbl.getHeight(), Image.SCALE_SMOOTH);
            siseLbl.setIcon(new ImageIcon(m));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public View() { }
    @Override
    public void update(Observable o, Object arg) {

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
    public JPanel getPanel() { return panel; }




}
