package application.presentation.empleado;

import application.Application;
import application.logic.Empleado;
import application.logic.Service;

import javax.swing.*;
import java.awt.*;

public class Controller {
    Model model;
    View view;
    JDialog dialog;

    public Controller(View view, Model model) {
        model.setCurrent(new Empleado());
        model.setSucursales(Service.instance().sucursalesSearch(""));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }
    public void show() {
        dialog = new JDialog(Application.window, "Empleado", true);
        dialog.setSize(600, 400);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
        Point location = Application.window.getLocation();
        dialog.setLocation(location.x+400, location.y+100);
        dialog.setVisible(true);
    }
    public void preAgregar(){
        model.setModo(Application.MODO_AGREGAR);
        model.setSucursales(Service.instance().sucursalesSearch(""));
        model.setCurrent(new Empleado());
        model.commit();
        this.show();
    }
    public void hide() { dialog.dispose();}
    public void guardar(Empleado e) throws Exception {
        switch (model.getModo()) {
            case Application.MODO_AGREGAR:
                Service.instance().empleadoAdd(e);
                model.setCurrent(new Empleado());
                break;
            case Application.MODO_EDITAR:
                Service.instance().empleadoUpdate(e);
                model.setCurrent(e);
                break;
        }
        Application.empleadosController.buscar("");
        model.commit();
    }

    public void editar(Empleado e) {
        model.setModo(Application.MODO_EDITAR);
        model.setCurrent(e);
        model.commit();
        this.show();
    }
}
