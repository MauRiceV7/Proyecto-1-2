package application.presentation.main;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
    }
}
