package application.presentation.main;

import application.Application;
import application.logic.Service;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Controller {
    Model model;
    View view;

    public Controller(View view, Model model) {
        this.model = model;
        this.view = view;
        view.setModel(model);
        view.setController(this);
    }

    public void show(){
        Application.window.setContentPane(view.getTabbedPane1());
        Application.window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.instance().store();
            }
        });
    }
}
