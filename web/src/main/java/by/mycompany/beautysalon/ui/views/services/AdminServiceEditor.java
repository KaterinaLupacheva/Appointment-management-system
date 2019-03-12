package by.mycompany.beautysalon.ui.views.services;

import by.mycompany.beautysalon.entity.Service;
import by.mycompany.beautysalon.service.ServiceService;
import by.mycompany.beautysalon.ui.components.FormButtonsBar;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AdminServiceEditor extends VerticalLayout implements KeyNotifier {

    private ServiceService serviceService;
    private Service service;

    private TextField title = new TextField("Service Title");
    private NumberField duration= new NumberField("Duration, min");

    private FormButtonsBar buttons;

    @Autowired
    public AdminServiceEditor(@Value("${schedule.interval}") Integer step, ServiceService serviceService) {
        this.serviceService = serviceService;

        buttons = new FormButtonsBar();
        duration.setStep(step);
        duration.setMin(step);
        duration.setHasControls(true);
        add(title, duration, buttons);
    }

    public void setBinder(Binder<Service> binder, Service service) {
        this.service = service;
        binder.bind(title, "title");
        binder.forField(duration).bind(Service::getDuration, Service::setDuration);
    }

    public FormButtonsBar getButtons() {
        return buttons;
    }

    public Service getService() {
        return service;
    }
}
