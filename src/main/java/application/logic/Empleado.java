package application.logic;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;


@XmlAccessorType(XmlAccessType.FIELD)
public class Empleado {
    @XmlID
    String cedula;
    String nombre;
    String numeroTel;
    int salario;
    @XmlIDREF
    Sucursal sucursal;


    public Empleado(String cedula, String nombre, String numeroTel, Sucursal sucursal, int salario) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.numeroTel = numeroTel;
        this.sucursal = sucursal;
        this.salario = salario;
    }

    public Empleado() {
        this("","","", null, 0);
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public int getSalarioTotal(){
        return salario+((salario*sucursal.zonaje)/100);
    }
}
