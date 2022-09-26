package application.presentation.sucursal;

import java.util.Observable;
import java.util.Observer;
import application.logic.Sucursal;

public class Model extends Observable {

    Sucursal current;
    int modo;

    public Model() { }

    public Sucursal getCurrent() {
        return current;
    }

    public void setCurrent(Sucursal current) {
        this.current = current;
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

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
