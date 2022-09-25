package application;

import application.presentation.empleados.Controller;

import javax.swing.*;

public class Application {
    public static JFrame window;
    public static application.presentation.empleados.Controller empleadosController;
    public static application.presentation.sucursales.Controller sucursalesController;
    public static application.presentation.main.Controller mainController;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
        catch (Exception ex) {};

        //empleados
        application.presentation.empleados.Model empleadosModel= new application.presentation.empleados.Model();
        application.presentation.empleados.View empleadosView = new application.presentation.empleados.View();
        empleadosController = new application.presentation.empleados.Controller(empleadosView,empleadosModel);

        //sucursales
        application.presentation.sucursales.Model sucursalesModel = new application.presentation.sucursales.Model();
        application.presentation.sucursales.View sucursalesView = new application.presentation.sucursales.View();
        sucursalesController = new application.presentation.sucursales.Controller(sucursalesView, sucursalesModel);

        //main
        application.presentation.main.Model mainModel= new application.presentation.main.Model();
        application.presentation.main.View mainView = new application.presentation.main.View();
        mainController = new application.presentation.main.Controller(mainView, mainModel);

        mainView.getTabbedPane1().add("Empleados",empleadosView.getPanel());
        mainView.getTabbedPane1().add("Sucursales",sucursalesView.getPanel());

        window = new JFrame();
        window.setSize(500,600);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("SISTEMA...");
        window.setVisible(true);
        mainController.show();
    }
}
