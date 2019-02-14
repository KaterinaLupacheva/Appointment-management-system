package by.mycompany.beautysalon.ui.views.services;

import by.mycompany.beautysalon.entity.Service;
import by.mycompany.beautysalon.service.ServiceService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AdminServiceEditor extends VerticalLayout implements KeyNotifier {

    private ServiceService serviceService;
    private Service service;

    private TextField title = new TextField("Service Title");
    private TextField duration = new TextField("Duration, min");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout buttons = new HorizontalLayout(save, delete);

    Binder<Service> binder = new Binder<>(Service.class);
    private ChangeHandler changeHandler;

    @Autowired
    public AdminServiceEditor(ServiceService serviceService) {
        this.serviceService = serviceService;
        add(title, duration, buttons);

        binder.bind(title, "title");
        binder.forField(duration)
                .withConverter(new StringToIntegerConverter("Please enter number")).bind("duration");

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());

        addKeyPressListener(Key.ENTER, e -> save());
        setVisible(false);
    }

    private void delete() {
        serviceService.delete(service);
        changeHandler.onChange();
    }

    private void save() {
        serviceService.save(service);
        changeHandler.onChange();
    }

    public interface ChangeHandler{
        void onChange();
    }

    public void setService(Service service) {
        this.service = service;
        binder.setBean(service);
        delete.setVisible(service.isPersisted());
        setVisible(true);
    }

    public void setChangeHandler(ChangeHandler ch) {
        this.changeHandler = ch;
    }
}
