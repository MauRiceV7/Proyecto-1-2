package application.presentation.sucursal;

import javax.swing.*;

import application.Application;
import application.logic.Service;
import application.logic.Sucursal;

import java.awt.*;

public class Controller {
    Model model;
    View view;
    JDialog dialog;

    public Controller (View view, Model model) {
        model.setCurrent(new Sucursal());
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    public void show() {
        dialog = new JDialog(Application.window, "Sucursal", true);
        dialog.setSize(1000, 800);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
        Point location = Application.window.getLocation();
        dialog.setLocation(location.x+400, location.y+100);
        dialog.setVisible(true);
    }

    public void preAgregar(){
        model.setModo(Application.MODO_AGREGAR);
        model.setCurrent(new Sucursal());
        model.commit();
        this.show();
    }
    public void guardar(Sucursal s) throws Exception {
        switch (model.getModo()) {
            case Application.MODO_AGREGAR:
                Service.instance().sucursalAdd(s);
                model.setCurrent(new Sucursal());
                break;
            case Application.MODO_EDITAR:
                Service.instance().sucursalUpdate(s);
                model.setCurrent(s);
                break;
        }
        Application.sucursalesController.buscar("");
        model.commit();
    }

    public void hide() { dialog.dispose();}
}
