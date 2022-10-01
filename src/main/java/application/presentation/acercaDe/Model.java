package application.presentation.acercaDe;

import java.util.Observable;
import java.util.Observer;

public class Model extends Observable {

    public Model() { }

    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit(){
        setChanged();
        notifyObservers(null);
    }

}
