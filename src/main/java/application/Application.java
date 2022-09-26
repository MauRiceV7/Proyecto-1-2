package application;

import application.presentation.empleados.Controller;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;


public class Application {
    public static JFrame window;
    public static application.presentation.empleados.Controller empleadosController;

    public static application.presentation.empleado.Controller empleadoController;
    public static application.presentation.sucursales.Controller sucursalesController;
    public static application.presentation.sucursal.Controller sucursalController;
    public static application.presentation.acercaDe.Controller acercaDeController;
    public static application.presentation.main.Controller mainController;
    public static  final int  MODO_AGREGAR=0;
    public static final int MODO_EDITAR=1;
    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        //empleados
        application.presentation.empleados.Model empleadosModel= new application.presentation.empleados.Model();
        application.presentation.empleados.View empleadosView = new application.presentation.empleados.View();
        empleadosController = new application.presentation.empleados.Controller(empleadosView,empleadosModel);

        //empleado
        application.presentation.empleado.Model empleadoModel= new application.presentation.empleado.Model();
        application.presentation.empleado.View empleadoView = new application.presentation.empleado.View();
        empleadoController = new application.presentation.empleado.Controller(empleadoView,empleadoModel);

        //sucursales
        application.presentation.sucursales.Model sucursalesModel = new application.presentation.sucursales.Model();
        application.presentation.sucursales.View sucursalesView = new application.presentation.sucursales.View();
        sucursalesController = new application.presentation.sucursales.Controller(sucursalesView, sucursalesModel);

        //sucursal
        application.presentation.sucursal.Model sucursalModel = new application.presentation.sucursal.Model();
        application.presentation.sucursal.View sucursalView = new application.presentation.sucursal.View();
        sucursalController = new application.presentation.sucursal.Controller(sucursalView, sucursalModel);

        //acerca de
        application.presentation.acercaDe.Model acercaDeModel = new application.presentation.acercaDe.Model();
        application.presentation.acercaDe.View acercaDeView = new application.presentation.acercaDe.View();
        acercaDeController = new application.presentation.acercaDe.Controller(acercaDeView, acercaDeModel);

        //main
        application.presentation.main.Model mainModel= new application.presentation.main.Model();
        application.presentation.main.View mainView = new application.presentation.main.View();
        mainController = new application.presentation.main.Controller(mainView, mainModel);

        mainView.getTabbedPane1().add("Empleados",empleadosView.getPanel());
        mainView.getTabbedPane1().add("Sucursales",sucursalesView.getPanel());
        mainView.getTabbedPane1().add("Acerca de..", acercaDeView.getPanel());

        window = new JFrame();
        window.setSize(500,600);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("SISTEMA...");
        window.setVisible(true);
        mainController.show();
    }
}
