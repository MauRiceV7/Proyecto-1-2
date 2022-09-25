package application.presentation.sucursales;

import application.logic.Sucursal;
import application.logic.Service;

import java.util.List;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.setSucursales(Service.instance().sucursalesSearch(""));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void buscar(String filtro){
        List<Sucursal> rows = Service.instance().sucursalesSearch(filtro);
        model.setSucursales(rows);
        model.commit();
    }
}
