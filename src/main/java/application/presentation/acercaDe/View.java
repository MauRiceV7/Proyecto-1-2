package application.presentation.acercaDe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    Controller controller;
    Model model;
    private JLabel siseLbl;
    private JLabel totalSoftLbl;
    private JPanel panel;
    private JLabel logoLbl;

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
