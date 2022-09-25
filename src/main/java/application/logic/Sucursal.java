package application.logic;

import jakarta.xml.bind.annotation.XmlID;

public class Sucursal {
    String referencia;
    @XmlID
    String codigo;
    String direccion;
    int zonaje;
    int x;
    int y;

    public Sucursal(String referencia, String codigo, String direccion, int zonaje, int x, int y) {
        this.referencia = referencia;
        this.codigo = codigo;
        this.direccion = direccion;
        this.zonaje = zonaje;
        this.x = x;
        this.y = y;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getZonaje() { return zonaje; }

    public void setZonaje(int zonaje) {  this.zonaje = zonaje;  }

    public int getX() {  return x;  }

    public void setX(int x) {   this.x = x;    }

    public int getY() {  return y;    }

    public void setY(int y) {  this.y = y;  }
}
