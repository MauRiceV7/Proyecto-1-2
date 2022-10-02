package application.presentation.empleado;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import application.logic.Empleado;
import application.logic.Sucursal;

public class Model extends Observable {

    Empleado current;
    private List<Sucursal> sucursales;
    int modo;

    public Model() { }

    public Empleado getCurrent() {
        return current;
    }

    public void setCurrent(Empleado current) {
        this.current = current;
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public List<Sucursal> getSucursales() { return sucursales; }

    public void setSucursales(List<Sucursal> sucursales) { this.sucursales = sucursales; }

    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit(){
        setChanged();
        notifyObservers(null);
    }
}
