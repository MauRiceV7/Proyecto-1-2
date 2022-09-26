package application.presentation.acercaDe;



public class Controller {
    Model model;
    View view;
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

}
