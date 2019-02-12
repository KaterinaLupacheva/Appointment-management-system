package by.mycompany.beautysalon.ui.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;

@Route("admin/startpage")
@Theme(Material.class)
public class adminStart extends VerticalLayout {

    private Button services;
    private Button masters;
    private Button appointments;

    public adminStart() {
        this.services = new Button("Services");
        this.masters = new Button("Masters");
        this.appointments = new Button("Appointments");
        add(services, masters, appointments);
    }
}
