package by.mycompany.beautysalon.ui.views.masters;


import by.mycompany.beautysalon.dto.MasterDto;
import by.mycompany.beautysalon.entity.Service;
import by.mycompany.beautysalon.service.MasterService;
import by.mycompany.beautysalon.service.ServiceService;
import by.mycompany.beautysalon.ui.components.FormButtonsBar;
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

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AdminMasterEditor extends VerticalLayout implements KeyNotifier {

    private MasterService masterService;
    private ServiceService serviceService;
    private MasterDto masterDto;

    private TextField firstName = new TextField("First Name");
    private TextField lastName = new TextField("Last Name");
    private TextField mainService = new TextField("Main Service");

    private FormButtonsBar buttons;

    @Autowired
    private AdminMasterEditor(MasterService masterService, ServiceService serviceService) {
        this.masterService = masterService;
        this.serviceService = serviceService;

        buttons = new FormButtonsBar();

        add(firstName, lastName, mainService, buttons);
    }

    public void setBinder(Binder<MasterDto> binder, MasterDto masterDto) {
        this.masterDto = masterDto;
        binder.forField(firstName).bind(MasterDto::getFirstName, MasterDto::setFirstName);
        binder.forField(lastName).bind(MasterDto::getLastName, MasterDto::setLastName);
        binder.forField(mainService).bind(MasterDto::getMainService, MasterDto::setMainService);
    }

//    private List<String> getAllServices() {
//        List<Service> services = serviceService.findAll();
//        List<String> titles = new ArrayList<>();
//        titles.add(" ");
//        for(Service tempService : services) {
//            titles.add(tempService.getTitle());
//        }
//        return titles;
//    }

//    public void setMaster(MasterDto masterDto) {
//        this.masterDto = masterDto;
//        binder.setBean(masterDto);
//        delete.setVisible(masterDto.isPersisted());
//        setVisible(true);
//    }
//

    public FormButtonsBar getButtons() {
        return buttons;
    }

    public MasterDto getMasterDto() {
        return masterDto;
    }
}
