package by.mycompany.beautysalon.ui.views.masters;


import by.mycompany.beautysalon.dto.MasterDto;
import by.mycompany.beautysalon.entity.Master;
import by.mycompany.beautysalon.entity.Service;
import by.mycompany.beautysalon.service.MasterService;
import by.mycompany.beautysalon.service.ServiceService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AdminMasterEditor extends VerticalLayout implements KeyNotifier {

    private MasterService masterService;
    private ServiceService serviceService;
    private MasterDto masterDto;

    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField mainService = new TextField("Main Service");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout buttons = new HorizontalLayout(save, delete);

    Binder<MasterDto> binder = new Binder<>(MasterDto.class);
    private ChangeHandler changeHandler;

    @Autowired
    private AdminMasterEditor(MasterService masterService, ServiceService serviceService) {
        this.masterService = masterService;
        this.serviceService = serviceService;

        add(firstName, lastName, mainService, buttons);

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());

        binder.bindInstanceFields(this);
        addKeyPressListener(Key.ENTER, e -> save());
        setVisible(false);
    }

    private void save() {
        masterService.saveMasterDto(masterDto);
        changeHandler.onChange();
    }

    private void delete() {
        masterService.deleteMasterDto(masterDto);
        changeHandler.onChange();
    }

    public interface ChangeHandler{
        void onChange();
    }

    private List<String> getAllServices() {
        List<Service> services = serviceService.findAll();
        List<String> titles = new ArrayList<>();
        titles.add(" ");
        for(Service tempService : services) {
            titles.add(tempService.getTitle());
        }
        return titles;
    }

    public void setMaster(MasterDto masterDto) {
        this.masterDto = masterDto;
        binder.setBean(masterDto);
        delete.setVisible(masterDto.isPersisted());
        setVisible(true);
    }

    public void setChangeHandler(ChangeHandler ch) {
        this.changeHandler = ch;
    }
}
