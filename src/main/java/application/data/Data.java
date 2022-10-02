package application.data;
import application.logic.Sucursal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import application.logic.Empleado;
import application.logic.Sucursal;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    private List<Empleado> empleados;
    private List<Sucursal> sucursales;

    public Data() {
        empleados = new ArrayList<>();
        sucursales = new ArrayList<>();

//        sucursales.add(new Sucursal("Cartago", "001", "San Jose", 5, 10, 50));
//        sucursales.add(new Sucursal("San Jose", "002", "San Jose", 5, 40, 60));
//        sucursales.add(new Sucursal("Alajuela", "003", "San Jose", 5, 40, 190));
//        sucursales.add(new Sucursal("Heredia", "004", "San Jose", 5, 10, 80));
//        sucursales.add(new Sucursal("Guanacaste", "005", "San Jose", 5, 340, 70));
//
//        empleados.add(new Empleado("1111", "Juan", "345", sucursales.get(0),1000));
//        empleados.add(new Empleado("2222", "Pedro", "345", sucursales.get(1),1000));
//        empleados.add(new Empleado("3333", "Maria", "345", sucursales.get(2),1000));
//        empleados.add(new Empleado("4444", "Jose", "345", sucursales.get(3),1000));
//        empleados.add(new Empleado("5555", "Ana", "345", sucursales.get(4),1000));
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }
}
